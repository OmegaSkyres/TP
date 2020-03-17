package simulator.model;

import java.util.List;

public class MostCrownedStrategy implements LightSwitchStrategy {
    private int time;
    public MostCrownedStrategy(int value){
        time = value;
    }

    private int busqueda(int inicio, List<List<Vehicle>> qs){
        int maximo = inicio;
        for(int i = 0; i < qs.size(); i++) {
            if(qs.get((i + inicio) % qs.size()).size() > qs.get(maximo).size()) maximo = (i + inicio) % qs.size();
        }
        return  maximo;
    }

    @Override
    public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime, int currTime) {
        if(roads.isEmpty()) return -1;
        else if (currGreen == -1) return busqueda(0,qs);
        else if (currTime-lastSwitchingTime <time) return currGreen;
        else return busqueda(currGreen + 1,qs);
    }
}
