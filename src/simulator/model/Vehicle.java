package simulator.model;

import org.json.JSONObject;
import simulator.exceptions.WrongValuesException;
import simulator.model.SimulatedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.min;


public class Vehicle<Junction> extends SimulatedObject {
    protected List<Junction> itenerario;
    protected int velocidadMaxima; // velocidad maxima
    protected int velocidadActual; // velocidad actual
    protected VehicleStatus estado; //estado
    protected Road carretera; //carretera en la que se encuentra el vehiculo
    protected int localizacion; //distancia desde que comenzo la carretera
    protected int gradoContaminacion; //numero entre 0 y 10
    protected int contaminacionTotal; //total emitido
    protected int kilometraje; // distancia recorrida

    public Vehicle(String id, int velocidadMaxima, int contClass, List<Junction> itinerario) throws WrongValuesException { //ConClass es grado de contaminacion
        super(id);
        Collections.unmodifiableList(new ArrayList<>(itinerario)); // creamos la copia aqui??
        if(!checkValores(velocidadMaxima,gradoContaminacion,itinerario)){
            throw new WrongValuesException("Los valores introducidos son erroneos");
        }
        else{

        }

    }

    @Override
    protected void advance(int time) {
        if(estado == VehicleStatus.TRAVELING){
            localizacion = min((getLocalizacion() + getVelocidadActual()),carretera.getLength());
            if(getLocalizacion() >= carretera.getLength()) {
                int anterior = getLocalizacion();
                localizacion = carretera.getLength();
                kilometraje = getKilometraje() - (anterior - getLocalizacion());
            }
        }

    }

    @Override
    public JSONObject report() {
        return null;
    }

    protected boolean checkValores(int velocidadMaxima, int contClass, List<Junction> itinerario){
        if(velocidadMaxima > 0 && contClass > 0 && contClass < 10 && itinerario.size() >= 2) return true;
        else return false;
    }

    protected void setSpeed(int s){
        velocidadActual = s;
    }

    public int getContaminacionTotal() {
        return contaminacionTotal;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public int getGradoContaminacion() {
        return gradoContaminacion;
    }

    public int getVelocidadActual() {
        return velocidadActual;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public int getLocalizacion() {
        return localizacion;
    }

    protected void setContaminationClass(int c){
        gradoContaminacion = c;
    }

    protected void

}
