package simulator.model;

import java.util.List;

public class RoundRobinStrategy implements LightSwitchStrategy {

    private int time;

    public RoundRobinStrategy(int value){
        time = value;
    }

    @Override
    public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime, int currTime) {
        if (roads == null) {
            return -1;
        } else if (currGreen == -1) {
            return 0;
        } else if (currTime - lastSwitchingTime < time) {
            return currGreen;
        } else return (currGreen + 1) % roads.size();
    }
}
