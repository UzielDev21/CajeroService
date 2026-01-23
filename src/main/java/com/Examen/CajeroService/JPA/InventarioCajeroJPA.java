package com.Examen.CajeroService.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "INVENTARIOCAJERO")
public class InventarioCajeroJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idinventario")
    private int idInventario;

    @ManyToOne
    @JoinColumn(name = "idcajero")
    public CajeroJPA cajeroJPA;

    @ManyToOne
    @JoinColumn(name = "iddenominacion")
    public DenominacionJPA denominacionJPA;

    @Column(name = "cantidad")
    private float cantidad;

    public InventarioCajeroJPA() {
    }

    public InventarioCajeroJPA(int idInventario, CajeroJPA cajeroJPA, DenominacionJPA denominacionJPA, float cantidad) {
        this.idInventario = idInventario;
        this.cajeroJPA = cajeroJPA;
        this.denominacionJPA = denominacionJPA;
        this.cantidad = cantidad;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public CajeroJPA getCajeroJPA() {
        return cajeroJPA;
    }

    public void setCajeroJPA(CajeroJPA cajeroJPA) {
        this.cajeroJPA = cajeroJPA;
    }

    public DenominacionJPA getDenominacionJPA() {
        return denominacionJPA;
    }

    public void setDenominacionJPA(DenominacionJPA denominacionJPA) {
        this.denominacionJPA = denominacionJPA;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

}
