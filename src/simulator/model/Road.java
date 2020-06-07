package simulator.model;

import org.json.JSONObject;
import simulator.exceptions.WrongValuesContamination;
import simulator.exceptions.WrongValuesVehicle;
import simulator.exceptions.WrongValuesWeather;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public abstract class Road extends SimulatedObject {
    protected int longitud; // Longitud de la carretera
    protected int velocidadMaxima; // Velocidad maxima
    protected int limiteVelocidad; // Limite de velocidad en una carretera
    protected int alarmaContaminacion; //Indica el Limite de contaminacion
    protected Weather condicionAmbiental; //Enumerado que indica las condiciones para adaptar los demas parametros.
    protected int contaminacionTotal; //Contaminacion total acumulada en la carretera.
    protected Junction cruceOrigen; // Cruce del que parte la carretera
    protected Junction cruceDestino; // Cruce al que llega la carretera
    protected List<Vehicle> vehiculos; // Lista ordenada de vehiculos en lacarretera (ordenada por localizacion)
    protected Comparator<Vehicle> comparadorVehiculo; // Orden entre vehiculos

    protected Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) throws Exception {
        super(id);
        if(checkValues(srcJunc, destJunc, maxSpeed, contLimit, length, weather)){
            longitud = length;
            velocidadMaxima = maxSpeed;
            alarmaContaminacion = contLimit;
            condicionAmbiental = weather;
            limiteVelocidad = velocidadMaxima;
            contaminacionTotal = 0;
            cruceOrigen = srcJunc;
            cruceDestino = destJunc;
            cruceOrigen.mapaCarreterasSalientes.put(srcJunc,this);
            cruceDestino.mapaCarreterasSalientes.put(destJunc,this);
            vehiculos = new LinkedList<Vehicle>();
            cruceOrigen.addOutGoingRoad(this);
            cruceDestino.addIncommingRoad(this);
            comparadorVehiculo = new Comparator<Vehicle>() {
                @Override
                public int compare(Vehicle v1, Vehicle v2) {
                    if(v1.getLocalizacion() < v2.getLocalizacion()) return 1;
                    else if(v1.getLocalizacion() > v2.getLocalizacion()) return -1;
                    else return 0;
                }
            };
        }

}

    private boolean checkValues(Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) throws Exception {
        if(maxSpeed <= 0) throw new Exception("El valor de la velocidad no puede ser negativo");
        if(contLimit < 0) throw new Exception("El valor de contLimit no puede ser negativo");
        if(length <= 0 ) throw  new Exception("El valor de lenght no puede ser negativo");
        if(srcJunc == null || destJunc == null) throw new Exception("Los cruces deben existir");
        if(weather == null) throw new WrongValuesWeather("El tiempo deve tener un valor Valido");
        return true;
    }


    @Override
    protected void advance(int time) {
        reduceTotalContamination();
        updateSpeedLimit();
        if(!vehiculos.isEmpty()){
            for(Vehicle v : vehiculos){
                if(v.estado == VehicleStatus.WAITING){
                    v.velocidadActual = 0;
                }
                else{
                    v.setSpeed(calculateVehicleSpeed(v));;
                    v.advance(time);
                }

            }
        }

        vehiculos.sort(comparadorVehiculo);

    }

    @Override
    public JSONObject report() {
        JSONObject report = new JSONObject();
        report.put("id",this._id);
        report.put("speedlimit",this.getLimiteVelocidad());
        report.put("weather",this.getCondicionAmbiental());
        report.put("co2", this.getContaminacionTotal());
        if(vehiculos.isEmpty()){
            report.put("vehicles", (Collection<?>) null);
        }
        else{
            for(Vehicle v : vehiculos){
                report.append("vehicles", v.getId());
            }
        }

        return report;

    }

    public int getLength(){
        return longitud;
    }

    public String getId(){
        return _id;
    }

    protected void enter(Vehicle v) throws WrongValuesVehicle {
        if(v.localizacion == 0 && v.velocidadActual == 0){
            vehiculos.add(v);
        }
        else throw new WrongValuesVehicle("Los atributos de este Vehiculo son Erroneos");
    }

    public void exit(Vehicle v){
        vehiculos.remove(v);
    }

    public void setWeather(Weather w) throws WrongValuesWeather {
        if(w == null){
            throw new WrongValuesWeather("El valor de las Condicioones Atmosfericas es Erroneo");
        }
        else{
            condicionAmbiental = w;
        }
    }

    public void addContamination(int c) throws WrongValuesContamination {
        if(c < 0){
            throw new WrongValuesContamination("El valor de la contaminacion es Erroneo");
        }
        contaminacionTotal += c;
    }

    public Junction getCruceDestino() {
        return cruceDestino;
    }

    public Junction getCruceOrigen() {
        return cruceOrigen;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public Weather getCondicionAmbiental() {
        return condicionAmbiental;
    }

    public int getAlarmaContaminacion() {
        return alarmaContaminacion;
    }

    public int getLongitud() {
        return longitud;
    }

    public int getContaminacionTotal() {
        return contaminacionTotal;
    }

    public int getLimiteVelocidad() {
        return limiteVelocidad;
    }

    abstract void reduceTotalContamination();
    abstract void updateSpeedLimit();
    abstract int calculateVehicleSpeed(Vehicle v);

}
