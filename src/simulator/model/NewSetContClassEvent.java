package simulator.model;

import simulator.exceptions.WrongValuesContamination;
import simulator.misc.Pair;

import java.util.Iterator;
import java.util.List;

public class NewSetContClassEvent extends Event {
    protected List<Pair<String,Integer>> cs;

    public NewSetContClassEvent(int time, List<Pair<String,Integer>> cs) throws WrongValuesContamination {
        super(time);
        this.cs = cs;
        checkStateContamination();

    }
    @Override
    void execute(RoadMap map) throws Exception {
        for(Pair p : cs){
            map.getVehicle(p.getFirst().toString()).setContaminationClass((Integer) p.getSecond());;
        }

    }

    void checkStateContamination() throws WrongValuesContamination {
        if(this.cs == null){
            throw new WrongValuesContamination("El valor de la lista es nulo");
        }
    }
}
