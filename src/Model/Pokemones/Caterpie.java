package Model.Pokemones;

import Enums.ETipo;
import Model.Pokemon;

import java.util.Objects;

public class Caterpie extends Pokemon {

    String nombre;
    ETipo tipo;

    public Caterpie(String nombre) {
        super(30, 35, 20, 20,45);
        this.nombre = nombre;
        this.tipo = ETipo.Bicho;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public ETipo getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Caterpie caterpie)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(super.getId(), caterpie.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        return "Pikachu{" +
                "nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                "} " + super.toString();
    }


}

