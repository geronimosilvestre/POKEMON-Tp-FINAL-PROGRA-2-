package Model.Pokemones;

import Enums.ETipo;
import Model.Pokemon;

import java.util.Objects;

public class Squirtle extends Pokemon {

    String nombre;
    ETipo tipo;

    public Squirtle(int ataque, int defense, int defensaEspecial, int ataqueEspecial, String nombre, ETipo tipo) {
        super(ataque, defense, defensaEspecial, ataqueEspecial);
        this.nombre = nombre;
        this.tipo = ETipo.Agua;
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
        if (!(o instanceof Squirtle squirtle)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(super.getId(), squirtle.getId());
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