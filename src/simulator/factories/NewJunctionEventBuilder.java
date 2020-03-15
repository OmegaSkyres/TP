package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.model.DequeingStrategy;
import simulator.model.Junction;
import simulator.model.LightSwitchStrategy;
import simulator.model.NewJunctionEvent;

public class NewJunctionEventBuilder extends Builder<NewJunctionEvent> {

    Factory<LightSwitchStrategy> ls;
    Factory<DequeingStrategy> dq;

    public NewJunctionEventBuilder(Factory<LightSwitchStrategy> lssFactory, Factory<DequeingStrategy> dqsFactory){
        super("new_junction");
        ls = lssFactory;
        dq = dqsFactory;

    }

    @Override
    protected NewJunctionEvent createTheInstance(JSONObject data) {
        int time = data.getInt("time");
        String id = data.getString("id");
        JSONArray coordenadas = data.getJSONArray("coor");
        int xC = coordenadas.getInt(0);
        int yC = coordenadas.getInt(1);
        LightSwitchStrategy lsStrategy = ls.createInstance(data.getJSONObject("ls_strategy"));
        DequeingStrategy dqStrategy = dq.createInstance(data.getJSONObject("dq_strategy"));
        return new NewJunctionEvent(time,id,lsStrategy,dqStrategy,xC,yC);


    }
}
