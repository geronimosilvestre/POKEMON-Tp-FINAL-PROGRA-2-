package Colecctions;

import Exceptions.*;
import Interfaces.IConvertirJSON;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class Equipos implements IConvertirJSON<JSONArray,Equipos> {
    private HashMap<Entrenador, Mochila> equipos;

    public Equipos() {
        this.equipos = new HashMap<>();
    }

    // Dentro del map puede haber solo 2 equipos, cada equipo es un conjunto de clave entrenado y valor mochila(mochila tiene pokemones)
    public boolean agregarEquipo(Entrenador entrenador, Mochila mochila) throws existException, capacidadInvalidaException, mochilaVaciaException {

        if(this.equipos.containsKey(entrenador)) {
            throw new existException("Entrenador ya existe");
        }
        if(mochila.size()== 0)
        {
            throw new mochilaVaciaException("Mochila vacia");
        }

        int cantidadDeEquipos = size();
        if (cantidadDeEquipos >2) {
            throw new capacidadInvalidaException("No se puede agregar mas de 2 equipos");
        }

        equipos.put(entrenador,mochila);
        return true;
    }

    public int size() {
        return this.equipos.size();
    }

    //Elimina el equipo entero a partir del nombre y apellido del entrenador
    public boolean eliminarEquipo(String nombre, String apellido) throws existException {

        Entrenador aux = new Entrenador(nombre, apellido);
        if(!this.equipos.containsKey(aux)) {
            throw new existException("Entrenador no encontrado");
        }

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

    //Obtener mochila a partir del nombre y apellido del entrenador
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
//Obtener un entrenador en particular a partir de su nombre y apellido
    public Entrenador getEntrenador(String nombre, String apellido)
    {

        Entrenador aux = new Entrenador(nombre, apellido);
        for (Entrenador entrenador : equipos.keySet()) {
            if (entrenador.equals(aux))
                return entrenador;
        }
        return null;

    }
//Obtener todos los entrenadores para cuando quiero obtener la mochila de cada uno sin saber su nombre
    public ArrayList<Entrenador> getEntrenadores()
    {
        ArrayList<Entrenador> entrenadores = new ArrayList<>();
        for (Entrenador entrenador : equipos.keySet()) {
            entrenadores.add(entrenador);
        }
        return entrenadores;

    }
//Reemplazar pokemon para dentro de la batalla a partir del equipo
    public Boolean reemplazarPokemon(String nombre, String apellido, Pokemon pokemonNuevo, String pokemonDescarte) throws capacidadInvalidaException, existException {
        Entrenador entrenador = new Entrenador(nombre, apellido);

        for (Entrenador e : equipos.keySet()) {
            if (e.equals(entrenador)) {
                equipos.get(e).eliminar(pokemonDescarte);
                 equipos.get(e).agregar(pokemonNuevo);
                return true;
            }
        }

        return false;


    }
//Lista los nombres de los entrenadores junto a los pokemones dentro su mochila
    public String listar() throws capacidadInvalidaException,existException {
        StringBuilder sb = new StringBuilder();
        if (this.equipos.isEmpty()) {
            throw new capacidadInvalidaException("No se encontraron equipos");
        }
        int contador = 0;
        for (Map.Entry<Entrenador, Mochila> entry : equipos.entrySet()) {
            String nombreEntrenador = entry.getKey().getNombre();
            String apellidoEntrenador = entry.getKey().getApellido();
            String nombrePokemones = entry.getValue().listar();
            contador++;
            sb.append("Equipo " + contador).append("\n").append(nombreEntrenador).append(" " + apellidoEntrenador).append("\n").append(nombrePokemones);


        }

        return sb.toString();
    }


    @Override
    public JSONArray toJSON() {

        JSONArray arrayContenedor=new JSONArray();

        for (Map.Entry<Entrenador, Mochila> entry : equipos.entrySet()) {

            JSONArray arrayPokemones= new JSONArray();

            JSONArray arrayEquipoIndividual=new JSONArray();

            JSONObject entrenador=new JSONObject();

            entrenador=entry.getKey().toJSON();

            arrayEquipoIndividual.put(entrenador);

            for(Pokemon p: entry.getValue().obtenerTodos()){

                arrayPokemones.put(p.toJSON());

            }
            arrayEquipoIndividual.put(arrayPokemones);
            arrayContenedor.put(arrayEquipoIndividual);
        }

        return arrayContenedor;
    }

    @Override
    public  Equipos fromJSON(JSONArray arrayContenedor)  throws JSONException  {
        Equipos equipos = new Equipos();

        for (int i = 0; i < arrayContenedor.length(); i++) {
            JSONArray arrayEquipoIndividual = arrayContenedor.getJSONArray(i);

            // Primer elemento del array es el entrenador
            JSONObject jsonEntrenador = arrayEquipoIndividual.getJSONObject(0);
            Entrenador entrenador = new Entrenador().fromJSON(jsonEntrenador);

            // Segundo elemento es el array de pokemones
            JSONArray arrayPokemones = arrayEquipoIndividual.getJSONArray(1);
            Mochila mochila = new Mochila();

            for (int j = 0; j < arrayPokemones.length(); j++) {
                JSONObject jsonPokemon = arrayPokemones.getJSONObject(j);

                try {
                    Pokemon pokemon = new Pokemon().fromJSON(jsonPokemon);
                    mochila.agregar(pokemon);
                } catch (capacidadInvalidaException e) {
                    System.out.println(e.getMessage());
                } catch (existException e) {
                    System.out.println(e.getMessage());
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }

            // Agregar al map
            try {
                equipos.agregarEquipo(entrenador, mochila);
            } catch (existException e) {

                System.out.println(e.getMessage());
            } catch (capacidadInvalidaException e) {

                System.out.println(e.getMessage());
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return equipos;
    }

}

