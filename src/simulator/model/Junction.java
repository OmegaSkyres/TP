package simulator.model;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class Junction extends SimulatedObject {
    protected List<Road> listaCarreterasEntrantes; //Lista de Carreteras que entran al Cruce
    protected Map<Junction,Road> mapaCarreterasSalientes; //Mapa para seleccionar que carretera r llega al cruce j;
    protected List<List<Vehicle>> listaDeColas; //Lista de colas para las carreteras Entrantes.
    protected int indiceSemaforo; //Indice del semaforo de la carretera Entrante -1 indica rojo



    Junction(String id) {
        super(id);
    }

    @Override
    protected void advance(int time) {

    }

    @Override
    public JSONObject report() {
        return null;
    }
}
