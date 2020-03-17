package simulator.model;

import java.util.List;

public class MoveFirstStrategy implements DequeingStrategy {

    @Override
    public List<Vehicle> dequeue(List<Vehicle> q) {
        return q.subList(0,1);
    }
}
