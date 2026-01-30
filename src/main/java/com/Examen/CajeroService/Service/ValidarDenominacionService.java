package com.Examen.CajeroService.Service;

import com.Examen.CajeroService.DTO.ValidarDenominacionRequest;
import com.Examen.CajeroService.DTO.ValidarDenominacionResponse;
import com.Examen.CajeroService.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class ValidarDenominacionService {

    @PersistenceContext
    private EntityManager entityManager;

    public Result ejecutarValidacionDenominacion(ValidarDenominacionRequest validarDenominacionRequest) {

        Result result = new Result();

        try {

            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("ValidarDenominacion");

            spQuery.registerStoredProcedureParameter("pIdCajero", Integer.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter("pMonto", BigDecimal.class, ParameterMode.IN);

            spQuery.registerStoredProcedureParameter("oCodigo", Integer.class, ParameterMode.OUT);
            spQuery.registerStoredProcedureParameter("oMensaje", String.class, ParameterMode.OUT);
            spQuery.registerStoredProcedureParameter("oDesglose", String.class, ParameterMode.OUT);

            spQuery.setParameter("pIdCajero", validarDenominacionRequest.getIdCajero());
            spQuery.setParameter("pMonto", validarDenominacionRequest.getMonto());
            
            spQuery.execute();

            Integer codigo = (Integer) spQuery.getOutputParameterValue("oCodigo");
            String mensaje = (String) spQuery.getOutputParameterValue("oMensaje");
            String desglose = (String) spQuery.getOutputParameterValue("oDesglose");

            ValidarDenominacionResponse validarDenominacionResponse = new ValidarDenominacionResponse(codigo, mensaje, desglose);

            if (Integer.valueOf(0).equals(codigo)) {

                result.correct = true;
                result.object = validarDenominacionResponse;

            } else {
                result.correct = false;
                result.object = validarDenominacionResponse;
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
