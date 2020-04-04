package simulator.model;

import org.json.JSONObject;
import simulator.misc.SortedArrayList;

import java.util.List;

public class TrafficSimulator implements Observable<TrafficSimObserver> {

    protected RoadMap mapaDeCarreteras;
    protected List<Event> listaEventos;
    protected int time;

    public TrafficSimulator(){
        mapaDeCarreteras = new RoadMap();
        listaEventos = new SortedArrayList<Event>();
        time = 0;

    }

    public void addEvent(Event e){
        listaEventos.add(e);
    }

    public void advance() throws Exception {
        time = time + 1;
        int i;
        for(i = 0; i < listaEventos.size();) {
            Event e = listaEventos.get(i);
            if(e.getTime() == time){
                e.execute(mapaDeCarreteras);
                listaEventos.remove(i);
            }
            else i++;

        }
        for(Junction j : mapaDeCarreteras.getJunctions()){
            j.advance(time);
        }
        for(Road r : mapaDeCarreteras.getCarreteras()){
            r.advance(time);
        }
    }

    public void reset(){
        mapaDeCarreteras.reset();
        //mapaDeCarreteras = new RoadMap();
        listaEventos.clear();
        time = 0;

    }

    public JSONObject report(){
        JSONObject report = new JSONObject();
        report.put("time",this.time);
        report.put("state",mapaDeCarreteras.report());
        return  report;
    }


    @Override
    public void addObserver(TrafficSimObserver o) {

    }

    @Override
    public void removeObserver(TrafficSimObserver o) {

    }
}
