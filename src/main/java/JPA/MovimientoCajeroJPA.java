package JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MOVIMIENTOCAJERO")
public class MovimientoCajeroJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmovimiento")
    private int idMovimiento;

    @ManyToOne
    @JoinColumn(name = "idcajero")
    public CajeroJPA cajeroJPA;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    public UsuarioJPA usuaioJPA;

    @Column(name = "tipomovimiento")
    private String tipoMovimiento;

    public MovimientoCajeroJPA() {
    }

    public MovimientoCajeroJPA(int idMovimiento, CajeroJPA cajeroJPA, UsuarioJPA usuarioJPA, String tipoMovimiento) {
        this.idMovimiento = idMovimiento;
        this.cajeroJPA = cajeroJPA;
        this.usuaioJPA = usuarioJPA;
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public CajeroJPA getCajeroJPA() {
        return cajeroJPA;
    }

    public void setCajeroJPA(CajeroJPA cajeroJPA) {
        this.cajeroJPA = cajeroJPA;
    }

    public UsuarioJPA getUsuaioJPA() {
        return usuaioJPA;
    }

    public void setUsuaioJPA(UsuarioJPA usuaioJPA) {
        this.usuaioJPA = usuaioJPA;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

}
