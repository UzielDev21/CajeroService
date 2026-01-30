package com.Examen.CajeroService.Service;

import com.Examen.CajeroService.DTO.ConsultarSaldoUsuarioResponse;
import com.Examen.CajeroService.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ConsultarSaldoUsuarioService {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Result ejecutarConsultaSaldoUsuario(Integer idCuenta){
        
        Result result = new Result();
        
        try {
            
            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("ConsultarSaldoUsuario");
            
            spQuery.registerStoredProcedureParameter("pIdCuenta", Integer.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter("pCursor", Void.class, ParameterMode.REF_CURSOR);
            
            spQuery.setParameter("pIdCuenta", idCuenta);
            spQuery.execute();
            
            List<Object[]> rows = spQuery.getResultList();
            
            if (rows.isEmpty()) {
                result.correct = false;
                result.errorMessage = "No se encontro info de la cuenta";
                return result;
            }
            Object[] row = rows.get(0);
            
            ConsultarSaldoUsuarioResponse consultarSaldoUsuarioResponse = new ConsultarSaldoUsuarioResponse();
            
            consultarSaldoUsuarioResponse.setUsername((String) row[0]);
            consultarSaldoUsuarioResponse.setNoCuenta(((Number) row[1]).intValue());
            consultarSaldoUsuarioResponse.setSaldo((BigDecimal) row[2]);
            
            result.correct = true;
            result.object = consultarSaldoUsuarioResponse;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
    
}
