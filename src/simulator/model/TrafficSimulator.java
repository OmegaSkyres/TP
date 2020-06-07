package simulator.model;

import org.json.JSONObject;
import simulator.misc.SortedArrayList;

import java.util.ArrayList;
import java.util.List;

public class TrafficSimulator implements Observable<TrafficSimObserver> {

    protected RoadMap mapaDeCarreteras;
    protected List<Event> listaEventos;
    private List<TrafficSimObserver> observadores;
    protected int time;

    public TrafficSimulator(){
        this.observadores = new ArrayList<>();
        mapaDeCarreteras = new RoadMap();
        listaEventos = new SortedArrayList<Event>();
        time = 0;

    }

    public void addEvent(Event e) throws Exception {
        if(e != null){
            if(e.getTime() < this.time){
                String error = "No se pudo insertar el evento";
                this.notificaError(error);
                throw new Exception(error);
            }
            else{
                listaEventos.add(e);
                notificaNuevoEvento(e);
            }
        }


    }

    public void advance() throws Exception {
        try {
            time = time + 1;
            int i;
            for (i = 0; i < listaEventos.size(); ) {
                Event e = listaEventos.get(i);
                if (e.getTime() == time) {
                    e.execute(mapaDeCarreteras);
                    listaEventos.remove(i);
                } else i++;

            }
            for (Junction j : mapaDeCarreteras.getJunctions()) {
                j.advance(time);
            }
            for (Road r : mapaDeCarreteras.getCarreteras()) {
                r.advance(time);
            }
            notificaAvanza();
        }catch (Exception e){
            notificaError(e.getMessage());
        }
    }

    public void reset(){
        mapaDeCarreteras.reset();
        listaEventos.clear();
        time = 0;

    }

    public JSONObject report(){
        JSONObject report = new JSONObject();
        report.put("time",this.time);
        report.put("state",mapaDeCarreteras.report());
        return  report;
    }

    public List<Vehicle> getVehiculos(){
        return mapaDeCarreteras.getVehiculos();
    }

    public List<Road> getRoads(){
        return mapaDeCarreteras.getCarreteras();
    }

    private void notificaNuevoEvento(Event e) {
        for (TrafficSimObserver o : this.observadores) {
            o.onEventAdded(mapaDeCarreteras,listaEventos,e,time);
        }
    }

    private void notificaError(String error) throws Exception {
        for (TrafficSimObserver o : this.observadores) {
            o.onError(error);
        }
    }

    private void notificaAvanza() throws Exception{
        for (TrafficSimObserver o : this.observadores) {
            o.onAdvanceStart(mapaDeCarreteras,listaEventos,time);
        }
    }


    @Override
    public void addObserver(TrafficSimObserver o) {
        if (o != null && !this.observadores.contains(o)) {
            this.observadores.add(o);
        }
    }

    @Override
    public void removeObserver(TrafficSimObserver o) {
        if (o != null && this.observadores.contains(o)) {
            this.observadores.remove(o);
        }
    }

    public int getTime() {
        return time;
    }
}
