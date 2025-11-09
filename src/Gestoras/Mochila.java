package Gestoras;

import Exceptions.*;
import Interfaces.IBatalla;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Mochila<T extends IBatalla> {
    private LinkedHashSet<Pokemon> pokemones;

     public  Mochila()
     {
         this.pokemones = new LinkedHashSet<>();
     }

     public boolean agregar(Pokemon pokemon) throws  capacidadInvalidaException, existException
     {
         if (pokemones.size() == 3) {
             throw new capacidadInvalidaException("La mochila no puede tener mas de 3 pokemones");
         }
         if (pokemones.size() < 3) {


             if (pokemones.contains(pokemon)) {
                 throw new existException("Pokemon ya existe");
             }
                 return pokemones.add(pokemon);

         }
        return  false;
     }

    public boolean eliminar(String nombre) throws capacidadInvalidaException, existException {
        Pokemon aux = new Pokemon(nombre);
        if (pokemones.size() == 0) {
            throw new capacidadInvalidaException("Mochila vacia");
        }
        if (!pokemones.contains(aux)) {
        throw new existException("No existe ese pokemon en la mochila");

        }

        return pokemones.remove(aux);

     }

     public String listar() throws capacidadInvalidaException
     {
        StringBuilder sb = new StringBuilder();
        if (pokemones.size() == 0) {
            throw new capacidadInvalidaException("Mochila vacia");
        }
         int contador = 0;

         for (Pokemon p : pokemones) {
             sb.append("[").append(contador).append("] ")
                     .append(p.getNombre()).append("\n");
             contador++;
         }
         return sb.toString();
     }

    public Pokemon getPokemon(String nombre)
    {
        Pokemon aux= new Pokemon(nombre);

        for(Pokemon pokemon : pokemones)
        {
            if(pokemon.equals(aux))
                {
                return pokemon;
                }
        }
        return null;
    }
    public Pokemon getPokemonIndex(int indice) throws capacidadInvalidaException {
        int contador = 0;
        if (indice < 0 || indice >= pokemones.size()) {
            throw new capacidadInvalidaException("No existe un pokemon con ese indice")
        }
        for (Pokemon p : pokemones) {
            if (contador == indice) {
                return p;
            }
            contador++;
        }
        return null;
    }

    public int size()
    {
        return pokemones.size();
    }
    public LinkedHashSet<Pokemon> obtenerTodos() {
        return pokemones;
    }

    public boolean booleanoAleatorio() {
        int numRandom = (int) (Math.random() * 1000);
        return numRandom < 500;
    }
}
