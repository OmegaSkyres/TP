package simulator.model;

public class InterCityRoad extends Road {
    InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
        super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
    }

    protected int reduceTotalContamination(){
        int x;
        if(condicionAmbiental.equals("SUNNY")){
            x = 2;
        }
        else if (condicionAmbiental.equals("CLOUDY")){
            x = 3;
        }
        else if (condicionAmbiental.equals("RAINY")){
            x = 10;
        }
        else if (condicionAmbiental.equals("WINDY")){
            x = 15;
        }
        else{
            x = 20;
        }
        return (int) (((100.0-x) / 100.0) * getContaminacionTotal());
    }

    protected void updateSpeedLimit(){
        if(getContaminacionTotal() > getAlarmaContaminacion()){
            limiteVelocidad = (int) (getVelocidadMaxima() * 0.5);
        }
        else limiteVelocidad = getVelocidadMaxima();
    }

    protected void calculateVehicleSpeed(){
        for(Vehicle v : vehiculos) { //TODO PREGUNTAR SI CUANDO SE CUANDO SE CALCULA LA VELOCIDAD SE LE PONE A TODOS LOS VEHICULOS
            if (condicionAmbiental.equals("STORM")) {
                v.velocidadActual = (int) (getLimiteVelocidad() * 0.8);
            } else v.velocidadActual = getLimiteVelocidad();
        }
    }
}
