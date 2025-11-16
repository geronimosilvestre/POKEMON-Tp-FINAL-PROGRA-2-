package Gestoras;

import Colecctions.Pokedex;
import Exceptions.notPokemonFoundException;
import Model.Pokemones.Pokemon;

public class Capturar {
    public Capturar() {
    }


    public Pokemon encontrarPokemon (Pokedex pokedex) {
            return pokedex.getRandom();
    }


    public boolean capturar (Pokemon pokemon) throws notPokemonFoundException
    {
        if (pokemon==null){
            throw new notPokemonFoundException("No se encontro al pokemon");
        }
        if (pokemon.getVidaRestante() <= 0) {
            throw new IllegalStateException("El Pokémon está debilitado, no se puede capturar.");
        }

        double vida= 1 - (Math.random() / pokemon.getVidaCompleta()); //0.43

        double tipo=pokemon.getTipo().getPoderBase()/100.0;//0.5

        double chance= (tipo)*(vida);

        if (chance < 0) chance = 0;
        if (chance > 1) chance = 1;


        double porcFinal=Math.random();

        return  porcFinal <=chance;

    }  public boolean capturarBatalla (Pokemon pokemon) throws notPokemonFoundException
    {
        if (pokemon==null){
            throw new notPokemonFoundException("No se encontro al pokemon");
        }
        if (pokemon.getVidaRestante() <= 0) {
            throw new IllegalStateException("El Pokémon está debilitado, no se puede capturar.");
        }

        double vida= 1 - (pokemon.getVidaRestante() / pokemon.getVidaCompleta()); //0.43

        double tipo=pokemon.getTipo().getPoderBase()/100.0;//0.5

        double chance= (tipo)*(vida);

        if (chance < 0) chance = 0;
        if (chance > 1) chance = 1;


        double porcFinal=Math.random();

        return  porcFinal <=chance;
    }

}
