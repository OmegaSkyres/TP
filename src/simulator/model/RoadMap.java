package simulator.model;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.exceptions.WrongValuesJuntion;
import simulator.exceptions.WrongValuesRoad;
import simulator.exceptions.WrongValuesVehicle;

import javax.print.attribute.standard.JobHoldUntil;
import java.util.*;

public class RoadMap {
    private List<Road> carreteras;
    private List<Junction> cruces;
    private List<Vehicle> vehiculos;
    // estructuras para agilizar la bÃºsqueda (id,valor)
    private Map<String, Road> mapaDeCarreteras;
    private Map<String, Junction> mapaDeCruces;
    private Map<String, Vehicle> mapaDeVehiculos;

    protected RoadMap(){ //Preguntar si la construnctora tiene que ser protected
        carreteras = new LinkedList<Road>();
        cruces = new LinkedList<Junction>();
        vehiculos = new LinkedList<Vehicle>();

        mapaDeCarreteras = new HashMap<>();
        mapaDeCruces = new HashMap<>();
        mapaDeVehiculos = new HashMap<>();
    }

    void addJuntion(Junction j) throws WrongValuesJuntion {
        if(!mapaDeCruces.containsKey(j.getId())){
            cruces.add(j);
            mapaDeCruces.put(j.getId(),j);
        }
        else throw new WrongValuesJuntion("Este cruce ya esta en el Mapa de Cruces");
    }
    void addRoad(Road r) throws WrongValuesRoad {
        if(!mapaDeCarreteras.containsKey(r.getId()) && mapaDeCruces.containsKey(r.getCruceDestino().getId()) && mapaDeCruces.containsKey(r.getCruceOrigen().getId())){
            carreteras.add(r);
            mapaDeCarreteras.put(r.getId(),r);
        }
        else throw new WrongValuesRoad("Esta carretera ya existe en el Mapa de Carreteras");
    }

    void addVehicle(Vehicle v) throws WrongValuesVehicle {
            List<Junction> lista = v.getItinerario();
            for(int i = 0; i < lista.size() - 1; i++){
                Junction ori = lista.get(i);
                Junction dest = lista.get(i+1);
                Road r = ori.roadTo(dest);
                if (r == null){
                    throw new WrongValuesVehicle("El itinerario de este vehiculo es incorrecto");
                }
            }
        if(!mapaDeVehiculos.containsKey(v.getId())){
            vehiculos.add(v);
            mapaDeVehiculos.put(v.getId(),v);


        }
        else throw new WrongValuesVehicle("El Vehiculo ya existe en el mapa de Vehiculos");
    }

    public Junction getJunction(String id) throws Exception {
        if(mapaDeCruces.containsKey(id)){
            return mapaDeCruces.get(id);
        }

        else throw new Exception("El cruce no existe en el mapa de cruce");
    }

    public Road getRoad(String id) throws Exception {
        if(mapaDeCarreteras.containsKey(id)){
            return mapaDeCarreteras.get(id);
        }
        else throw new Exception("La carretera no existe en el mapa de Carreteras");
    }

    public Vehicle getVehicle(String id) throws Exception {
        if(mapaDeVehiculos.containsKey(id)){
            return mapaDeVehiculos.get(id);
        }
        else throw new Exception("El vehiculo no existe en el mapa de Vehiculos");
    }

    public List<Junction>getJunctions(){ return cruces; }

    public List<Road> getCarreteras() { return carreteras; }

    public List<Vehicle> getVehiculos() { return vehiculos; }

    void reset(){
        cruces.clear();
        carreteras.clear();
        vehiculos.clear();
        mapaDeVehiculos.clear();
        mapaDeCarreteras.clear();
        mapaDeCruces.clear();
    }

    public JSONObject report(){
        JSONObject report = new JSONObject();


        // genera informe para carreteras
        for(Road r : carreteras){
            report.append("roads", r.report());
        }

        // genera informe para vehiculos
        for(Vehicle v : vehiculos){
            report.append("vehicles",v.report());
        }

        // genera informe para cruces
        for(Junction j : cruces){
            report.append("junctions", j.report());
        }

        return report;
    }



}
