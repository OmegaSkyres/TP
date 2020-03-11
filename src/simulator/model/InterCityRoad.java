package simulator.model;

public class InterCityRoad extends Road {
    InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) throws Exception {
        super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
    }

    protected void reduceTotalContamination(){
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
