package simulator.model;

public class NewInterCityRoadEvent extends NewRoadEvent{


    public NewInterCityRoadEvent(int time, String id, String srcJun, String destJunc, int length, int co2Limit, int maxSpeed, Weather weather)
    {
        super(time, id, srcJun, destJunc, length, co2Limit, maxSpeed, weather);
    }

    @Override
    void execute(RoadMap map) throws Exception {
        Junction srcJunc = map.getJunction(origen);
        Junction destJunc = map.getJunction(destino);
        InterCityRoad r = createRoad(srcJunc,destJunc);
        map.addRoad(r);

    }

    InterCityRoad createRoad(Junction srcJunc, Junction destJunc) throws Exception {
        return new InterCityRoad(newId,srcJunc,destJunc,newMaxSpeed,newClassLimit,newlength,newWeather);
    }
}
