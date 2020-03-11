package simulator.model;

import org.json.JSONObject;
import simulator.exceptions.WrongValuesException;
import simulator.exceptions.WrongValuesIncommingRoad;
import simulator.exceptions.WrongValuesOutGoingRoad;

import java.util.*;

public class Junction extends SimulatedObject {
    protected List<Road> listaCarreterasEntrantes; //Lista de Carreteras que entran al Cruce.
    protected Map<Junction,Road> mapaCarreterasSalientes; //Mapa para seleccionar que carretera r llega al cruce j.
    protected List<List<Vehicle>> listaDeColas; //Lista de colas para las carreteras Entrantes.
    protected int indiceSemaforo; //Indice del semaforo de la carretera Entrante -1 indica rojo.
    protected int ultimoPasoDeCambio; //Paso en el cual el indice de Semaforo en verde ha cambiado de Valor.
    protected LightSwitchStrategy estrategiaCambioSemaforos; //Estrategia para cambiar el color de los semaforos.
    protected DequeingStrategy estrategiaEliminarCola; //Estrategia para eliminar vehiculos de las colas.
    protected int coordenadaX; //Coordenada X;
    protected int coordenadaY; //Coordenada Y;




    Junction(String id, LightSwitchStrategy lsStrategy, DequeingStrategy dqStrategy, int xCoor, int yCoor) throws WrongValuesException {
        super(id);
        if(!checkValores(lsStrategy,dqStrategy,xCoor,yCoor)){
            throw new WrongValuesException("Los valores introducidos son erroneos");
        }
        indiceSemaforo = 0;
        listaCarreterasEntrantes = new ArrayList<Road>();
        mapaCarreterasSalientes = new HashMap<Junction, Road>();
        ultimoPasoDeCambio  = 0;
    }


    @Override
    protected void advance(int time) {

    }

    @Override
    public JSONObject report() {
        JSONObject report = new JSONObject();
        report.put("id", _id);
        if(indiceSemaforo == -1) report.put("green", "none");
        else report.put("green", listaCarreterasEntrantes.get(indiceSemaforo).getId());
        int i = 0;
        for(List<Vehicle> lv : listaDeColas) {
            JSONObject obj = new JSONObject();
            obj.put("road", listaCarreterasEntrantes.get(i).getId());
            for(Vehicle v : lv) {
                obj.append("vehicles", v.getId());
            }
            report.append("queues", obj);
            i++;
        }
        return report;
    }

    public void addIncommingRoad(Road r) throws WrongValuesIncommingRoad {
        if(checkIncommingRoad(r)){
            listaCarreterasEntrantes.add(r);
            List<Vehicle> cola = new LinkedList<Vehicle>();
            listaDeColas.add(cola);
        }
    }

    private Boolean checkIncommingRoad(Road r) throws WrongValuesIncommingRoad {
        if(r.getCruceDestino() != this) throw new WrongValuesIncommingRoad("Wrong Incomming Road");
        else return true;
    }

    public void addOutGoingRoad(Road r) throws WrongValuesOutGoingRoad {
        if(checkValuesOutGoingRoad(r)){
            Junction j = r.getCruceDestino();
            if(mapaCarreterasSalientes.containsKey(j)){ //Compruebo si mi cruce tiene otra carretera saliente.
                mapaCarreterasSalientes.put(r.getCruceDestino(),r);

            }

        }


    }

    private boolean checkValores(LightSwitchStrategy lsStrategy, DequeingStrategy dqStrategy, int xCoor, int yCoor) {
        if(lsStrategy == null || dqStrategy == null || xCoor < 0 || yCoor < 0) return false;
        else return true;
    }

    private boolean checkValuesOutGoingRoad(Road r) throws WrongValuesOutGoingRoad {
        if(r.getCruceOrigen() == this); throw new WrongValuesOutGoingRoad("Wrong Outgoing Road");
    }

    public void enter(Vehicle v){
        v.carretera.vehiculos.add(v);
    }

    public Road roadTo(Junction j){
        return mapaCarreterasSalientes.get(j);
    }
}
