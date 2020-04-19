package simulator.factories;

import org.json.JSONObject;
import simulator.exceptions.WrongValuesContamination;
import simulator.exceptions.WrongValuesWeather;

public interface Factory<T> {
	public T createInstance(JSONObject info) throws Exception;
}
