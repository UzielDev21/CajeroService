package com.Examen.CajeroService.DAO;

import com.Examen.CajeroService.JPA.CuentasJPA;
import com.Examen.CajeroService.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CuentasJPADAOImplementation implements ICuentasJPA {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Result GetById(int idCuenta) {

        Result result = new Result();

        try {
            CuentasJPA cuentasJPA = entityManager.find(
                    CuentasJPA.class,
                    idCuenta);
            result.object = cuentasJPA;
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetAll() {
        Result result = new Result();
        
        try {
            
            TypedQuery<CuentasJPA> cuentasJPA = entityManager.createQuery(
            "FROM CuentasJPA",
                    CuentasJPA.class);
            List<CuentasJPA> cuentas = cuentasJPA.getResultList();
            
            result.objects = (List<Object>)(List<?>)cuentas;
            result.correct = true;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
