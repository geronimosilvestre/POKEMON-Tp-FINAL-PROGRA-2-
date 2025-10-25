package Model.Pokemones;

import Enums.ETipo;
import Model.Pokemon;

import java.util.Objects;

public class Jigglypuff extends Pokemon {

    String nombre;
    ETipo tipo;

    public Jigglypuff(String nombre) {
        super(45, 20, 25, 25);
        this.nombre = nombre;
        this.tipo = ETipo.Normal; //esto tendria que venir definido
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
        if (!(o instanceof Jigglypuff jigglypuff)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(super.getId(), jigglypuff.getId());
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

