package simulator.model;

import org.json.JSONObject;
import simulator.exceptions.WrongValuesContamination;
import simulator.exceptions.WrongValuesVehicle;
import simulator.exceptions.WrongValuesWeather;

import java.util.Comparator;
import java.util.List;

public class Road extends SimulatedObject {
    protected int longitud; // Longitud de la carretera
    protected int velocidadMaxima; // Velocidad maxima
    protected int limiteVelocidad; // Limite de velocidad en una carretera
    protected int alarmaContaminacion; //Indica el Limite de contaminacion
    protected Weather condicionAmbiental; //Enumerado que indica las condiciones para adaptar los demas parametros.
    protected int contaminacionTotal; //Contaminacion total acumulada en la carretera.
    protected Junction cruceOrigen; // Cruce del que parte la carretera
    protected Junction cruceDestino; // Cruce al que llega la carretera
    protected List<Vehicle> vehiculos; // Lista ordenada de vehÃ­culos en lacarretera (ordenada por localizacion)
    protected Comparator<Vehicle> comparadorVehiculo; // Orden entre vehiculos

    Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
        super(id);
        longitud = length;
        velocidadMaxima = maxSpeed;
        cruceOrigen = srcJunc;
        cruceDestino = destJunc;
        cruceOrigen.mapaCarreterasSalientes.put(srcJunc,this);
        cruceDestino.mapaCarreterasSalientes.put(destJunc,this);
}

    @Override
    protected void advance(int time) {
        reduceTotalContamination();
        updateSpeedLimit();
        for(Vehicle v : vehiculos){
            calculateVehicleSpeed(v);
            v.advance(time);
        }
        vehiculos.sort(comparadorVehiculo);

    }

    @Override
    public JSONObject report() {
        return null;
    }

    public int getLength(){
        return longitud;
    }

    private void enter(Vehicle v) throws WrongValuesVehicle {
        if(v.localizacion == 0 && v.velocidadActual == 0){
            vehiculos.add(v);
        }
        else throw new WrongValuesVehicle("Los atributos de este Vehiculo son Erroneos");
    }

    private void exit(Vehicle v){
        vehiculos.remove(v);
    }

    private void setWeather(Weather w) throws WrongValuesWeather {
        if(w == null){
            throw new WrongValuesWeather("El valor de las Condicioones Atmosfericas es Erroneo");
        }
        else{
            condicionAmbiental = w;
        }
    }

    private void addContamination(int c) throws WrongValuesContamination {
        if(c < 0){
            throw new WrongValuesContamination("El valor de la contaminacion es Erroneo");
        }
        contaminacionTotal += c;
    }

    private abstract void reduceTotalContamination();
    private abstract void updateSpeedLimit();
    private abstract int calculateVehicleSpeed(Vehicle v);
}
