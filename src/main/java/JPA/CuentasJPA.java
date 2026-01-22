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
@Table(name = "CUENTAS")
public class CuentasJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcuenta")
    private int idCuenta;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    public UsuarioJPA usuarioJPA;

    @ManyToOne
    @JoinColumn(name = "idtipocuenta")
    public TipoCuentaJPA tipoCuentaJPA;

    @Column(name = "saldo")
    private float saldo;

    @Column(name = "estado")
    private int estado;

    @Column(name = "nocuenta")
    private int noCuenta;

    @Column(name = "nip")
    private int nip;

    public CuentasJPA() {
    }

    public CuentasJPA(int idCuenta, UsuarioJPA usuarioJPA, TipoCuentaJPA tipoCuentaJPA, float saldo, int estado, int noCuenta, int nip) {
        this.idCuenta = idCuenta;
        this.usuarioJPA = usuarioJPA;
        this.tipoCuentaJPA = tipoCuentaJPA;
        this.saldo = saldo;
        this.estado = estado;
        this.noCuenta = noCuenta;
        this.nip = nip;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public UsuarioJPA getUsuarioJPA() {
        return usuarioJPA;
    }

    public void setUsuarioJPA(UsuarioJPA usuarioJPA) {
        this.usuarioJPA = usuarioJPA;
    }

    public TipoCuentaJPA getTipoCuentaJPA() {
        return tipoCuentaJPA;
    }

    public void setTipoCuentaJPA(TipoCuentaJPA tipoCuentaJPA) {
        this.tipoCuentaJPA = tipoCuentaJPA;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getNoCuenta() {
        return noCuenta;
    }

    public void setNoCuenta(int noCuenta) {
        this.noCuenta = noCuenta;
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }
}
