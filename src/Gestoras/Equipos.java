package Gestoras;

import Exceptions.equiposCompletosException;
import Exceptions.nombreYaEncontradoException;
import Exceptions.vacioException;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;

import java.util.*;

public class Equipos {
    private HashMap<Entrenador, Mochila> equipos;

    public Equipos() {
        this.equipos = new HashMap<>();
    }

    public boolean agregarEquipo(Entrenador entrenador, Mochila mochila) throws nombreYaEncontradoException, equiposCompletosException {
        if(this.equipos.containsKey(entrenador)) {
            throw new equiposCompletosException("Equipo ya existe");
        }

        int cantidadDeEquipos = contar();
        if (cantidadDeEquipos >2) {
            throw new nombreYaEncontradoException("No se puede agregar mas de 2 equipos");
        }

        equipos.put(entrenador,mochila);
        return true;
    }

    public int contar() {
        return this.equipos.size();
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
    public Mochila getMochila(String nombre, String apellido )
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

    public Entrenador getEntrenador(String nombre, String apellido)
    {

        Entrenador aux = new Entrenador(nombre, apellido);
        for (Entrenador entrenador : equipos.keySet()) {
            if (entrenador.equals(aux))
                return entrenador;
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

    public String listar() throws vacioException {
        StringBuilder sb = new StringBuilder();
        if (this.equipos.isEmpty()) {
            throw new vacioException("No se encontraron equipos");
        }
        int contador = 0;
        for (Map.Entry<Entrenador, Mochila> entry : equipos.entrySet()) {
            String nombreEntrenador = entry.getKey().getNombre();
            String nombrePokemones = entry.getValue().listar();
            contador++;
            sb.append("Equipo " + contador).append("\n").append(nombreEntrenador).append("\n").append(nombrePokemones);


        }

        return sb.toString();
    }




}

