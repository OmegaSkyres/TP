package simulator.factories;

import org.json.JSONObject;

public class MoveAllStrategyBuilder extends Builder<MoveAllStrategyBuilder> {
    MoveAllStrategyBuilder() {
        super("most_all_dqs");
    }

    @Override
    protected MoveAllStrategyBuilder createTheInstance(JSONObject data) {
        return new MoveAllStrategyBuilder();
    }
}
