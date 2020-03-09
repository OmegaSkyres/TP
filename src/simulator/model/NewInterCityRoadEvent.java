package simulator.model;

public class NewInterCityRoadEvent extends NewRoadEvent{

    protected String id;
    protected String srcJuncId;
    protected String destJuncId;
    protected int length;
    protected int co2Limit;
    protected int maxSpeed;
    protected Weather weather;

    public NewInterCityRoadEvent(int time, String id, String srcJun, String destJunc, int length, int co2Limit, int maxSpeed, Weather weather)
    {
        super(time);
        this.id = id;
        this.srcJuncId = srcJun;
        this.destJuncId = destJunc;
        this.length = length;
        this.co2Limit = co2Limit;
        this.maxSpeed = maxSpeed;
        this.weather = weather;
    }

    @Override
    void execute(RoadMap map) throws Exception {
        Junction srcJunc = map.getJunction(this.srcJuncId);
        Junction destJunc = map.getJunction(this.destJuncId);
        Road r = createRoad(srcJunc,destJunc);
        map.addRoad(r);

    }

    Road createRoad(Junction srcJunc, Junction destJunc){
        return new InterCityRoad(this.id,srcJunc,destJunc,this.maxSpeed,this.co2Limit,this.length,this.weather);
    }
}
