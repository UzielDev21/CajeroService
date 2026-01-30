package com.Examen.CajeroService.Service;

import com.Examen.CajeroService.DTO.ValidarCajeroRequest;
import com.Examen.CajeroService.DTO.ValidarCajeroResponse;
import com.Examen.CajeroService.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class ValidarCajeroService {

    @PersistenceContext
    private EntityManager entityManager;

    public Result ejecutarValidacionCajero(ValidarCajeroRequest validarCajeroRequest) {

        Result result = new Result();

        try {

            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("ValidarCajero");

            spQuery.registerStoredProcedureParameter("pIdCajero", Integer.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter("pMonto", BigDecimal.class, ParameterMode.IN);

            spQuery.registerStoredProcedureParameter("oCodigo", Integer.class, ParameterMode.OUT);
            spQuery.registerStoredProcedureParameter("oMensaje", String.class, ParameterMode.OUT);

            spQuery.setParameter("pIdCajero", validarCajeroRequest.getIdCajero());
            spQuery.setParameter("pMonto", validarCajeroRequest.getMonto());
            
            spQuery.execute();

            Integer codigo = (Integer) spQuery.getOutputParameterValue("oCodigo");
            String mensaje = (String) spQuery.getOutputParameterValue("oMensaje");

            ValidarCajeroResponse validarCajeroResponse = new ValidarCajeroResponse(codigo, mensaje);

            if (Integer.valueOf(0).equals(codigo)) {

                result.correct = true;
                result.object = validarCajeroResponse;

            } else {

                result.correct = false;
                result.object = validarCajeroResponse;
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
