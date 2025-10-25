package Model;

import java.util.Objects;

public abstract class Pokemon {
    private static int contadorID = 0;
    private int id;
    private int ataque;
    private int defense;
    private int defensaEspecial;
    private int ataqueEspecial;
    private int hitPoints;

    public Pokemon(int ataque, int defense, int defensaEspecial, int ataqueEspecial, int hitPoints) {
        contadorID++;
        this.id = contadorID;
        this.hitPoints = hitPoints;
        this.ataque = ataque;
        this.defense = defense;
        this.defensaEspecial = defensaEspecial;
        this.ataqueEspecial = ataqueEspecial;
    }

    public int getId() {
        return id;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefense() {
        return defense;
    }

    public int getDefensaEspecial() {
        return defensaEspecial;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pokemon pokemon)) return false;
        return id == pokemon.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", ataque=" + ataque +
                ", defense=" + defense +
                ", defensaEspecial=" + defensaEspecial +
                ", ataqueEspecial=" + ataqueEspecial +
                '}';
    }
}
