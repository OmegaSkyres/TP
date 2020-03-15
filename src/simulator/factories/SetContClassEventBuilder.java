package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.exceptions.WrongValuesContamination;
import simulator.exceptions.WrongValuesWeather;
import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.NewSetContClassEvent;

import java.util.LinkedList;
import java.util.List;

public class SetContClassEventBuilder extends Builder<Event> {
    SetContClassEventBuilder() {
        super("set_cont_class");
    }

    @Override
    protected Event createTheInstance(JSONObject data) throws WrongValuesWeather, WrongValuesContamination {
        int time = data.getInt("time");
        List<Pair<String,Integer>> lista = new LinkedList<Pair<String,Integer>>();
        JSONArray array = data.getJSONArray("info");
        for(int i = 0; i < array.length(); i++){
            lista.add(new Pair<String,Integer>(array.getJSONObject(i).getString("vehicle"),array.getJSONObject(i).getInt("class")));
        }
        return new NewSetContClassEvent(time,lista);
    }
}
