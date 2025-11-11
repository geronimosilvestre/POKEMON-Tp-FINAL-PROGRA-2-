package Colecctions;

import Enums.ENombre;
import Exceptions.*;
import Model.Pokemones.Pokemon;

import java.util.LinkedHashSet;

public class Mochila {
    private LinkedHashSet<Pokemon> pokemones;

     public  Mochila()
     {
         this.pokemones = new LinkedHashSet<>();
     }

     //La mochila solo acepta pokemones y puede contener hasta 3 de cada uno

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

    //Eliminar de la mochila por el nombre del pokemon en String

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

     //Muestra todos los pokemones de la mochila y  la posicion en la que aparecen para que el usuario vea el indice

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

     //Obtener un pokemon mediante su nombre
    public Pokemon getPokemon(String nombre) throws existException,IllegalArgumentException
    {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre vacío");
        }

        ENombre nombreEnum = null;
        //Chequear si existe realmente un pokemon a partir del String


        for (ENombre n : ENombre.values()) {
            if (n.name().equalsIgnoreCase(nombre.trim())) {
                nombreEnum = n;
                break;
            }
        }
        if (nombreEnum == null) {
            throw new IllegalArgumentException("Nombre ingresado inexistente: " + nombre);
        }

        Pokemon aux = new Pokemon(nombreEnum);
        for (Pokemon pokemon : pokemones) {
            if (pokemon.equals(aux)) {
                return pokemon;
            }
        }
        throw new existException("No existe ese pokemon en la mochila: " + nombre);
    }
    //Obtener pokemon gracias al indice de listar
    public Pokemon getPokemonIndex(int indice) throws capacidadInvalidaException,noIndexFoundException {
        if(indice > 2|| indice < 0){
            throw new noIndexFoundException("No existe un pokemon con ese indice");
        }
         int contador = 0;
        if (indice < 0 || indice >= pokemones.size()) {
            throw new capacidadInvalidaException("No existe un pokemon con ese indice");
        }
        for (Pokemon p : pokemones) {
            if (contador == indice) {
                return p;
            }
            contador++;
        }
        throw new noIndexFoundException("No existe un pokemon con ese indice");
    }

    public int size()
    {
        return pokemones.size();
    }
//Obtener todos los pokemones al retornar la coleccion para poder usarlos
    public LinkedHashSet<Pokemon> obtenerTodos() {
        return pokemones;
    }

//    public boolean booleanoAleatorio() {
//        int numRandom = (int) (Math.random() * 1000);
//        return numRandom < 500;
//    }
}
