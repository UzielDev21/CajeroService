package com.Examen.CajeroService.DAO;

import com.Examen.CajeroService.JPA.CajeroJPA;
import com.Examen.CajeroService.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CajeroJPADAOImplementation implements ICajeroJPA {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {
            TypedQuery<CajeroJPA> cajerosJPA = entityManager.createQuery(
                    "FROM CajeroJPA", CajeroJPA.class);
            List<CajeroJPA> cajeros = cajerosJPA.getResultList();

            result.objects = (List<Object>) (List<?>) cajeros;
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetById(int idCajero) {
        Result result = new Result();

        try {

            CajeroJPA cajeroJPA = entityManager.find(
                    CajeroJPA.class, idCajero);
            result.object = cajeroJPA;
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
