package Gestoras;

import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Mochila {
    private LinkedHashSet<Pokemon> pokemones;

     public  Mochila()
     {
         this.pokemones = new LinkedHashSet<>();
     }

     public boolean agregar(Pokemon pokemon)
     {
        return pokemones.add(pokemon);
     }

     public boolean eliminar(String nombre)
     {
         Pokemon aux= new Pokemon(nombre);
         if(pokemones.contains(aux))
         {
             return pokemones.remove(aux);
         }
         return false;
     }

     public String listar()
     {
        StringBuilder sb = new StringBuilder();
         for (Pokemon pokemon : pokemones)
         {
             sb.append(pokemon.getNombre()+ "\n");
         }
         return sb.toString();
     }

    public Pokemon getPokemon(String nombre)
    {
        Pokemon aux= new Pokemon(nombre);

        if(pokemones.contains(aux))
        {
            return aux;
        }
        return null;
    }

    public LinkedHashSet<Pokemon> obtenerTodos() {
        return pokemones;
    }

    public boolean booleanoAleatorio() {
        int numRandom = (int) (Math.random() * 1000);
        return numRandom < 500;
    }
}
