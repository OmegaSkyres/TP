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
        JSONObject report;
        // genera informe para cruces

        for(int i = 0; i < this.cruces.size(); i++){
            report.put(this.cruces.get(i).report());
        }
        // genera informe para carreteras

        for(int i = 0; i < this.carreteras.size(); i++){
            report += this.carreteras.get(i).report() + "\n";
        }
        // genera informe para vehiculos

        for(int i = 0; i < this.vehiculos.size(); i++){
            report += this.vehiculos.get(i).report() + "\n";
        }


        return report;
    }



}
