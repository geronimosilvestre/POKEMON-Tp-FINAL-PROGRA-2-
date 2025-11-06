package Interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface IConvertirJSON<T> {
    public JSONObject toJSONObject();
    public T fromJSON(JSONObject jsonObject);

}
