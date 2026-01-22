package JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CAJERO")
public class CajeroJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcajero")
    private int idCajero;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "estado")
    private int estado;

    public CajeroJPA() {
    }

    public CajeroJPA(int idCajero, String ubicacion, int estado) {
        this.idCajero = idCajero;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    public int getIdCajero() {
        return idCajero;
    }

    public void setIdCajero(int idCajero) {
        this.idCajero = idCajero;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
