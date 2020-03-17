package simulator.control;

import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import simulator.exceptions.WrongValuesContamination;
import simulator.exceptions.WrongValuesException;
import simulator.exceptions.WrongValuesWeather;
import simulator.factories.Factory;
import simulator.model.Event;
import simulator.model.TrafficSimulator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Controller {
    protected TrafficSimulator trafficSimulator; //Utilizado para ejecutar la simulación.
    protected Factory<Event> eventFactory; //: Se usa para parsear los eventos suministrados por el usuario

    public Controller(TrafficSimulator sim, Factory<Event> eventsFactory) throws IOException {
        trafficSimulator = sim;
        eventFactory = eventsFactory;
        checkValues();
    }

    private boolean checkValues() throws IOException {
        if(trafficSimulator == null || eventFactory == null){
            throw new IOException("Algun elemento es nulo");
        }
        else{
            return true;
        }
    }

    public void loadEvents(InputStream in) throws WrongValuesException, WrongValuesContamination, WrongValuesWeather {
        JSONObject object = new JSONObject(new JSONTokener(in));
        if(object.isEmpty() || !object.has("events")) throw new WrongValuesException("Los valores del JSon son incorrectos");
        else{
            JSONArray array = object.getJSONArray("events");
            for(int i = 0; i < array.length(); i++){
                trafficSimulator.addEvent(eventFactory.createInstance(array.getJSONObject(i)));
            }
        }

    }

    public void run(int n, OutputStream out) throws IOException {
        JSONObject object = new JSONObject();
        try{
            for(int i = 0; i < n; i++){
                trafficSimulator.advance();
                object.append("states",trafficSimulator.report());
            }
            PrintStream print = new PrintStream(out);
            print.print(object.toString());
        }

        catch (Exception e){
            System.out.format(e.getMessage() + "%n %n");
        }
    }

    public void reset() {
        trafficSimulator.reset();
    }

}
