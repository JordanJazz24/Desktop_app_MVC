package SILAB.logic;

import SILAB.data.Data;
import SILAB.data.XmlPersister;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private static Service theInstance;

    public static Service instance(){
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }
    private Data data;

    private Service(){

        try {
            data = XmlPersister.instance().load(); // carga desde el xml
        }catch (Exception e){

            data = new Data();

        }

    }


    public void stop(){
        try {
            XmlPersister.instance().store(data);
        }catch (Exception e){
                System.out.println(e);
        }


    }


    //================= TIPOS DE INSTRUMENTO ============
    public void create(TipoInstrumento e) throws Exception{
        TipoInstrumento result = data.getTipos().stream()
                .filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result==null) data.getTipos().add(e);
        else throw new Exception("Tipo ya existe");

    }
    public void create(Instrumento e) throws Exception{
        Instrumento result = data.getInstrumentos().stream()
                .filter(i->i.getSerie().equals(e.getSerie())).findFirst().orElse(null);
        if (result==null){ data.getInstrumentos().add(e);
            TipoInstrumento tipoActual = read(e.tipoInstrumento);
            tipoActual.getInstrumentos().add(e);
        }
        else throw new Exception("INSTURMENTO ya existe");

    }



    public TipoInstrumento read(TipoInstrumento e) throws Exception{
        TipoInstrumento result = data.getTipos().stream()
                .filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Tipo no existe");

    }

    public Instrumento read(Instrumento e) throws Exception{
        Instrumento result = data.getInstrumentos().stream()
                .filter(i->i.getSerie().equals(e.getSerie())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Instrumento no existe");
    }

    public void update(TipoInstrumento e) throws Exception{
        TipoInstrumento result;
        try{
            if(findInstrumentsForTypes(e).size()>0) throw new Exception("");
            result = this.read(e);
            data.getTipos().remove(result);
            data.getTipos().add(e);
        }catch (Exception ex) {
            throw new Exception("No se puede actualizar, tiene Instrumentos asociados");
        }
    }
    public List<Instrumento> findInstrumentsForTypes(TipoInstrumento TipoInstrumento) {
        return data.getInstrumentos().stream() // instrumentos asociados al tipo
                .filter(instrumento -> instrumento.getTipoInstrumento().equals(TipoInstrumento))
                .collect(Collectors.toList());
    }

    public void update(Instrumento e) throws Exception{
        Instrumento result;
        try{
            if(findCalibrationsForInstrument(e).size()>0) throw new Exception("");
            result = this.read(e);
            data.getInstrumentos().remove(result);
            data.getInstrumentos().add(e);

        }catch (Exception ex) {
                throw new Exception("No se puede actualizar, tiene calibraciones asociadas");
        }
    }

    public void delete(TipoInstrumento e) throws Exception{
        TipoInstrumento tipoActual = read(e);
        if (tipoActual.getInstrumentos().size()>0) throw new Exception("Tipo tiene instrumentos asociados");
        data.getTipos().remove(e);
     }
    public void delete(Instrumento e) throws Exception{
        if (findCalibrationsForInstrument(e).size()>0) throw new Exception("Instrumento tiene calibraciones asociadas");
        TipoInstrumento tipoActual = read(e.tipoInstrumento);
        data.getInstrumentos().remove(e);
        tipoActual.getInstrumentos().remove(e); // elimina el instrumento de la lista de tipoInstrumento
     }


    public List<TipoInstrumento> search(TipoInstrumento e){
        return data.getTipos().stream()
                .filter(i->i.getNombre().contains(e.getNombre()))
                .sorted(Comparator.comparing(TipoInstrumento::getNombre))
                .collect(Collectors.toList());

    }

    public List<Instrumento> search(Instrumento e){
        return  data.getInstrumentos().stream()
                .filter(i->i.getSerie().contains(e.getSerie()))
                .sorted(Comparator.comparing(Instrumento::getSerie))
                .collect(Collectors.toList());

    }
    public List<Calibracion> findCalibrationsForInstrument(Instrumento instrumento) {
        // Filtra la lista de calibraciones para encontrar las calibraciones asociadas al instrumento
        return data.getCalibraciones().stream()
                .filter(calibracion -> calibracion.getInstrumento().equals(instrumento))
                .collect(Collectors.toList());
    }


    // CALIBREACIOENS
    public void create(Calibracion e) throws Exception {
        if (data.getCalibraciones().contains(e)) {
            throw new Exception("Calibración ya existe");
        } else {
            e.setMediciones(createMediciones(e.getInstrumento(),e.getMediciones().size()));
            data.getCalibraciones().add(e);
        }
    }



    public Calibracion read(Calibracion e) throws Exception{
        if (data.getCalibraciones().contains(e)) { // compara todo el objeto calibracion con la lista de calibraciones
            return e;
        } else {
            throw new Exception("Calibración si existe");

        }
    }

    public void update(Calibracion e) throws Exception{
        Calibracion result;
        try{
            result = this.read2(e);
            data.getCalibraciones().remove(result);
            data.getCalibraciones().add(e);

        }catch (Exception ex) {
            throw new Exception("Calibracion no existe");
        }
    }

    private Calibracion read2(Calibracion e) { //si la calirbacion exiteste en el intrumento asociado
        Instrumento instrumento = e.getInstrumento();
        List<Calibracion> listaDelInstrumentoSeleccionado = findCalibrationsForInstrument(instrumento);
        return listaDelInstrumentoSeleccionado.stream()
                .filter(i -> i.getNumCalibra().equals(e.getNumCalibra()))
                .findFirst().orElse(null);

    }

    public void delete(Calibracion e) throws Exception{
        if (e.getMediciones().size()>0) throw new Exception ("Calibracion tiene mediciones asociadas");
        data.getCalibraciones().remove(e); // elimina la calibracion de la lista de calibraciones

    }

    public List<Calibracion> search(Calibracion e){
        return data.getCalibraciones().stream()
                .filter(i->i.getNumCalibra().contains(e.getNumCalibra()))
                .sorted(Comparator.comparing(Calibracion::getNumCalibra))
                .collect(Collectors.toList());

    }
    public List<Calibracion> search2(Calibracion e) {
        List<Calibracion> listaDelInstrumentoSeleccionado = findCalibrationsForInstrument(e.getInstrumento());

        return listaDelInstrumentoSeleccionado.stream()
                .filter(i -> i.getFecha().contains(e.getFecha()))
                .sorted(Comparator.comparing(Calibracion::getFecha))
                .collect(Collectors.toList());
    }


    public List<Medicion> createMediciones(Instrumento instrumento, int cantidadMediciones) {
        int minimo = instrumento.getMinimo();
        int maximo = instrumento.getMaximo();

        List<Medicion> mediciones = new ArrayList<>(cantidadMediciones);
        if (cantidadMediciones > 0) {
            int paso = (maximo - minimo) / cantidadMediciones;

            for (int i = 0; i < cantidadMediciones; i++) {
                int valor = minimo + i * paso;
                Medicion medicion = new Medicion();
                medicion.setNumero(String.valueOf(i+1));
                medicion.setReferencia(String.valueOf(valor));
                medicion.setLectura("");
                mediciones.add(medicion);
            }
        }

        return mediciones;
    }

    public void deleteMedicionForCalibracion(Calibracion current, Medicion medicionActual) {
     try    {
         current.getMediciones().remove(medicionActual);
        }catch (Exception e){
         System.out.println("no se boroo");

    }}

    public List<Medicion> listaMediciones(Calibracion current) {
        return current.getMediciones();
    }
}
