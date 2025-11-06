import Gestoras.GestorEquipo;
import Gestoras.GestorPokedex;
import Gestoras.Pokedex;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;
import Enums.ENombre;
import Enums.ETipo;

import java.util.Scanner;

// TP GRUPAL POKEDEX (Flores, Jimenez, Pascuan, Silvestre).

public class Main {
    public static void main(String[] args) {


        Pokedex gp = new Pokedex();
        Pokemon pikachu = new Pokemon(ENombre.PIKACHU, ETipo.ELECTRICO, 100, 50, 30);
        Pokemon charmander = new Pokemon(ENombre.CHARMANDER, ETipo.FUEGO, 95, 52, 35);
        Pokemon squirtle = new Pokemon(ENombre.SQUIRTLE, ETipo.AGUA, 110, 48, 50);
        Pokemon bulbasaur = new Pokemon(ENombre.BULBASAUR, ETipo.PLANTA, 105, 49, 45);
        Pokemon geodude = new Pokemon(ENombre.GEODUDE, ETipo.ROCA, 120, 45, 60);
        Pokemon growlithe = new Pokemon(ENombre.GROWLITHE, ETipo.FUEGO, 100, 55, 40);
        Pokemon jigglypuff = new Pokemon(ENombre.MAGNETITE, ETipo.HIELO, 115, 45, 20); // ← cambiado
        Pokemon caterpie = new Pokemon(ENombre.CATERPIE, ETipo.PLANTA, 80, 30, 35);
        Pokemon arbok = new Pokemon(ENombre.ARBOK, ETipo.PLANTA, 95, 60, 44);
        Pokemon dragonite = new Pokemon(ENombre.DRAGONITE, ETipo.FUEGO, 150, 85, 70);
        Pokemon magnetite = new Pokemon(ENombre.MAGNETITE, ETipo.ELECTRICO, 90, 55, 35);
        Pokemon snorunt = new Pokemon(ENombre.SNORUNT, ETipo.HIELO, 95, 45, 30);




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