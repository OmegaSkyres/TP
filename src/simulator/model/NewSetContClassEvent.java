package simulator.model;

import simulator.misc.Pair;

import java.util.Iterator;
import java.util.List;

public class NewSetContClassEvent extends Event {
    protected List<Pair<String,Integer>> cs;

    public NewSetContClassEvent(int time, List<Pair<String,Integer>> cs) {
        super(time);
        this.cs = cs;

    }
    @Override
    void execute(RoadMap map) {
        for(int i = 0; i < cs.size(); i++){
            Iterator<List> it= lista.iterator();
        }

    }

    void checkStateContamination(){
        if(this.cs == null){
            throw new
        }
    }
}
