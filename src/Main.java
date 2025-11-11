import Enums.ENombre;
import Exceptions.archivoYaExisteException;
import Exceptions.capacidadInvalidaException;

import Exceptions.existException;
import Colecctions.Equipos;
import Colecctions.Mochila;
import Colecctions.Pokedex;
import Gestoras.GestorJuego;
import Menu.Menu;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;
import Utiles.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;




// TP GRUPAL POKEDEX (Agustin Flores 💀, Valentina Jimenez,Joel Pascuan, Geronimo Silvestre).

public class Main {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        Pokedex pokedex = new Pokedex();
        JSONArray array = new JSONArray();

        //Se crea un Json Array para meter a todos los pokemones existentes
        //El JsonArray es serializado y luego deserealizado para ser metido en la pokedex, asi demostrar el funcionamiento

        for (ENombre nombre : ENombre.values()) {  //Por cada valor de ENombre
            Pokemon p = new Pokemon(nombre); //Creo un pokemon completo gracias a ese ENombre
            array.put(p.toJSON()); //todos  los pokemones entran como JsonObject en el JsonArray
        }

        Equipos equipos1= new Equipos();
        //El juego permite importar equipos  (Entrenador y pokemon)  ya creados desde  un archivo JSON
        //Creamos un equipo manualmente  que se serializa a un .json para tener a mano un equipos.json y poder usarlo en el juego si es que el usuario no quiere crear nada

    Entrenador entrenador1 = new Entrenador("Joel","Pascuan");
    Entrenador entrenador2 = new Entrenador("Valentina","Jimenez");
    Mochila mochila1 = new Mochila();
    Mochila mochila2 = new Mochila();
        try {

            mochila1.agregar(new Pokemon("Dragonite"));
            mochila1.agregar(new Pokemon("Pikachu"));
            mochila1.agregar(new Pokemon("Charmander"));
            mochila2.agregar(new Pokemon ("Squirtle"));
            mochila2.agregar(new Pokemon ("Bulbasaur"));
            mochila2.agregar(new Pokemon ("Articuno"));
            equipos1.agregarEquipo(entrenador1,mochila1);
            equipos1.agregarEquipo(entrenador2,mochila2);

        } catch (capacidadInvalidaException e) {
            System.out.println(e.getMessage());
        } catch (existException e) {
            System.out.println(e.getMessage());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

//        try {
            JsonUtiles.grabarUnJson(equipos1.toJSON(), "equipos.json");
//        } catch (JSONException e) {
//            System.out.println(e.getMessage());
//        }





        // SE GRABAN POKEMONS EN JSON
        try {
            pokedex.grabar(array, "Pokedex.json");
        } catch (archivoYaExisteException e) {
            System.out.println( e.getMessage());
        }
//        catch (JSONException e) {
//            System.out.println(e.getMessage());
//        }

        ArrayList<Pokemon> lista = new ArrayList<>();

        //LEEN POKEMON DE JSON
        //De aqui es que se toman todos los pokemones que van a existir en la pokedex
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



            try{
                int opcion = sc.nextInt();
                sc.nextLine();

                //Switch que contiene las opciones principales del juego
                //Por dentro de cada case hay sub opciones con mas switch y llamadas a metodos

                switch (opcion) {
                    case 1 -> equipos = GestorJuego.menuEquipos(sc, pokedex, equipos);// tiene qe tener retorno para que reconosca el json
                    case 2 -> GestorJuego.menuPokedex(sc, pokedex);
                    case 3 -> GestorJuego.menuBatalla(sc, equipos);
                    case 0 -> salir = true;
                    default -> System.out.println("Opción inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println(" opcion ingresada invalida");
                sc.nextLine();
            }
        }

        System.out.println("Programa finalizado.");
        sc.close();
    }

}