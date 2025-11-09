package Menu;

import java.util.Scanner;

public class Menu {

    public static void mainmenu(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            opcion = menu(sc);
            saltarLinea(1);

            switch (opcion) {
                case 0:
                    System.out.println("<Cerrando programa...>");
                    break;
                case 1:
                    break;
                case 2:
                    int opcion1 = menuEntrenador(sc);
                    do {
                        switch (opcion1) {
                            case 0: break;
                            case 1: System.out.println("<Creando entrenador nuevo...>"); break;
                            case 2: System.out.println("<Mostrando todos los entrenadores disponibles...>"); break;
                            default: System.out.println("! ERROR !"); break;
                        }
                        if (opcion1 != 0) limpiarPantalla(sc);
                    } while (opcion1 != 0);
                    break;
                case 3: break;
                default: System.out.println("! ERROR !"); break;
            }

            if (opcion != 0) limpiarPantalla(sc);
        } while (opcion != 0);
    } // fin del main

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
        for (int i = 0; i < num; i++) System.out.print("- ");
        System.out.printf("%n");
    }

    public static void saltarLinea(int num) {
        for (int i = 0; i < num; i++) System.out.printf("%n");
    }

    public static void limpiarPantalla(Scanner sc) {
        saltarLinea(1);
        System.out.print("- Presione Enter para continuar...");
        sc.nextLine();
        saltarLinea(20);
    }

    public static void menuMochila(){
        System.out.println("\n--- Menú Mochila ---");
        System.out.println("1. Rellenar / completar automáticamente");
        System.out.println("2. Rellenar manualmente");
        System.out.println("3. Ver mochila");
        System.out.println("4. Eliminar Pokémon");
        System.out.println("5. Guardar peleador y volver");
        System.out.print("Opción: ");
    }

    public static void menuEquipos(){
        System.out.println("\n===== MENÚ EQUIPOS =====");
        System.out.println("1. Agregar peleador");
        System.out.println("2. Ver peleadores y sus Pokémon");
        System.out.println("3. Iniciar batalla  ");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
    }

    public static void menuPrincipal(){
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Equipos");
        System.out.println("2. Pokédex");
        System.out.println("3. Batallar");
        System.out.println("0. Salir");
        System.out.print("Elegí una opción: ");
    }



}


