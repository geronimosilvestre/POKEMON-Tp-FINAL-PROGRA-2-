package Gestoras;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;

import java.util.*;

public class GestorEquipos {
    private HashMap<Entrenador, Mochila> equipos;

    public GestorEquipos() {
        this.equipos = new HashMap<>();
    }

    public boolean agregarEquipo(Entrenador entrenador, Mochila mochila) {
        equipos.put(entrenador,mochila);
        return true;
    }

    public boolean eliminarEquipo(String nombre, String apellido) {

        Entrenador aux = new Entrenador(nombre, apellido);

        for(Entrenador entrenador : equipos.keySet())
        {
            if (entrenador.equals(aux))
            {
                 equipos.remove(entrenador);
                 return true;
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
                return equipos.get(entrenador).obtenerTodos();
            }
        }
        return null;

    }

    public Boolean reemplazarPokemon(String nombre, String apellido,Pokemon pokemonNuevo,String pokemonDescarte)
    {
        Entrenador entrenador = new Entrenador(nombre, apellido);

        for(Entrenador e:equipos.keySet())
        {
            if(e.equals(entrenador))
            {
                equipos.get(e).eliminar(pokemonDescarte);
                equipos.get(e).agregar(pokemonNuevo);

                return true;
            }
        }

        return false;


    }




}

