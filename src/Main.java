import Gestoras.GestorEquipo;
import Model.Entrenador;
import Model.Pokemon;
import Model.Pokemones.Caterpie;

import java.util.LinkedHashSet;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // Variables
        Scanner sc = new Scanner(System.in);
        int opcion;

        Pokemon caterpie = new Caterpie("Marcelo");
        Pokemon caterpie2 = new Caterpie("Emanuel");
        Pokemon caterpie3 = new Caterpie("Nico");

        Entrenador entrenador = new Entrenador("Joel","Gardel");
        GestorEquipo gestorEquipo = new GestorEquipo();
        gestorEquipo.crearEquipo(entrenador);
        LinkedHashSet<Pokemon> mochilaDelEntrenador = gestorEquipo.crearMochilaEntrenador();

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
                }
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
        saltarLinea(1);
        System.out.println("[ POKEDEX ]");
        separador(30);
        System.out.println("[0.] SALIR");
        System.out.println("[1.] (!) ");
        System.out.println("[2.] (!)");
        System.out.println("[3.] (!)");
        System.out.println("[4.] (!)");
        separador(30);
        System.out.print("- Ingrese una opción: ");
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