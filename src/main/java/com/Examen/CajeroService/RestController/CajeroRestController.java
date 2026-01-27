package com.Examen.CajeroService.RestController;

import com.Examen.CajeroService.DAO.CajeroJPADAOImplementation;
import com.Examen.CajeroService.DAO.ICajeroJPA;
import com.Examen.CajeroService.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CajeroRestController {

    @Autowired
    private CajeroJPADAOImplementation cajeroJPADAOImplementation;

    @GetMapping("/cajeros")
    public ResponseEntity GetAll() {
        Result result = new Result();

        try {

            result = cajeroJPADAOImplementation.GetAll();
            result.correct = true;
            result.status = 200;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        return ResponseEntity.status(result.status).body(result);
    }

    @GetMapping("/cajeros/{idCajero}")
    public ResponseEntity GetById(@PathVariable("idCajero") int idCajero) {

        Result result = new Result();

        try {
            if (idCajero != 0) {
                result = cajeroJPADAOImplementation.GetById(idCajero);
                result.correct = true;
                result.status = 200;
            } else {
                result.correct = false;
                result.errorMessage = "No se encuentra el cajero";
                result.status = 400;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        return ResponseEntity.status(result.status).body(result);
    }

}
