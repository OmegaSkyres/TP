package simulator.model;

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

}
