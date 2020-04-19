package simulator.model;

public abstract class NewRoadEvent extends Event {
    protected String newId;
    protected String origen;
    protected String destino;
    protected int newlength;
    protected int newClassLimit;
    protected int newMaxSpeed;
    protected Weather newWeather;

    NewRoadEvent(int time, String id, String srcJun, String destJunc, int length, int co2Limit, int maxSpeed, Weather weather) {
        super(time);
        newId = id;
        origen = srcJun;
        destino = destJunc;
        newlength = length;
        newClassLimit = co2Limit;
        newMaxSpeed = maxSpeed;
        newWeather = weather;
    }

    @Override
    void execute(RoadMap map) throws Exception {
        Junction srcJunc = map.getJunction(origen);
        Junction destJunc = map.getJunction(destino);
        Road r = createRoad(srcJunc,destJunc);
        map.addRoad(r);

    }

    protected abstract Road createRoad(Junction srcJunc, Junction destJunc) throws Exception;

    @Override
    public String toString() {
        return "New Road '"+newId+"'";
    }

}
