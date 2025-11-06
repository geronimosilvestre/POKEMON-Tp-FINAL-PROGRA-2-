package Gestoras;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;

import java.util.*;

public class GestorEquipos {
    private HashMap<Entrenador, LinkedHashSet<Pokemon>> equipos;

    public GestorEquipos() {
        this.equipos = new HashMap<>();
    }

    public boolean agregarEquipo(Entrenador entrenador, Mochila mochila) {
        equipos.put(entrenador,mochila.obtenerTodos());
        return true;
    }

    public boolean eliminarEquipo(String nombre, String apellido) {

        Entrenador aux = new Entrenador(nombre, apellido);

        for(Entrenador entrenador : equipos.keySet())
        {
            if (entrenador.equals(aux))
            {
                return equipos.remove(entrenador).contains(entrenador);
            }
        }
        return false;
    }
    public LinkedHashSet<Pokemon> getAllPokemonsFromEquipo(String nombre, String apellido )
    {
        Entrenador aux = new Entrenador(nombre,apellido);

        for(Entrenador entrenador : equipos.keySet())
        {
            if(entrenador.equals(aux))
            {
                return equipos.get(entrenador);
            }
        }
        return null;

    }

    public Boolean reemplazarPokemon(String nombre, String apellido,Pokemon pokemonNuevo,String pokemonDescarte)
    {
        Entrenador entrenador = new Entrenador(nombre, apellido);
        Pokemon descarte = new Pokemon(pokemonDescarte);

        for(Entrenador e:equipos.keySet())
        {
            if(e.equals(entrenador))
            {
                equipos.get(e).remove(descarte);
                equipos.get(e).add(pokemonNuevo);

                return true;
            }
        }

        return false;


    }




}

