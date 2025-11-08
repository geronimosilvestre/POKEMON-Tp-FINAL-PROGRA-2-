import Enums.ENombre;
import Enums.ETipo;
import Exceptions.archivoYaExisteException;
import Gestoras.Pokedex;
import Menu.Menu;
import Model.Pokemones.Pokemon;
import Utiles.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

// TP GRUPAL POKEDEX (Flores, Jimenez, Pascuan, Silvestre).

public class Main {
    public static void main(String[] args) {


        Pokedex pokedex = new Pokedex();
        Pokemon pikachu = new Pokemon(ENombre.PIKACHU, ETipo.ELECTRICO, 100, 100, 50, 30);
        Pokemon charmander = new Pokemon(ENombre.CHARMANDER, ETipo.FUEGO, 95, 95, 52, 35);
        Pokemon squirtle = new Pokemon(ENombre.SQUIRTLE, ETipo.AGUA, 110, 110, 48, 50);
        Pokemon bulbasaur = new Pokemon(ENombre.BULBASAUR, ETipo.PLANTA, 105, 105, 49, 45);
        Pokemon geodude = new Pokemon(ENombre.GEODUDE, ETipo.ROCA, 120, 120, 45, 60);
        Pokemon growlithe = new Pokemon(ENombre.GROWLITHE, ETipo.FUEGO, 100, 100, 55, 40);
        Pokemon jigglypuff = new Pokemon(ENombre.MAGNETITE, ETipo.HIELO, 115, 115, 45, 20); // ← cambiado
        Pokemon caterpie = new Pokemon(ENombre.CATERPIE, ETipo.PLANTA, 80, 80, 30, 35);
        Pokemon arbok = new Pokemon(ENombre.ARBOK, ETipo.PLANTA, 95, 95, 60, 44);
        Pokemon dragonite = new Pokemon(ENombre.DRAGONITE, ETipo.FUEGO, 150, 150, 85, 70);
        Pokemon magnetite = new Pokemon(ENombre.MAGNETITE, ETipo.ELECTRICO, 90, 90, 55, 35);
        Pokemon snorunt = new Pokemon(ENombre.SNORUNT, ETipo.HIELO, 95, 95, 45, 30);


        JSONArray array = new JSONArray();

        array.put(pikachu.toJSONObject());
        array.put(charmander.toJSONObject());
        array.put(squirtle.toJSONObject());
        array.put(bulbasaur.toJSONObject());
        array.put(geodude.toJSONObject());
        array.put(growlithe.toJSONObject());
        array.put(jigglypuff.toJSONObject());
        array.put(caterpie.toJSONObject());
        array.put(arbok.toJSONObject());
        array.put(dragonite.toJSONObject());
        array.put(magnetite.toJSONObject());
        array.put(snorunt.toJSONObject());

        try {
            pokedex.grabarUnJson(array, "Pokedex.json");
        } catch (archivoYaExisteException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            List<Pokemon> lista = Pokedex.leerPokemonesFromJson("Pokedex.json");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }


//
//        Mochila mochila1 = new Mochila();
//        Mochila mochila2 = new Mochila();
//
//        Pokemon random1 = pokedex.getRandom();
//        Pokemon random2 = pokedex.getRandom();
//        Pokemon random3 = pokedex.getRandom();
//        Pokemon random4 = pokedex.getRandom();
//        Pokemon random5 = pokedex.getRandom();
//        Pokemon random6 = pokedex.getRandom();
//
//        mochila1.agregar(random1);
//        mochila2.agregar(random2);
//        mochila1.agregar(random3);
//        mochila2.agregar(random4);
//        mochila1.agregar(random5);
//        mochila2.agregar(random6);
//
//        Equipos equipos = new Equipos();
//
//        Entrenador entrenador1 = new Entrenador("Joel", "Pascuan");
//        Entrenador entrenador2 = new Entrenador("Elsa", "Popepe");
//
//        equipos.agregarEquipo(entrenador1, mochila1);
//        equipos.agregarEquipo(entrenador2, mochila2);


        //Menu.mainmenu(args);




    }
}