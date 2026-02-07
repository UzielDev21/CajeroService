package com.Examen.CajeroService.Service;

import com.Examen.CajeroService.DTO.ConsultarSaldoCajeroResponse;
import com.Examen.CajeroService.DTO.DenominacionInventario;
import com.Examen.CajeroService.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ConsultarSaldoCajeroService {

    @PersistenceContext
    private EntityManager entityManager;

    public Result ejecutarConsultaSaldoCajero(Integer idCajero) {

        Result result = new Result();

        try {

            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("ConsultarSaldoCajero");

            spQuery.registerStoredProcedureParameter("pIdCajero", Integer.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter("pCursorSaldoTotal", void.class, ParameterMode.REF_CURSOR);
            spQuery.registerStoredProcedureParameter("pCursorDenominacionInventario", void.class, ParameterMode.REF_CURSOR);

            spQuery.setParameter(("pIdCajero"), idCajero);
            spQuery.execute();

            List<Object[]> saldoRows = spQuery.getResultList();

            if (saldoRows.isEmpty()) {
                result.correct = false;
                result.errorMessage = "No se encontro la informacion del cajero";
                return result;
            }

            Object[] saldoRow = saldoRows.get(0);

            ConsultarSaldoCajeroResponse consultarSaldoCajeroResponse = new ConsultarSaldoCajeroResponse();

            consultarSaldoCajeroResponse.setUbicacion((String) saldoRow[0]);
            consultarSaldoCajeroResponse.setTotal((BigDecimal) saldoRow[1]);
            consultarSaldoCajeroResponse.setIsService((String) saldoRow[2]);

            spQuery.hasMoreResults();
            List<Object[]> denomRows = spQuery.getResultList();

            List<DenominacionInventario> lista = new ArrayList<>();

            for (Object[] row : denomRows) {
                DenominacionInventario denominacionInventario = new DenominacionInventario();

                denominacionInventario.setIdInventario(((Number) row[0]).intValue());
                denominacionInventario.setIdDenominacion(((Number) row[1]).intValue());
                denominacionInventario.setValor(new BigDecimal(row[2].toString()));
                denominacionInventario.setCantidad(((Number) row[3]).intValue());
                lista.add(denominacionInventario);
            }
            consultarSaldoCajeroResponse.setDenominaciones(lista);

            result.correct = true;
            result.object = consultarSaldoCajeroResponse;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
