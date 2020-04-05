package simulator.model;

public class CityRoad extends Road {
    CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) throws Exception {
        super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
    }

    protected void reduceTotalContamination(){
        int x;
        switch(condicionAmbiental) {
            case STORM: x = 10;
                break;
            case WINDY: x = 10;
                break;
            default: x = 2;
                break;
        }
        if(getContaminacionTotal() - x > 0){
            contaminacionTotal = contaminacionTotal - x;
        }
    }

    @Override
    void updateSpeedLimit() {
        limiteVelocidad = velocidadMaxima;
    }

    @Override
    int calculateVehicleSpeed(Vehicle v) {
        double velocidadRedondeo =  Math.ceil(((11.0 - v.getGradoContaminacion()) / 11.0) * getLimiteVelocidad());
        return v.velocidadActual = (int) velocidadRedondeo;
    }

}
