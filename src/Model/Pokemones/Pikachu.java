package Model.Pokemones;

import Enums.ETipo;
import Model.Pokemon;

import java.util.Objects;

public class Pikachu extends Pokemon {

    String nombre;
    ETipo tipo;

    public Pikachu(int ataque, int defense, int defensaEspecial, int ataqueEspecial, String nombre) {
        super(ataque, defense, defensaEspecial, ataqueEspecial);
        this.nombre = nombre;
        this.tipo = ETipo.Eléctrico;
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
        if (!(o instanceof Pikachu pikachu)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(super.getId(), pikachu.getId());
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
