package Gestoras;

import Model.Pokemones.Pokemon;

import java.util.ArrayList;

public class Pokedex{

    private ArrayList<Pokemon> pokemones;
    public Pokedex() {
        pokemones = new ArrayList<>();
    }

    public boolean agregar(Pokemon pokemon) {
        pokemones.add(pokemon);
        return true;
    }

    public Pokemon obtener(int posicion) {
        return pokemones.get(posicion);
    }

    public ArrayList<Pokemon> obtenerTodos() {
        return pokemones;
    }

    public int tamanioActual() {
        return pokemones.size();
    }


}
