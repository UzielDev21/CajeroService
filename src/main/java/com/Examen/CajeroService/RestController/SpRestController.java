package com.Examen.CajeroService.RestController;

import com.Examen.CajeroService.DTO.AccesoCuentaRequest;
import com.Examen.CajeroService.DTO.AccesoCuentaResponse;
import com.Examen.CajeroService.DTO.EjecutarRetiroRequest;
import com.Examen.CajeroService.DTO.RecargarCajeroRequest;
import com.Examen.CajeroService.DTO.ValidarCajeroRequest;
import com.Examen.CajeroService.DTO.ValidarDenominacionRequest;
import com.Examen.CajeroService.DTO.ValidarSaldoCuentaRequest;
import com.Examen.CajeroService.JPA.Result;
import com.Examen.CajeroService.Service.AccesoCuentaService;
import com.Examen.CajeroService.Service.ConsultarSaldoCajeroService;
import com.Examen.CajeroService.Service.ConsultarSaldoUsuarioService;
import com.Examen.CajeroService.Service.EjecutarRetiroService;
import com.Examen.CajeroService.Service.RecargaCajeroService;
import com.Examen.CajeroService.Service.ValidarCajeroService;
import com.Examen.CajeroService.Service.ValidarDenominacionService;
import com.Examen.CajeroService.Service.ValidarSaldoCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private ConsultarSaldoUsuarioService consultarSaldoUsuarioService;

    @Autowired
    private ConsultarSaldoCajeroService consultarSaldoCajeroService;

    @Autowired
    private RecargaCajeroService recargaCajeroService;

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

    @PostMapping("/recargar-cajero")
    public ResponseEntity EjecutarRecargaCajero(@RequestBody RecargarCajeroRequest recargarCajeroRequest) {

        Result result = new Result();

        try {

            result = recargaCajeroService.ejecutarRecarga(recargarCajeroRequest);

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

    @GetMapping("/consultar-saldo-usuario/{idCuenta}")
    public ResponseEntity consultarSaldoUsuario(@PathVariable("idCuenta") int idCuenta) {

        Result result = new Result();

        try {

            if (idCuenta <= 0) {
                result.correct = false;
                result.errorMessage = "Id de cuenta invalido";
                result.status = 400;
                return ResponseEntity.status(result.status).body(result);
            }

            result = consultarSaldoUsuarioService.ejecutarConsultaSaldoUsuario(idCuenta);

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

    @GetMapping("/consultar-saldo-cajero/{idCajero}")
    public ResponseEntity ConsultaSaldoCajero(@PathVariable("idCajero") int idCajero) {

        Result result = new Result();

        try {

            if (idCajero <= 0) {
                result.correct = false;
                result.errorMessage = "Id de cajero invalido";
                result.status = 400;
                return ResponseEntity.status(result.status).body(result);
            }

            result = consultarSaldoCajeroService.ejecutarConsultaSaldoCajero(idCajero);

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
