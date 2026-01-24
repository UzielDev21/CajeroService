package com.Examen.CajeroService.RestController;

import com.Examen.CajeroService.DTO.AccesoCuentaRequest;
import com.Examen.CajeroService.DTO.AccesoCuentaResponse;
import com.Examen.CajeroService.DTO.ValidarCajeroRequest;
import com.Examen.CajeroService.JPA.Result;
import com.Examen.CajeroService.UserDetailsJPAService.AccesoCuentaService;
import com.Examen.CajeroService.UserDetailsJPAService.ValidarCajeroService;
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
    public ResponseEntity ValidarCajero(@RequestBody ValidarCajeroRequest validarCajeroRequest){
        
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
    public ResponseEntity ValidarDenominacion(){
        
        Result result = new Result();
        
        try {
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        return ResponseEntity.status(result.status).body(result);
    }
    
}
