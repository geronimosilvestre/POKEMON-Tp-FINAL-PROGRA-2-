package Gestoras;

import Colecctions.Pokedex;
import Model.Pokemones.Pokemon;

public class Capturar {

    public Pokemon encontrarPokemon (Pokedex pokedex) {
            return pokedex.getRandom();
    }

}
