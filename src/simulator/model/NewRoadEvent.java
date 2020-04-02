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
    public String toString() {
        return "New Road '"+newId+"'";
    }

}
