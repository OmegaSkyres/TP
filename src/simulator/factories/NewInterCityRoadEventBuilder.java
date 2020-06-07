package simulator.factories;

import org.json.JSONObject;
import simulator.model.*;

public class NewInterCityRoadEventBuilder extends NewRoadEventBuilder {
    public NewInterCityRoadEventBuilder() {
        super("new_inter_city_road");
    }

    @Override
    protected Event createEvent(JSONObject data) throws Exception {
        int time = data.getInt("time");
        String id = data.getString("id");
        String src = data.getString("src");
        String dst = data.getString("dest");
        int length = data.getInt("length");
        int co2limit = data.getInt("co2limit");
        int maxSpeed = data.getInt("maxspeed");
        Weather weather = Weather.valueOf(data.getString("weather").toUpperCase());
        return new NewInterCityRoadEvent(time, id, src, dst, length, co2limit, maxSpeed, weather);
    }


}
