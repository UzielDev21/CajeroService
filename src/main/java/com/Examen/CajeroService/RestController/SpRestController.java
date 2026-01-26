package com.Examen.CajeroService.RestController;

import com.Examen.CajeroService.DTO.AccesoCuentaRequest;
import com.Examen.CajeroService.DTO.AccesoCuentaResponse;
import com.Examen.CajeroService.DTO.EjecutarRetiroRequest;
import com.Examen.CajeroService.DTO.ValidarCajeroRequest;
import com.Examen.CajeroService.DTO.ValidarDenominacionRequest;
import com.Examen.CajeroService.DTO.ValidarSaldoCuentaRequest;
import com.Examen.CajeroService.JPA.Result;
import com.Examen.CajeroService.UserDetailsJPAService.AccesoCuentaService;
import com.Examen.CajeroService.UserDetailsJPAService.EjecutarRetiroService;
import com.Examen.CajeroService.UserDetailsJPAService.ValidarCajeroService;
import com.Examen.CajeroService.UserDetailsJPAService.ValidarDenominacionService;
import com.Examen.CajeroService.UserDetailsJPAService.ValidarSaldoCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class SpRestController {

    @Autowired
    private AccesoCuentaService accesoCuentaService;

    @Autowired
    private ValidarCajeroService validarCajeroService;

    @Autowired
    private ValidarSaldoCuentaService validarSaldoCuentaService;

    @Autowired
    private ValidarDenominacionService validarDenominacionService;

    @Autowired
    private EjecutarRetiroService ejecutarRetiroService;

    @PostMapping("/acceso-cuenta")
    public ResponseEntity AccesoCuenta(@RequestBody AccesoCuentaRequest accesoCuentaRequest) {

        Result result = new Result();

        try {

            result = accesoCuentaService.ejecutarAccesoCuenta(accesoCuentaRequest);

            if (result.correct) {

                result.status = 200;
            } else {
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

    @PostMapping("/validar-cajero")
    public ResponseEntity ValidarCajero(@RequestBody ValidarCajeroRequest validarCajeroRequest) {

        Result result = new Result();

        try {

            result = validarCajeroService.ejecutarValidacionCajero(validarCajeroRequest);

            if (result.correct) {
                result.status = 200;
            } else {
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

    @PostMapping("/validar-denominacion")
    public ResponseEntity ValidarDenominacion(@RequestBody ValidarDenominacionRequest validarDenominacionRequest) {

        Result result = new Result();

        try {

            result = validarDenominacionService.ejecutarValidacionDenominacion(validarDenominacionRequest);

            if (result.correct) {
                result.status = 200;
            } else {
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

    @PostMapping("/validar-saldoCuenta")
    public ResponseEntity ValidarSaldoCuenta(@RequestBody ValidarSaldoCuentaRequest validarSaldoCuentaRequest) {

        Result result = new Result();

        try {

            result = validarSaldoCuentaService.ejecutarValidacionSaldoCuenta(validarSaldoCuentaRequest);

            if (result.correct) {
                result.status = 200;
            } else {
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

    @PostMapping("/retiro")
    public ResponseEntity EjecutarRetiro(@RequestBody EjecutarRetiroRequest ejecutarRetiroRequest) {

        Result result = new Result();

        try {

            result = ejecutarRetiroService.ejecutarRetiroService(ejecutarRetiroRequest);

            if (result.correct) {
                result.status = 200;
            } else {
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
