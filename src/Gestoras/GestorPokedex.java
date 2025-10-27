package Gestoras;

import Model.Pokemon;
import Model.Pokemones.*;

import java.util.ArrayList;
import java.util.List;

public class GestorPokedex{

    private ArrayList<Pokemon> pokemones;
    public GestorPokedex() {pokemones = new ArrayList<>();}


    public ArrayList<Pokemon> getPokemones() {
        return pokemones;
    }

    public Pokemon getPokemonEspecifico(int posicion) {
        return pokemones.get(posicion);
    }

    public int pokedexSize() {
        return pokemones.size();
    }
}
