package simulator.model;

import simulator.exceptions.WrongValuesException;
import simulator.exceptions.WrongValuesJuntion;

public class NewJunctionEvent extends Event {
    protected String id;
    protected LightSwitchStrategy lsStrategy;
    protected DequeingStrategy dqStrategy;
    protected int xCoor;
    protected int yCoor;

    public NewJunctionEvent(int time, String id, LightSwitchStrategy lsStrategy, DequeingStrategy dqStrategy, int xCoor, int yCoor) {
        super(time);
        this.id = id;
        this.lsStrategy = lsStrategy;
        this.dqStrategy = dqStrategy;
        this.xCoor = xCoor;
        this.yCoor = yCoor;


    }

    @Override
    void execute(RoadMap map) throws WrongValuesException, WrongValuesJuntion {
        map.addJuntion(createJuntion());
    }

    @Override
    public String toString() {
        return "New Juntion '"+id+"'";
    }

    protected Junction createJuntion() throws WrongValuesException {
        return new Junction(this.id,this.lsStrategy,this.dqStrategy,this.xCoor,this.yCoor);
    }


}
