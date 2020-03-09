package simulator.model;

import simulator.exceptions.WrongValuesException;
import simulator.exceptions.WrongValuesVehicle;

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
    void execute(RoadMap map) throws WrongValuesVehicle, WrongValuesException {
        Vehicle v = createVehicle();
        map.addVehicle(v);

    }

    Vehicle createVehicle() throws WrongValuesException {
        return new Vehicle(this.id,this.maxSpeed,this.contClass,this.itinerary);
    }
}
