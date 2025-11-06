package Model.Entrenador;

import Interfaces.IConvertirJSON;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.UUID;

public class Entrenador implements IConvertirJSON<Entrenador> {
    String nombre;
    String apellido;
    UUID uuid;

    public Entrenador(String nombre, String apellido) {
        this.uuid = UUID.randomUUID();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entrenador)) return false;
        Entrenador that = (Entrenador) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", uuid=" + uuid +
                '}';
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("uuid", uuid.toString());
            object.put("nombre", nombre);
            object.put("apellido", apellido);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public static Entrenador fromJSON(JSONObject jsonObject) {
        Entrenador generico = new Entrenador();
        try{
            generico.uuid = UUID.fromString(jsonObject.getString("uuid"));
            generico.nombre = jsonObject.getString("nombre");
            generico.apellido = jsonObject.getString("apellido");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return generico;
    }
}
