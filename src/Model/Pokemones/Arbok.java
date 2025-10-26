package Model.Pokemones;

import Enums.ETipo;
import Model.Pokemon;

import java.util.Objects;

public class Arbok extends Pokemon {

    String nombre;
    ETipo tipo;

    public Arbok(String nombre) {
        super(95, 69, 79, 65,60);
        this.nombre = nombre;
        this.tipo = ETipo.PLANTA;
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
        if (!(o instanceof Arbok arbok)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(super.getId(), arbok.getId());
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

