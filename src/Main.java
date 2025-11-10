import Enums.ENombre;
import Exceptions.archivoYaExisteException;
import Exceptions.capacidadInvalidaException;
import Exceptions.emptyNameException;
import Exceptions.existException;

import Colecctions.Equipos;

import Colecctions.Mochila;
import Colecctions.Pokedex;
import Gestoras.GestorDamage;
import Gestoras.GestorJuego;
import Menu.Menu;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;
import Utiles.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.Scanner;


import static Gestoras.GestorDamage.seleccionarNuevoPokemon;
import static Gestoras.GestorJuego.menuBatalla;


// TP GRUPAL POKEDEX (Flores, Jimenez, Pascuan, Silvestre).

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        Pokedex pokedex = new Pokedex();
        Pokemon pikachu = new Pokemon(ENombre.PIKACHU);
        Pokemon charmander = new Pokemon(ENombre.CHARMANDER);
        Pokemon squirtle = new Pokemon(ENombre.SQUIRTLE);
        Pokemon bulbasaur = new Pokemon(ENombre.BULBASAUR);
        Pokemon geodude = new Pokemon(ENombre.GEODUDE);
        Pokemon growlithe = new Pokemon(ENombre.GROWLITHE);
        Pokemon jigglypuff = new Pokemon(ENombre.JIGGLYPUFF);
        Pokemon caterpie = new Pokemon(ENombre.CATERPIE);
        Pokemon arbok = new Pokemon(ENombre.ARBOK);
        Pokemon dragonite = new Pokemon(ENombre.DRAGONITE);
        Pokemon magnetite = new Pokemon(ENombre.MAGNETITE);
        Pokemon snorunt = new Pokemon(ENombre.SNORUNT);


        JSONArray array = new JSONArray();

        array.put(pikachu.toJSON());
        array.put(charmander.toJSON());
        array.put(squirtle.toJSON());
        array.put(bulbasaur.toJSON());
        array.put(geodude.toJSON());
        array.put(growlithe.toJSON());
        array.put(jigglypuff.toJSON());
        array.put(caterpie.toJSON());
        array.put(arbok.toJSON());
        array.put(dragonite.toJSON());
        array.put(magnetite.toJSON());
        array.put(snorunt.toJSON());

    Equipos equipos1= new Equipos();

    Entrenador entrenador1 = new Entrenador("JoeLPrueba","Pascuan");
    Entrenador entrenador2 = new Entrenador("Valentina","Jimenez");
    Mochila mochila1 = new Mochila();
        try {
            mochila1.agregar(arbok);
            mochila1.agregar(pikachu);
            mochila1.agregar(charmander);
            equipos1.agregarEquipo(entrenador1,mochila1);
            equipos1.agregarEquipo(entrenador2,mochila1);

        } catch (capacidadInvalidaException e) {
            throw new RuntimeException(e);
        } catch (existException e) {
            throw new RuntimeException(e);
        } catch (emptyNameException e) {
            throw new RuntimeException(e);
        }
        JsonUtiles.grabarUnJson(equipos1.toJSON(), "Equipos.json");




        // SE GRABAN POKEMONS EN JSON
        try {
            pokedex.grabar(array, "Pokedex.json");
        } catch (archivoYaExisteException e) {
            System.out.println( e.getMessage());
        }

        ArrayList<Pokemon> lista = new ArrayList<>();

        //LEEN POKEMON DE JSON
        try {
             lista = (ArrayList<Pokemon>) pokedex.leer("Pokedex.json");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        //SE CREAN LOS OBJETOS DE LOS DISTINTOS POKEMON
        try {
            for (Pokemon p : lista) {
                pokedex.agregar(p);
            }
        } catch (existException e) {
            System.out.println(e.getMessage());
        }
        Scanner sc = new Scanner(System.in);
        Equipos equipos = new Equipos();



        boolean salir = false;

        while (!salir) {
            //se muestra el menu prinicipal
                Menu.menuPrincipal();

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> equipos = GestorJuego.menuEquipos(sc, pokedex, equipos);// tiene qe tener retorno para que reconosca el json
                case 2 -> GestorJuego.menuPokedex(sc, pokedex);
                case 3 -> GestorJuego.menuBatalla(sc, equipos);
                case 0 -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }

        System.out.println("Programa finalizado.");
        sc.close();
    }


}