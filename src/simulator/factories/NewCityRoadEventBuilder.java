package simulator.factories;

import org.json.JSONObject;
import simulator.model.Event;
import simulator.model.NewCityRoadEvent;
import simulator.model.Weather;

public class NewCityRoadEventBuilder extends Builder<Event> {
    NewCityRoadEventBuilder() {
        super("new_city_road");
    }

    @Override
    protected Event createTheInstance(JSONObject data) {
        int time = data.getInt("time");
        String id = data.getString("id");
        String origen = data.getString("src");
        String destino = data.getString("dest");
        int length = data.getInt("length");
        int co2limit = data.getInt("co2limit");
        int maxSpeed = data.getInt("maxspeed");
        Weather weather = Weather.valueOf(data.getString("weather").toUpperCase());
        return new NewCityRoadEvent(time, id, origen, destino, length, co2limit, maxSpeed, weather);
    }
}
