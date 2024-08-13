package SILAB.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
public class Medicion{
    @XmlID
    String numero;
    String referencia;
    String lectura;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getLectura() {
        return lectura;
    }

    public void setLectura(String lectura) {
        this.lectura = lectura;
    }

    public Medicion() {
        this.numero = "";
        this.referencia = "";
        this.lectura = "";

    }

    public Medicion(String numero, String referencia, String lectura) {
        this.numero = numero;
        this.referencia = referencia;
        this.lectura = lectura;
    }
}