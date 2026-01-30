package com.Examen.CajeroService.Service;

import com.Examen.CajeroService.DTO.AccesoCuentaRequest;
import com.Examen.CajeroService.DTO.AccesoCuentaResponse;
import com.Examen.CajeroService.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;

@Service
public class AccesoCuentaService {

    @PersistenceContext
    private EntityManager entityManager;

    public Result ejecutarAccesoCuenta(AccesoCuentaRequest accesoCuentaRequest) {

        Result result = new Result();

        try {

            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("AccesoCuenta");

            spQuery.registerStoredProcedureParameter("pIdUsuario", Integer.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter("pNoCuenta", Integer.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter("pNip", Integer.class, ParameterMode.IN);

            spQuery.registerStoredProcedureParameter("oIdCuenta", Integer.class, ParameterMode.OUT);
            spQuery.registerStoredProcedureParameter("oCodigo", Integer.class, ParameterMode.OUT);
            spQuery.registerStoredProcedureParameter("oMensaje", String.class, ParameterMode.OUT);

            spQuery.setParameter("pIdUsuario", accesoCuentaRequest.getIdUsuario());
            spQuery.setParameter("pNoCuenta", accesoCuentaRequest.getNoCuenta());
            spQuery.setParameter("pNip", accesoCuentaRequest.getNip());

            spQuery.execute();

            Integer idCuenta = (Integer) spQuery.getOutputParameterValue("oIdCuenta");
            Integer codigo = (Integer) spQuery.getOutputParameterValue("oCodigo");
            String mensaje = (String) spQuery.getOutputParameterValue("oMensaje");

            AccesoCuentaResponse accesoCuentaResponse = new AccesoCuentaResponse(idCuenta, codigo, mensaje);

            if (codigo != null && codigo == 0) {

                result.correct = true;
                result.object = accesoCuentaResponse;

            } else {

                result.correct = false;
                result.object = accesoCuentaResponse;
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
