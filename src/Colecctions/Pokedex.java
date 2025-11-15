package Colecctions;

import Exceptions.archivoYaExisteException;

import Exceptions.existException;
import Model.Pokemones.Pokemon;
import Utiles.Contenedor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static Utiles.JsonUtiles.grabarUnJson;
import static Utiles.JsonUtiles.leerUnJson;

public class Pokedex extends Contenedor<Pokemon> {

    private ArrayList<Pokemon> pokemones;
    //En la pokedex se guardaran todos los pokemones existentes posibles para capturarlos desde aqui y para tener una lista completa de los que hay
    public Pokedex() {
        pokemones = new ArrayList<>();
    }


    //Agrega  pokemones al ararylist sin repetidos
    public boolean agregar(Pokemon pokemon) throws existException {
        if (!pokemones.contains(pokemon)) {
            agregarObjeto(pokemon);
            return true;
        } else {
            throw new existException("El Pokemon no se puede repetir, intente con otro");
        }
    }

    @Override
    public boolean eliminarObjeto(Pokemon objeto) {
        return super.eliminarObjeto(objeto);
    }
    //retornar un pokemon al buscarlo por el indice de la arraylist

    public Pokemon buscar(int posicion) {
        return pokemones.get(posicion);
    }


//    //retorna la arraylist para poder usarla
//    public ArrayList<Pokemon> obtenerTodos() {
//        return pokemones;
//    }

    //Muestra todos los pokemones junto al indice de la arraylist
    public String listar( )  {
        StringBuilder sb = new StringBuilder();
        int contador = 0;
        for(Pokemon pokemon : pokemones){
            sb.append("["+ contador  + "]");
           sb.append(pokemon.getNombre());
           sb.append("\n");
           contador++;
        }
        return sb.toString();

    }

    //Obtiene un pokemon random a partir de calcular un numero usando el tamanio total del arraylist, esto se aprovecha en la batalla
    public Pokemon getRandom() {
        return pokemones.get((int)(Math.random() * pokemones.size()));
    }


    //Recibe un JsonArray y el nombre del archivo , el array se crea a mano a partir de meter los pokemoneeeeeeeeeee

    public  void grabar(JSONArray jsonArray, String archivo) throws archivoYaExisteException {
        File file = new File(archivo);

        if (file.exists()) {
            throw new archivoYaExisteException("El archivo ya existe: " + archivo);
        }

        grabarUnJson(jsonArray, archivo);
    }



    public  List<Pokemon> leer(String archivo) throws JSONException {
        List<Pokemon> pokemones = new ArrayList<>();


            JSONTokener tokener = null;
            try{
                tokener = leerUnJson(archivo);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

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
