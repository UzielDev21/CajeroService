package com.Examen.CajeroService.Service;

import com.Examen.CajeroService.DTO.ConsultarSaldoCajeroResponse;
import com.Examen.CajeroService.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ConsultarSaldoCajeroService {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Result ejecutarConsultaSaldoCajero(Integer idCajero){
        
        Result result = new Result();
        
        try {
            
            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("ConsultarSaldoCajero");
            
            spQuery.registerStoredProcedureParameter("pIdCajero", Integer.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter("pCursor", void.class, ParameterMode.REF_CURSOR);
            
            spQuery.setParameter(("pIdCajero"), idCajero);
            spQuery.execute();
            
            List<Object[]> rows = spQuery.getResultList();
            
            if (rows.isEmpty()) {
                result.correct = false;
                result.errorMessage = "No se encontro la informacion del cajero";
                return result;
            }
            
            Object[] row = rows.get(0);
            
            ConsultarSaldoCajeroResponse consultarSaldoCajeroResponse = new ConsultarSaldoCajeroResponse();
            
            consultarSaldoCajeroResponse.setUbicacion((String) row[0]);
            consultarSaldoCajeroResponse.setTotal((BigDecimal) row[1]);
            consultarSaldoCajeroResponse.setIsService((String) row[2]);
            
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
