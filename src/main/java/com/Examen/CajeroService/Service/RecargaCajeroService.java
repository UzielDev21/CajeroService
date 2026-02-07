package com.Examen.CajeroService.Service;

import com.Examen.CajeroService.DTO.RecargarCajeroRequest;
import com.Examen.CajeroService.DTO.RecargarCajeroResponse;
import com.Examen.CajeroService.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;

@Service
public class RecargaCajeroService {

    @PersistenceContext
    private EntityManager entityManager;

    public Result ejecutarRecarga(RecargarCajeroRequest recargarCajeroRequest) {

        Result result = new Result();

        try {

            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("RecargarCajero");

            spQuery.registerStoredProcedureParameter("pIdUsuario", Integer.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter("pIdCajero", Integer.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter("oCodigo", Integer.class, ParameterMode.OUT);
            spQuery.registerStoredProcedureParameter("oMensaje", String.class, ParameterMode.OUT);

            spQuery.setParameter("pIdUsuario", recargarCajeroRequest.getIdUsuario());
            spQuery.setParameter("pIdCajero", recargarCajeroRequest.getIdCajero());

            spQuery.execute();

            Integer codigo = (Integer) spQuery.getOutputParameterValue("oCodigo");
            String mensaje = (String) spQuery.getOutputParameterValue("oMensaje");

            RecargarCajeroResponse recargarCajeroResponse = new RecargarCajeroResponse();

            recargarCajeroResponse.setCodigo(codigo);
            recargarCajeroResponse.setMensaje(mensaje);

            result.correct = codigo != null && codigo == 0;
            result.object = recargarCajeroResponse;

            if (!result.correct) {
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
