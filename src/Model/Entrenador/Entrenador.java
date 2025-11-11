package Model.Entrenador;

import Interfaces.IConvertirJSON;
import Model.Entidad;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.UUID;

public final class Entrenador extends Entidad implements IConvertirJSON<JSONObject,Entrenador> {
    String nombre;
    String apellido;

//Constructor que valida que no se ingresen nombres vacios, evita errores mas adelante
    public Entrenador(String nombre, String apellido) throws IllegalArgumentException {
        super();
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }

        this.nombre = nombre;
        this.apellido = apellido;
    }

    //Si tenemos un JSON con un entrenador se usa este constructor para deserializarlo a un objeto entrenador y poder usar el propio UUID que viene desde el archivo

    public Entrenador(UUID uuid,String nombre, String apellido) throws IllegalArgumentException {
        super(uuid);
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }

        this.nombre = nombre;
        this.apellido = apellido;
    }


    public Entrenador() {
        super();
        this.nombre = "";
        this.apellido = "";
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


    //Dos entrenadores con el mismo nombre y apellido no son perimitidos
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entrenador that)) return false;
        return Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido);
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", uuid=" + super.getUuid() +
                '}';
    }

    @Override
    public JSONObject toJSON() {
        JSONObject object = new JSONObject();
        try {
            object.put("uuid", super.getUuid().toString());
            object.put("nombre", nombre);
            object.put("apellido", apellido);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public   Entrenador fromJSON(JSONObject jsonObject) {

        try{

            UUID uuid = UUID.fromString(jsonObject.getString("uuid"));
            String nombre = jsonObject.getString("nombre");
            String apellido = jsonObject.getString("apellido");

            return new Entrenador(uuid, nombre, apellido);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
