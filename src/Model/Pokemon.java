package Model;

import Interfaces.ICapturar;

import java.util.Objects;

public abstract class Pokemon implements ICapturar {
    private static int contadorID = 0;
    private int id;
    private int ataque;
    private int defense;
    private int defensaEspecial;
    private int ataqueEspecial;
    private int hitPoints;
    private boolean capturado ; // por defecto no estan capturados

    public Pokemon(int ataque, int defense, int defensaEspecial, int ataqueEspecial, int hitPoints) {
        contadorID++;
        this.id = contadorID;
        this.hitPoints = hitPoints;
        this.ataque = ataque;
        this.defense = defense;
        this.defensaEspecial = defensaEspecial;
        this.ataqueEspecial = ataqueEspecial;
        this.capturado = false;
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

    private void setCapturado() {
        this.capturado = true;
    }

    public boolean capturarPokemon() {  // si el numero es menor a 500 se captura
        int numRandom = (int) (Math.random() * 1000);
        if (numRandom < 500) {
            setCapturado();
            return true;
        }
        return false;
    }
}
