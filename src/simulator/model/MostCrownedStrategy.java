package simulator.model;

import java.util.List;

public class MostCrownedStrategy implements LightSwitchingStrategy{
    public MostCrownedStrategy(int value){

    }

    @Override
    public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime, int currTime) {
        return 0;
    }
}
