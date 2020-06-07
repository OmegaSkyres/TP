package simulator.control;

import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import simulator.exceptions.WrongValuesContamination;
import simulator.exceptions.WrongValuesException;
import simulator.exceptions.WrongValuesWeather;
import simulator.factories.Factory;
import simulator.misc.Pair;
import simulator.model.*;

import java.io.*;
import java.util.*;

public class Controller {
    protected TrafficSimulator trafficSimulator; //Utilizado para ejecutar la simulación.
    protected Factory<Event> eventFactory; //: Se usa para parsear los eventos suministrados por el usuario
    private InputStream ficheroEntrada;
    private OutputStream ficheroSalida;
    private int pasosSimulacion;

    public Controller(TrafficSimulator sim, Factory<Event> eventsFactory) throws IOException {
        trafficSimulator = sim;
        eventFactory = eventsFactory;
        checkValues();
    }

    public Controller(TrafficSimulator sim, Factory<Event> eventsFactory, Integer limite, InputStream entrada, OutputStream salida) throws IOException {
        trafficSimulator = sim;
        pasosSimulacion = limite;
        ficheroEntrada = entrada;
        ficheroSalida = salida;
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

    public void loadEvents(InputStream in) throws Exception {
        JSONObject object = new JSONObject(new JSONTokener(in));
        if(object.isEmpty() || !object.has("events")) throw new WrongValuesException("Los valores del JSon son incorrectos");
        else{
            JSONArray array = object.getJSONArray("events");
            for(int i = 0; i < array.length(); i++){
                trafficSimulator.addEvent(eventFactory.createInstance(array.getJSONObject(i)));
            }
        }

    }

    public void run(int n) throws Exception {
        for(int i = 0; i < n; i++){
            trafficSimulator.advance();
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

    void addEvent(Event e) throws Exception {
        trafficSimulator.addEvent(e);
    }

    public void setFicheroEntrada(File ficheroEntrada) throws FileNotFoundException {
        this.ficheroEntrada = new FileInputStream(ficheroEntrada);
    }

    public int getSteps(){
        return trafficSimulator.getTime();
    }

    public InputStream getFichero() {
        return ficheroEntrada;
    }

    public List<Vehicle> getVehiculos(){
        return trafficSimulator.getVehiculos();
    }

    public List<Road> getRoads(){
        return trafficSimulator.getRoads();
    }

    public void newEventC02(String newVehicle, int newCO2, int newTicks) throws Exception {
        Pair p = new Pair<String, Integer>(newVehicle, newCO2);
        ArrayList<Pair<String,Integer>> ls = new ArrayList<Pair<String,Integer>>();
        ls.add(p);


        NewSetContClassEvent e = new NewSetContClassEvent(newTicks + trafficSimulator.getTime(),ls);
        addEvent(e);
    }

    public void newEventRoadWeather(String newRoad, Weather newWeather, int newTicks) throws Exception {
        Pair p = new Pair<String, Weather>(newRoad, newWeather);
        ArrayList<Pair<String,Weather>> ls = new ArrayList<Pair<String,Weather>>();
        ls.add(p);
        NewSetWeatherEvent e = new NewSetWeatherEvent(newTicks + trafficSimulator.getTime(),ls);
        addEvent(e);
    }
}
