package com.Examen.CajeroService.Service;

import com.Examen.CajeroService.DTO.EjecutarRetiroRequest;
import com.Examen.CajeroService.DTO.EjecutarRetiroResponse;
import com.Examen.CajeroService.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class EjecutarRetiroService {

    @PersistenceContext
    private EntityManager entityManager;

    public Result ejecutarRetiroService(EjecutarRetiroRequest ejecutarRetiroRequest) {

        Result result = new Result();

        try {

            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("EjecutarRetiro");

            spQuery.registerStoredProcedureParameter("pIdUsuario", Integer.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter("pIdCuenta", Integer.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter("pIdCajero", Integer.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter("pMonto", BigDecimal.class, ParameterMode.IN);

            spQuery.registerStoredProcedureParameter("oCodigo", Integer.class, ParameterMode.OUT);
            spQuery.registerStoredProcedureParameter("oMensaje", String.class, ParameterMode.OUT);

            spQuery.setParameter("pIdUsuario", ejecutarRetiroRequest.getIdUsuario());
            spQuery.setParameter("pIdCuenta", ejecutarRetiroRequest.getIdCuenta());
            spQuery.setParameter("pIdCajero", ejecutarRetiroRequest.getIdCajero());
            spQuery.setParameter("pMonto", ejecutarRetiroRequest.getMonto());
            
            spQuery.execute();

            Integer codigo = (Integer) spQuery.getOutputParameterValue("oCodigo");
            String mensaje = (String) spQuery.getOutputParameterValue("oMensaje");

            EjecutarRetiroResponse ejecutarRetiroResponse = new EjecutarRetiroResponse(codigo, mensaje);

            if (Integer.valueOf(0).equals(codigo)) {
                result.correct = true;
                result.object = ejecutarRetiroResponse;
            } else {
                result.correct = false;
                result.object = ejecutarRetiroResponse;
                result.errorMessage = mensaje;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
