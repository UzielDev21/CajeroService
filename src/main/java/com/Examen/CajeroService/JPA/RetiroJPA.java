package com.Examen.CajeroService.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "RETIRO")
public class RetiroJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idretiro")
    private int idRetiro;

    @ManyToOne
    @JoinColumn(name = "idcuenta")
    public CuentasJPA cuentasJPA;

    @ManyToOne
    @JoinColumn(name = "idcajero")
    public CajeroJPA cajeroJPA;

    @Column(name = "monto")
    private BigDecimal monto;

    @Column(name = "estatus")
    private String estatus;

    public RetiroJPA() {
    }

    public RetiroJPA(int idRetiro, CuentasJPA cuentasJPA, CajeroJPA cajeroJPA, BigDecimal monto, String status) {
        this.idRetiro = idRetiro;
        this.cuentasJPA = cuentasJPA;
        this.cajeroJPA = cajeroJPA;
        this.monto = monto;
        this.estatus = estatus;
    }

    public int getIdRetiro() {
        return idRetiro;
    }

    public void setIdRetiro(int idRetiro) {
        this.idRetiro = idRetiro;
    }

    public CuentasJPA getCuentasJPA() {
        return cuentasJPA;
    }

    public void setCuentasJPA(CuentasJPA cuentasJPA) {
        this.cuentasJPA = cuentasJPA;
    }

    public CajeroJPA getCajeroJPA() {
        return cajeroJPA;
    }

    public void setCajeroJPA(CajeroJPA cajeroJPA) {
        this.cajeroJPA = cajeroJPA;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

}
