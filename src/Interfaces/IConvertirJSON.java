package Interfaces;

import org.json.JSONException;

public interface IConvertirJSON< J, T> {
    public J toJSON();

    public  T fromJSON(J jsonObjetOArray) throws JSONException;


}
