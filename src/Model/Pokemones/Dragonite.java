package Model.Pokemones;

import Enums.ETipo;
import Model.Pokemon;

import java.util.Objects;

public class Dragonite extends Pokemon {

    String nombre;
    ETipo tipo;

    public Dragonite(String nombre) {
        super(134, 95, 100, 100, 91);
        this.nombre = nombre;
        this.tipo = ETipo.ROCA;
    }

    public Dragonite() {
        super(134, 95, 100, 100, 91);
        this.nombre = "dragonite";
        this.tipo = ETipo.ROCA;
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
        if (!(o instanceof Dragonite dragonite)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(super.getId(), dragonite.getId());
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

