package simulator.model;

import org.json.JSONObject;
import simulator.exceptions.WrongValuesException;
import simulator.exceptions.WrongValuesVehicle;
import simulator.model.SimulatedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.min;


public class Vehicle<Junction> extends SimulatedObject {
    protected List<Junction> itinerario;
    protected int lastPositionItenerary;
    protected int velocidadMaxima; // velocidad maxima
    protected int velocidadActual; // velocidad actual
    protected VehicleStatus estado; //estado
    protected Road carretera; //carretera en la que se encuentra el vehiculo
    protected int localizacion; //distancia desde que comenzo la carretera
    protected int gradoContaminacion; //numero entre 0 y 10
    protected int contaminacionTotal; //total emitido
    protected int kilometraje; // distancia recorrida


    protected Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary) throws WrongValuesException { //ConClass es grado de contaminacion
        super(id);
        if(!checkValores(velocidadMaxima,gradoContaminacion,itinerario)){
            throw new WrongValuesException("Los valores introducidos son erroneos");
        }
        else{
            velocidadMaxima = maxSpeed;
            gradoContaminacion = contClass;
            itinerario = Collections.unmodifiableList(new ArrayList<>(itinerary));
            velocidadActual = 0;
            estado = VehicleStatus.PENDING;
            localizacion = 0;
            kilometraje = 0;
            contaminacionTotal = 0;
            lastPositionItenerary = 0;

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
        JSONObject report = new JSONObject();
        report.put("id",this._id);
        report.put("speed",this.velocidadActual);
        report.put("distance",this.kilometraje);
        report.put("co2",this.contaminacionTotal);
        report.put("class",this.gradoContaminacion);
        report.put("status",this.estado);
        if(estado != VehicleStatus.PENDING || estado != VehicleStatus.ARRIVED){
            report.put("road",this.carretera.getId());
            report.put("location",this.localizacion);
        }
        return report;
    }

    void moveToNextRoad() throws WrongValuesVehicle {
        if(this.estado != VehicleStatus.PENDING || this.estado != VehicleStatus.WAITING){
            throw new WrongValuesVehicle("El estado del Vehiculo no es el correcto");
        }
        else{
            if(this.estado == VehicleStatus.WAITING) {
                carretera.exit(this);
            }
            if(lastPositionItenerary + 1 == itinerario.size()){
                estado = VehicleStatus.ARRIVED;
                velocidadActual = 0;
            }
            else{
                itinerario.get(lastPositionItenerary).roadTo(itinerario.get(lastPositionItenerary + 1));
            }


        }

    }

    protected boolean checkValores(int velocidadMaxima, int contClass, List<Junction> itinerario){
        if(velocidadMaxima > 0 && contClass > 0 && contClass < 10 && itinerario.size() >= 2) return true;
        else return false;
    }

    public void setSpeed(int s){
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

    public void setContaminationClass(int c){
        gradoContaminacion = c;
    }


}
