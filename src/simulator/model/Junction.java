package simulator.model;

import org.json.JSONObject;
import simulator.exceptions.WrongValuesException;
import simulator.exceptions.WrongValuesIncommingRoad;
import simulator.exceptions.WrongValuesOutGoingRoad;
import simulator.exceptions.WrongValuesVehicle;

import java.util.*;

public class Junction extends SimulatedObject {
    protected List<Road> listaCarreterasEntrantes; //Lista de Carreteras que entran al Cruce.
    protected Map<Junction,Road> mapaCarreterasSalientes; //Mapa para seleccionar que carretera r llega al cruce j.
    protected List<List<Vehicle>> listaDeColas; //Lista de colas para las carreteras Entrantes.
    protected Map<Road,List<Vehicle>> colaCarretera; //Mapa para identificar y mejorar la busqueda
    protected int indiceSemaforo; //Indice del semaforo de la carretera Entrante -1 indica rojo.
    protected int ultimoPasoDeCambio; //Paso en el cual el indice de Semaforo en verde ha cambiado de Valor.
    protected LightSwitchStrategy ligstr; //EstrategiaCambioSemaforos; //Estrategia para cambiar el color de los semaforos.
    protected DequeingStrategy dqstr; //EstrategiaEliminarCola; //Estrategia para eliminar vehiculos de las colas.
    protected int coordenadaX; //Coordenada X;
    protected int coordenadaY; //Coordenada Y;



    protected Junction(String id, LightSwitchStrategy lsStrategy, DequeingStrategy dqStrategy, int xCoor, int yCoor) throws WrongValuesException {
        super(id);
        if(!checkValores(lsStrategy,dqStrategy,xCoor,yCoor)){
            throw new WrongValuesException("Los valores introducidos son erroneos");
        }
        else{
            ligstr = lsStrategy;
            dqstr = dqStrategy;
            coordenadaX = xCoor;
            coordenadaY = yCoor;
            indiceSemaforo = -1;
            listaCarreterasEntrantes = new LinkedList<Road>();
            mapaCarreterasSalientes = new HashMap<Junction, Road>();
            listaDeColas = new LinkedList<List<Vehicle>>();
            colaCarretera = new HashMap<Road, List<Vehicle>>();
        }

    }


    @Override
    protected void advance(int time) throws WrongValuesVehicle {
        if(indiceSemaforo != -1) {
            if(!listaDeColas.isEmpty()) {
                List<Vehicle> vehiculosAMover = dqstr.dequeue(listaDeColas.get(indiceSemaforo));
                for (Vehicle v : vehiculosAMover) {
                    try {
                        v.moveToNextRoad();
                        listaDeColas.get(indiceSemaforo).remove(v);
                    } catch (WrongValuesVehicle e) {
                        System.out.format(e.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
            int index = ligstr.chooseNextGreen(listaCarreterasEntrantes, listaDeColas, indiceSemaforo, ultimoPasoDeCambio, time);
            if (index != indiceSemaforo) {
                indiceSemaforo = index;
                ultimoPasoDeCambio = time;
            }


    }

    @Override
    public JSONObject report() {
        JSONObject report = new JSONObject();
        report.put("id", _id);
        if(indiceSemaforo == -1) report.put("green", "none");
        else report.put("green", listaCarreterasEntrantes.get(indiceSemaforo).getId());
        int i = 0;
        if(listaDeColas.isEmpty()){
            report.put("queues", (Collection<?>) null);
        }
        else {
            for(List<Vehicle> lv : listaDeColas) {
                JSONObject obj = new JSONObject();
                obj.put("road", listaCarreterasEntrantes.get(i).getId());
                if(lv.isEmpty()){
                    obj.put("vehicles", (Collection<?>) null);
                }
                else{
                    for(Vehicle v : lv) {
                        obj.append("vehicles", v.getId());
                    }
                }
                report.append("queues", obj);
                i++;
            }
        }

        return report;
    }

    public void addIncommingRoad(Road r) throws WrongValuesIncommingRoad {
        if(checkIncommingRoad(r)){
            listaCarreterasEntrantes.add(r);
            LinkedList<Vehicle> cola = new LinkedList<Vehicle>();
            listaDeColas.add(cola);
            colaCarretera.put(r,cola);
        }
    }

    private Boolean checkIncommingRoad(Road r) throws WrongValuesIncommingRoad {
        if(r.getCruceDestino() != this) throw new WrongValuesIncommingRoad("Wrong Incomming Road");
        else return true;
    }

    public void addOutGoingRoad(Road r) throws WrongValuesOutGoingRoad {
        if(checkValuesOutGoingRoad(r)){
            mapaCarreterasSalientes.put(r.getCruceDestino(),r);

        }
    }

    private boolean checkValores(LightSwitchStrategy lsStrategy, DequeingStrategy dqStrategy, int xCoor, int yCoor) {
        if(lsStrategy == null || dqStrategy == null || xCoor < 0 || yCoor < 0) return false;
        else return true;
    }

    private boolean checkValuesOutGoingRoad(Road r) throws WrongValuesOutGoingRoad {
        if(r.getCruceOrigen() != this ||  mapaCarreterasSalientes.containsKey(r.getCruceDestino())) throw new WrongValuesOutGoingRoad("Wrong Outgoing Road");
        else return true;
    }

    public void enter(Vehicle v){
        colaCarretera.get(v.carretera).add(v);
    }

    protected Road roadTo(Junction j){
        return mapaCarreterasSalientes.get(j);
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public int getCoordenadaY(){
        return coordenadaY;
    }

    public int getIndiceSemaforo() {
        return indiceSemaforo;
    }

    public List<Road> getListaCarreterasEntrantes() {
        return listaCarreterasEntrantes;
    }

    public String muestraSemaforoVerde(){
        String queue = "";

        for (int i = 0; i < listaCarreterasEntrantes.size(); i++) {
            int idx = listaCarreterasEntrantes.get(i).getCruceDestino().getIndiceSemaforo();
            if(idx != -1 && listaCarreterasEntrantes.get(i).equals(listaCarreterasEntrantes.get(i).getCruceDestino().getListaCarreterasEntrantes().get(idx))){
                queue = listaCarreterasEntrantes.get(i).toString();
            }
        }
        if(queue == ""){
            queue = "NONE";
        }


        return queue;
    }

    public String muestraColas() {
        String queue = "";
        Iterator it = colaCarretera.keySet().iterator();
        while(it.hasNext()){
            Object key = it.next();
            queue += key.toString() + ":" + "[";
            for(int i = 0; i < colaCarretera.get(key).size(); i++){
                queue += colaCarretera.get(key).get(i);
            }
            queue += "]" + " ";

        }
        return queue;
    }
}
