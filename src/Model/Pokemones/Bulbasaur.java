package Model.Pokemones;

import Enums.ETipo;
import Model.Pokemon;

import java.util.Objects;

public class Bulbasaur extends Pokemon {

    String nombre;
    ETipo tipo;

    public Bulbasaur(int ataque, int defense, int defensaEspecial, int ataqueEspecial, String nombre) {
        super(ataque, defense, defensaEspecial, ataqueEspecial);
        this.nombre = nombre;
        this.tipo = ETipo.Planta; //esto tendria que venir definido
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
        if (!(o instanceof Bulbasaur bulbasaur)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(super.getId(), bulbasaur.getId());
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

