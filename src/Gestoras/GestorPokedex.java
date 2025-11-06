package Gestoras;

import Model.Pokemones.Pokemon;

import java.util.ArrayList;

public class GestorPokedex{

    private ArrayList<Pokemon> pokemones;
    public GestorPokedex() {
        pokemones = new ArrayList<>();
    }

    public boolean agregarPokemon(Pokemon pokemon) {
        pokemones.add(pokemon);
        return true;
    }

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
