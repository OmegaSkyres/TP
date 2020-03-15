package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.exceptions.WrongValuesWeather;
import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.NewSetWeatherEvent;
import simulator.model.Weather;

import java.util.LinkedList;
import java.util.List;

public class SetWeatherEventBuilder extends Builder<Event> {
    SetWeatherEventBuilder() {
        super("set_weather");
    }

    @Override
    protected Event createTheInstance(JSONObject data) throws WrongValuesWeather {
        int time = data.getInt("time");
        List<Pair<String, Weather>> pares = new LinkedList<Pair<String,Weather>>();
        JSONArray array = data.getJSONArray("info");
        for(int i = 0; i < array.length(); i++){
            pares.add(new Pair<String,Weather>(array.getJSONObject(i).getString("road"),Weather.valueOf(array.getJSONObject(i).getString("weather").toUpperCase())));
        }
        return new NewSetWeatherEvent(time,pares);
    }
}
