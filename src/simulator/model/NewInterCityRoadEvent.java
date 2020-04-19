package simulator.model;

public class NewInterCityRoadEvent extends NewRoadEvent{
    private String newId;

    public NewInterCityRoadEvent(int time, String id, String srcJun, String destJunc, int length, int co2Limit, int maxSpeed, Weather weather)
    {
        super(time, id, srcJun, destJunc, length, co2Limit, maxSpeed, weather);
        newId = id;
    }

    @Override
    protected Road createRoad(Junction srcJunc, Junction destJunc) throws Exception {
        return new InterCityRoad(newId,srcJunc,destJunc,newMaxSpeed,newClassLimit,newlength,newWeather);
    }


    @Override
    public String toString() {
        return "New InterCityRoad '"+newId+"'";
    }
}
