package simulator.model;

import simulator.exceptions.WrongValuesException;
import simulator.exceptions.WrongValuesVehicle;

import java.util.LinkedList;
import java.util.List;

public class NewVehicleEvent extends Event {
    protected String id;
    protected int maxSpeed;
    protected int contClass;
    protected List<String> itinerary;

    public NewVehicleEvent(int time, String id, int maxSpeed, int contClass, List<String> itinerary) {
        super(time);
        this.id = id;
        this.maxSpeed = maxSpeed;
        this.contClass = contClass;
        this.itinerary = itinerary;
    }
    @Override
    void execute(RoadMap map) throws Exception {
        Vehicle v = createVehicle(map);
        map.addVehicle(v);
        v.moveToNextRoad();

    }

    @Override
    public String toString() {
        return "New Vehicle '"+id+"'";
    }

    Vehicle createVehicle(RoadMap map) throws Exception {
        List<Junction> iti = new LinkedList<Junction>();
        for(String s : itinerary ){
            iti.add(map.getJunction(s));
        }
        return new Vehicle(this.id,this.maxSpeed,this.contClass,iti);
    }
}
