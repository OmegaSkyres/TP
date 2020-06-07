package simulator.model;

import simulator.exceptions.WrongValuesContamination;
import simulator.exceptions.WrongValuesRoad;
import simulator.exceptions.WrongValuesWeather;
import simulator.misc.Pair;

import java.util.List;

public class NewSetWeatherEvent extends Event {
    protected List<Pair<String,Weather>> ws;

    public NewSetWeatherEvent(int time, List<Pair<String,Weather>> ws) throws WrongValuesWeather {
        super(time);
        this.ws = ws;
        checkContClass();
    }

    @Override
    void execute(RoadMap map) throws Exception {
        for(Pair p : ws){
            if(!map.getCarreteras().contains(p.getFirst()))throw new WrongValuesRoad("Carretera inexistente");
            else{
                map.getRoad(p.getFirst().toString()).setWeather((Weather) p.getSecond());
            }
        }

    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String cadena = "";
        for(int i = 0; i < ws.size(); i++){
            cadena = sb.append("(" + ws.get(i).getFirst() + "," + ws.get(i).getSecond() + ")").toString();
        }
        return "Change CO2 Class: " + cadena + "]";

    }

    void checkContClass() throws WrongValuesWeather {
        if(this.ws == null) throw new WrongValuesWeather("El dato del tiempo es un valor nulo");
    }
}
