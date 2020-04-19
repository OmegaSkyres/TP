package simulator.factories;

import org.json.JSONObject;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.Road;

public abstract class NewRoadEventBuilder extends Builder<Event> {
    NewRoadEventBuilder(String type) {
        super(type);
    }

    @Override
    protected Event createTheInstance(JSONObject data) throws Exception {
        Event e = createEvent(data);
        return e;
    }

    protected abstract Event createEvent(JSONObject data) throws Exception;

}
