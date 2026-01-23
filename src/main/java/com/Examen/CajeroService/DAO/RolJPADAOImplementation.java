package com.Examen.CajeroService.DAO;

import com.Examen.CajeroService.JPA.Result;
import com.Examen.CajeroService.JPA.RolJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RolJPADAOImplementation implements IRolJPA {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {
            TypedQuery<RolJPA> rolesJPA = entityManager.createQuery(
            "FROM RolJPA", RolJPA.class);
            List<RolJPA> roles = rolesJPA.getResultList();
            
            result.objects = (List<Object>)(List<?>)roles;
            result.correct = true;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
