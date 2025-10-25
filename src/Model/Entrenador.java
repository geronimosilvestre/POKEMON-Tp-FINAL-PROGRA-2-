package Model;

import java.util.Objects;
import java.util.UUID;

public class Entrenador {
    String nombre;
    String apellido;
    UUID uuid;

    public Entrenador(String nombre, String apellido) {
        this.uuid = UUID.randomUUID();
        this.nombre = nombre;
        this.apellido = apellido;
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

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entrenador that)) return false;
        return Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, uuid);
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}
