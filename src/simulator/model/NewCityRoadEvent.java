package simulator.model;

public class NewCityRoadEvent extends NewRoadEvent {

    public NewCityRoadEvent(int time, String id, String srcJun, String destJunc, int length, int co2Limit, int maxSpeed, Weather weather)
    {
        super(time, id, srcJun, destJunc, length, co2Limit, maxSpeed, weather);
    }

    @Override
    void execute(RoadMap map) throws Exception {
        Junction srcJunc = map.getJunction(origen);
        Junction destJunc = map.getJunction(destino);
        CityRoad r = createRoad(srcJunc,destJunc);
        map.addRoad(r);

    }

    CityRoad createRoad(Junction srcJunc, Junction destJunc) throws Exception {
        return new CityRoad(this.newId,srcJunc,destJunc,this.newMaxSpeed,this.newClassLimit,this.newlength,this.newWeather);
    }
}
