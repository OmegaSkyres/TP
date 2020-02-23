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

    public Vehicle(String id, int velocidadMaxima, int contClass, List<Junction> itinerario) throws WrongValuesException { //Preguntar que es contClass
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
            localizacion = min((localizacion + velocidadActual),carretera.getLength());
            if(localizacion >= carretera.getLength()) {
                int anterior = localizacion;
                localizacion = carretera.getLength();
                kilometraje = kilometraje - (anterior - localizacion);
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

    protected void setContaminationClass(int c){
        gradoContaminacion = c;
    }

}
