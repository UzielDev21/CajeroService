package com.Examen.CajeroService.Service;

import com.Examen.CajeroService.DTO.DesgloseItem;
import com.Examen.CajeroService.DTO.EjecutarRetiroRequest;
import com.Examen.CajeroService.DTO.EjecutarRetiroResponse;
import com.Examen.CajeroService.JPA.Result;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EjecutarRetiroService {

    @PersistenceContext
    private EntityManager entityManager;

    private final ObjectMapper objectMapper = new ObjectMapper();

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
            spQuery.registerStoredProcedureParameter("oDesglose", String.class, ParameterMode.OUT);

            spQuery.setParameter("pIdUsuario", ejecutarRetiroRequest.getIdUsuario());
            spQuery.setParameter("pIdCuenta", ejecutarRetiroRequest.getIdCuenta());
            spQuery.setParameter("pIdCajero", ejecutarRetiroRequest.getIdCajero());
            spQuery.setParameter("pMonto", ejecutarRetiroRequest.getMonto());

            spQuery.execute();

            Integer codigo = (Integer) spQuery.getOutputParameterValue("oCodigo");
            String mensaje = (String) spQuery.getOutputParameterValue("oMensaje");
            String desglose = (String) spQuery.getOutputParameterValue("oDesglose");

            List<DesgloseItem> listaDesglose = new ArrayList<>();

            if (desglose != null && !desglose.isEmpty() && codigo == 0) {
                try {
                    listaDesglose = objectMapper.readValue(desglose, new TypeReference<List<DesgloseItem>>() {
                    });
                } catch (Exception ex) {
                    System.out.println("Error parseando JSON de desglose: " + ex.getLocalizedMessage());
                }
            }

            EjecutarRetiroResponse ejecutarRetiroResponse = new EjecutarRetiroResponse(codigo, mensaje, listaDesglose);

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
