package Interfaces;

import Exceptions.capacidadInvalidaException;
import Exceptions.existException;
import Gestoras.Equipos;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface IConvertirJSON< J, T> {
    public J toJSON();
    public T fromJSON(J jsonObject);


}
