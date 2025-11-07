package Model.Pokemones;

import Enums.ETipo;
import Model.Pokemon;

import java.util.Objects;

public class Charmander extends Pokemon {

    String nombre;
    ETipo tipo;

    public Charmander(String nombre) {
        super(52, 43, 60, 50,39);
        this.nombre = nombre;
        this.tipo = ETipo.FUEGO;
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
        if (!(o instanceof Caterpie charmander)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(super.getId(), charmander.getId());
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
