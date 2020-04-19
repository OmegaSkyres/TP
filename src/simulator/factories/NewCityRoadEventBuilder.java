package simulator.factories;

import org.json.JSONObject;
import simulator.model.*;

public class NewCityRoadEventBuilder extends NewRoadEventBuilder {
    public NewCityRoadEventBuilder() {
        super("new_city_road");
    }

    protected Event createEvent(JSONObject data) throws Exception {
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
