package JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DENOMINACION")
public class DenominacionJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddenominacion")
    private int idDenominacion;

    @Column(name = "valor")
    private int valor;

    @Column(name = "tipo")
    private String tipo;

    public DenominacionJPA() {
    }

    public DenominacionJPA(int idDenominacion, int valor, String tipo) {
        this.idDenominacion = idDenominacion;
        this.valor = valor;
        this.tipo = tipo;
    }

    public int getIdDenominacion() {
        return idDenominacion;
    }

    public void setIdDenominacion(int idDenominacion) {
        this.idDenominacion = idDenominacion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
