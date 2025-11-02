package Model;

import Interfaces.IBatalla;
import Interfaces.ICapturar;

import java.util.Objects;

public abstract class Pokemon implements ICapturar {
    private static int contadorID = 0;
    private int id;
    private int vida;
    private int ataque;
    private int ataqueEspecial;
    private int defensa;
    private int defensaEspecial;
    private boolean elegidoParaPelear;


    public Pokemon(int vida,int ataque, int ataqueEspecial, int defensa, int  defensaEspecial) {
        contadorID++;
        this.id = contadorID;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.elegidoParaPelear = false;
    }

    public int getId() {
        return id;
    }

    public boolean isElegidoParaPelear() {
        return elegidoParaPelear;
    }

    public void setElegidoParaPelear(boolean elegidoParaPelear) {
        this.elegidoParaPelear = elegidoParaPelear;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }


    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }


    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getDefensaEspecial() {
        return defensaEspecial;
    }

    public void setDefensaEspecial(int defensaEspecial) {
        this.defensaEspecial = defensaEspecial;
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

    //funcion para capturar pokemon, si el numero es menor a 500 se captura
    public boolean capturarPokemon() {
        int numRandom = (int) (Math.random() * 1000);
        return numRandom < 500;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", vida=" + vida +
                ", ataque=" + ataque +
                ", ataqueEspecial=" + ataqueEspecial +
                ", defensa=" + defensa +
                '}';
    }
}
