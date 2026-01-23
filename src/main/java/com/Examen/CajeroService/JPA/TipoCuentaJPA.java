package com.Examen.CajeroService.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TIPOCUENTA")
public class TipoCuentaJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipocuenta")
    private int idTipoCuenta;
    
    @Column(name = "tipocuenta")
    private String tipoCuenta;
    
    public TipoCuentaJPA(){
    }
    
    public TipoCuentaJPA(int idTipoCuenta, String tipoCuenta){
        this.idTipoCuenta = idTipoCuenta;
        this.tipoCuenta = tipoCuenta;
    }

    public int getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(int idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }   
}
