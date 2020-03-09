package simulator.model;

import org.json.JSONObject;
import simulator.exceptions.WrongValuesJuntion;
import simulator.exceptions.WrongValuesRoad;
import simulator.exceptions.WrongValuesVehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoadMap {
    private List<Road> carreteras;
    private List<Junction> cruces;
    private List<Vehicle> vehiculos;
    // estructuras para agilizar la bÃºsqueda (id,valor)
    private Map<String, Road> mapaDeCarreteras;
    private Map<String, Junction> mapaDeCruces;
    private Map<String, Vehicle> mapaDeVehiculos;

    protected RoadMap(){ //Preguntar si la construnctora tiene que ser protected
        carreteras = new ArrayList<>();
        cruces = new ArrayList<>();
        vehiculos = new ArrayList<>();

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
        if(!mapaDeVehiculos.containsKey(v.getId())){ //TODO TE FALTA COMPROBAR el itinerario es válido, es decir, existen carreteras que conecten los cruces consecutivos de su itinerario
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
        cruces.clear(); //Todo preguntar si vale la funcion Clear
        carreteras.clear();
        vehiculos.clear();
        mapaDeVehiculos.clear();
        mapaDeCarreteras.clear();
        mapaDeCruces.clear();
    }

    public JSONObject report(){
        JSONObject report = new JSONObject();
        // genera informe para cruces

        for(Junction j : cruces){
            report.put("juntions", j.report());
        }

        // genera informe para carreteras
        for(Road r : carreteras){
            report.put("roads", r.report());
        }

        // genera informe para vehiculos
        for(Vehicle v : vehiculos){
            report.put("vehicles",v.report());
        }

        return report;
    }



}
