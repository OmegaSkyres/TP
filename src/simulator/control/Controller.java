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
import simulator.model.TrafficSimObserver;
import simulator.model.TrafficSimulator;

import java.io.*;
import java.util.Collection;

public class Controller {
    protected TrafficSimulator trafficSimulator; //Utilizado para ejecutar la simulación.
    protected Factory<Event> eventFactory; //: Se usa para parsear los eventos suministrados por el usuario
    private FileInputStream ficheroEntrada;

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

    public void run(int n, OutputStream out) throws Exception {
        if (out == null) {
            out = new OutputStream() {
                @Override
                public void write(int b) throws IOException {}
            };
        }
        PrintStream p = new PrintStream(out);
        p.println("{");
        p.println("  \"states\": [");
        for(int i = 0; i < n - 1; i++){
            trafficSimulator.advance();
            p.print(trafficSimulator.report());
            p.println(",");
        }
        trafficSimulator.advance();
        p.println(trafficSimulator.report());
        p.println("]");
        p.println("}");

    }

    public void reset() {
        trafficSimulator.reset();
    }

    public void addObserver(TrafficSimObserver o){
        trafficSimulator.addObserver(o);
    }

    void removeObserver(TrafficSimObserver o){
        trafficSimulator.removeObserver(o);
    }

    void addEvent(Event e){
        trafficSimulator.addEvent(e);
    }

    public void setFicheroEntrada(File ficheroEntrada) throws FileNotFoundException {
        this.ficheroEntrada = new FileInputStream(ficheroEntrada);
    }

}
