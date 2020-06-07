package simulator.model;

import static simulator.model.Weather.*;

public class InterCityRoad extends Road {
    InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) throws Exception {
        super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
    }

    protected void reduceTotalContamination(){
        int x;
        switch (condicionAmbiental) {
            case CLOUDY: x = 3;
                break;
            case RAINY: x = 10;
                break;
            case STORM: x = 20;
                break;
            case SUNNY: x = 2;
                break;
            case WINDY: x = 15;
                break;
            default: x = 0;
                break;
        }
        contaminacionTotal = (int) (((100.0-x) / 100.0) * getContaminacionTotal());
    }

    protected void updateSpeedLimit(){
        if(getContaminacionTotal() > getAlarmaContaminacion()){
            limiteVelocidad = (int) (getVelocidadMaxima() * 0.5);
        }
        else limiteVelocidad = getVelocidadMaxima();
    }

    @Override
    int calculateVehicleSpeed(Vehicle v) {
            if (condicionAmbiental.equals("STORM")) {
                v.velocidadActual = (int) (getLimiteVelocidad() * 0.8);
            } else v.velocidadActual = getLimiteVelocidad();
            return v.velocidadActual;
    }



}
