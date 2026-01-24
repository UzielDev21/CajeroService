package com.Examen.CajeroService.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccesoCuentaResponse {

    private Integer idCuenta;
    
    @JsonIgnore
    private Integer codigo;
    
    private String mensaje;

    public AccesoCuentaResponse() {
    }

    public AccesoCuentaResponse(Integer idCuenta, Integer codigo, String mensaje) {
        this.idCuenta = idCuenta;
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
