package Gestoras;

import Exceptions.archivoYaExisteException;
import Model.Pokemones.Pokemon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static Utiles.JsonUtiles.grabarUnJson;
import static Utiles.JsonUtiles.leerUnJson;

public class Pokedex{

    private ArrayList<Pokemon> pokemones;
    public Pokedex() {
        pokemones = new ArrayList<>();
    }

    public boolean agregar(Pokemon pokemon) {
        pokemones.add(pokemon);
        return true;
    }

    public Pokemon buscar(int posicion) {
        return pokemones.get(posicion);
    }

    public ArrayList<Pokemon> obtenerTodos() {
        return pokemones;
    }
    public String listar(){
        StringBuilder sb = new StringBuilder();
        for(Pokemon pokemon : pokemones){

           sb.append(pokemon.getNombre());
           sb.append("\n");
        }
        return sb.toString();

    }
    public int tamanioActual() {
        return pokemones.size();
    }

    public Pokemon getRandom() {
        return pokemones.get((int)(Math.random() * pokemones.size()));
    }


    public  void grabar(JSONArray jsonArray, String archivo) throws archivoYaExisteException {
        File file = new File(archivo);

        if (file.exists()) {
            throw new archivoYaExisteException("El archivo ya existe: " + archivo);
        }

        grabarUnJson(jsonArray, archivo);
    }



    public static List<Pokemon> leer(String archivo) throws JSONException {
        List<Pokemon> pokemones = new ArrayList<>();


            JSONTokener tokener = leerUnJson(archivo);
            if (tokener == null) {
                throw new JSONException("No se pudo leer el archivo: " + archivo);
            }

            JSONArray array = new JSONArray(tokener);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonPokemon = array.getJSONObject(i);
                Pokemon generico = new Pokemon();
                pokemones.add(generico.fromJSON(jsonPokemon));
            }


        return pokemones;
    }



}
