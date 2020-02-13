package simulator.model;

import org.json.JSONObject;

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
        cruceOrigen
    }

    @Override
    protected void advance(int time) {

    }

    @Override
    public JSONObject report() {
        return null;
    }
}
