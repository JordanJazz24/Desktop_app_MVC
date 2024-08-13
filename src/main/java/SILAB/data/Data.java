package SILAB.data;

import SILAB.logic.Calibracion;
import SILAB.logic.Instrumento;
import SILAB.logic.Medicion;
import SILAB.logic.TipoInstrumento;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
public class Data {

    @XmlElementWrapper(name = "tipos")
    @XmlElement(name = "tipos")
    private List<TipoInstrumento> tipos;
    @XmlElementWrapper(name = "instu")
    @XmlElement(name = "instu")
    private List<Instrumento> instrumentos;

    @XmlElementWrapper(name = "calibraciones")
    @XmlElement(name = "calibraciones")
    private List<Calibracion> calibraciones;

    @XmlElementWrapper(name = "mediciones")
    @XmlElement(name = "mediciones")
    private List<Medicion> mediciones;

    public void setTipos(List<TipoInstrumento> tipos) {
        this.tipos = tipos;
    }

    public void setInstrumentos(List<Instrumento> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public List<Medicion> getMediciones() {
        return mediciones;
    }

    public void setMediciones(List<Medicion> mediciones) {
        this.mediciones = mediciones;
    }

    public List<Calibracion> getCalibraciones() {
        return calibraciones;
    }

    public void setCalibraciones(List<Calibracion> calibraciones) {
        this.calibraciones = calibraciones;
    }

    public List<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    public Data() {
        tipos = new ArrayList<>();
    calibraciones = new ArrayList<>();

       tipos.add(new TipoInstrumento("TER","Termómetro","Grados Celcius") );
       tipos.add(new TipoInstrumento("BAR","Barómetro","PSI") );
       tipos.add(new TipoInstrumento("BAL","Balanza","Gramos") );
       tipos.add(new TipoInstrumento("Vol","Voltometro","Voltios") );

        instrumentos = new ArrayList<>();
    }

    public List<TipoInstrumento> getTipos() {
        return tipos;
    }
 }
