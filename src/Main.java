import Gestoras.GestorEquipo;
import Gestoras.GestorPokedex;
import Model.Entrenador;
import Model.Pokemon;
import Model.Pokemones.*;

import java.util.Scanner;

// TP GRUPAL POKEDEX (Flores, Jimenez, Pascuan, Silvestre).

public class Main {
    public static void main(String[] args) {


        GestorPokedex gp = new GestorPokedex();
        //creacion de pokemons
        Pokemon arbok =new Arbok();
        Pokemon bulbasaur =new Bulbasaur();
        Pokemon caterpie = new Caterpie();
        Pokemon charmander =new Charmander();
        Pokemon dragonite = new Dragonite();
        Pokemon geodude =new Geodude();
        Pokemon growlithe =new Growlithe ();
        Pokemon jigglypuff =new Jigglypuff ();
        Pokemon pikachu =new Pikachu ();
        Pokemon squirtle = new Squirtle ();

        gp.agregarPokemon(arbok);
        gp.agregarPokemon(bulbasaur);
        gp.agregarPokemon(caterpie);
        gp.agregarPokemon(charmander);
        gp.agregarPokemon(dragonite);
        gp.agregarPokemon(geodude);
        gp.agregarPokemon(growlithe);
        gp.agregarPokemon(jigglypuff);
        gp.agregarPokemon(pikachu);
        gp.agregarPokemon(squirtle);



        // Variables
        Scanner sc = new Scanner(System.in);
        int opcion, opcion1;

        Pokemon caterpie1 = new Caterpie("Marcelo");
        Pokemon caterpie2 = new Caterpie("Emanuel");
        Pokemon caterpie3 = new Caterpie("Nico");

        Entrenador entrenador = new Entrenador("Joel","Gardel");
        Entrenador entrenador2 = new Entrenador("Agus","Flores");
        GestorEquipo gestorEquipo = new GestorEquipo();

        gestorEquipo.crearEquipo(entrenador);
        Pokemon random = gestorEquipo.crearPokemonRandom();

        //Aca se le pregunta al usuario si le interesa ese pokemon en particular
        gestorEquipo.agregarPokemon(entrenador,random);






        // En el switch va a existir una opcion para setear el principal


        // opcion para iniciar batalla
        /*Boolean batallaActiva= true;
        int rondas = 20;
        while(batallaActiva && rondas>0){
            

            rondas--;


        } */




        //Switch para agregar pokemones















        gestorEquipo.crearEquipo(entrenador);
        // LinkedHashSet<Pokemon> mochilaDelEntrenador = gestorEquipo.crearMochilaEntrenador();

        saltarLinea(1);
        do {
            opcion = menu(sc);
            saltarLinea(1);
            switch(opcion) {
                case 0: {
                    System.out.println("<Cerrando programa...>");
                    break;
                } // Salir
                case 1: {
                    break;
                } // Ver Pokédex
                case 2: {
                    do {
                        opcion1 = menuEntrenador(sc);
                        switch(opcion1) {
                            case 0: {
                                break;
                            } // Salir
                            case 1: {
                                System.out.println("<Creando entrenador nuevo...>");
                                // Código.
                                break;
                            } // Crear entrenador
                            case 2: {
                                System.out.println("<Mostrando todos los entrenadores disponibles...>");
                                // Código.
                                break;
                            } // Elegir entrenador
                            default: {
                                System.out.println("! ERROR !");
                                break;
                            } // Error
                        }

                        if(opcion1 != 0) {
                            limpiarPantalla(sc);
                        } // Limpiar pantalla
                    } while(opcion1 != 0);

                } // Crear/Elegir entrenador
                case 3: {
                    break;
                } // Batalla
                default: {
                    System.out.println("! ERROR !");
                    break;
                } // Error
            }

            if(opcion != 0) {
                limpiarPantalla(sc);
            } // Limpiar pantalla
        } while(opcion != 0); // Menu Principal
    }

    // Métodos
    public static int menu(Scanner sc) {
        System.out.println("[ SIMULADOR POKÉMON ]");
        separador(30);
        System.out.println("[0.] SALIR.");
        System.out.println("[1.] (!) Ver Pokédex.");
        System.out.println("[2.] (!) Crear/Elegir entrenador.");
        System.out.println("[3.] (!) Batalla.");
        separador(30);
        System.out.print("- Ingrese una opción: ");
        int opcion = sc.nextInt();
        sc.nextLine();
        return opcion;
    }
    public static int menuEntrenador(Scanner sc) {
        System.out.println("[ MENU ENTRENADOR ]");
        separador(30);
        System.out.println("[0.] SALIR.");
        System.out.println("[1.] (!) CREAR.");
        System.out.println("[2.] (!) ELEGIR.");
        separador(30);
        System.out.print("- Ingrese una opcion: ");
        int opcion = sc.nextInt();
        sc.nextLine();
        return opcion;
    }

    // Auxiliares
    public static void separador(int num) {
        for(int i=0; i<num; i++) {
            System.out.print("- ");
        }
        System.out.printf("%n");
    }
    public static void saltarLinea(int num) {
        for(int i=0; i<num; i++) {
            System.out.printf("%n");
        }
    }
    public static void limpiarPantalla(Scanner sc) {
        saltarLinea(1);
        System.out.print("- Presione Enter para continuar...");
        sc.nextLine();
        saltarLinea(20);
    }



}