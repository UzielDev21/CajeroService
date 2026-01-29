package com.Examen.CajeroService.RestController;

import com.Examen.CajeroService.DAO.CuentasJPADAOImplementation;
import com.Examen.CajeroService.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CuentasRestController {

    @Autowired
    private CuentasJPADAOImplementation cuentasJPADAOImplementation;

    @GetMapping("/cuentas")
    public ResponseEntity GetAll() {

        Result result = new Result();

        try {
            result = cuentasJPADAOImplementation.GetAll();
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

    @GetMapping("/cuentas/{idCuenta}")
    public ResponseEntity GetById(@PathVariable("idCuenta") int idCuenta) {

        Result result = new Result();

        try {
            if (idCuenta != 0) {
                result = cuentasJPADAOImplementation.GetById(idCuenta);
                result.correct = true;
                result.status = 200;
            } else {
                result.correct = false;
                result.errorMessage = "No se encuentra la cuenta";
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
