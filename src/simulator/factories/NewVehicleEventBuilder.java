package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.model.Event;
import simulator.model.NewVehicleEvent;

import java.util.LinkedList;
import java.util.List;

public class NewVehicleEventBuilder extends Builder<Event> {
    public NewVehicleEventBuilder() {
        super("new_vehicle");
    }

    @Override
    protected NewVehicleEvent createTheInstance(JSONObject data) {
        int time = data.getInt("time");
        String id = data.getString("id");
        int maxSpeed = data.getInt("maxspeed");
        int contamination = data.getInt("class");
        JSONArray itinerary = data.getJSONArray("itinerary");
        List<String> iti = new LinkedList<String>();
        for(int i = 0; i < itinerary.length(); i++){
            iti.add(itinerary.getString(i));
        }
        return new NewVehicleEvent(time,id,maxSpeed,contamination,iti);
    }
}
