package simulator.model;

public class CityRoad extends Road {
    CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
        super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
    }

    protected void reduceTotalContamination(){
        int x;
        if(condicionAmbiental.equals("WINDY") || condicionAmbiental.equals("STORM")){
            x = 10;
        }
        else{
            x = 2;
        }
        if(getContaminacionTotal() - x > 0){
            contaminacionTotal = contaminacionTotal - x;
        }
    }

    protected void calculateVehicleSpeed() {
        for (Vehicle v : vehiculos) {
            v.velocidadActual = (int) (((11.0 - v.getGradoContaminacion()) / 11.0) * getLimiteVelocidad());
        }
    }
}
