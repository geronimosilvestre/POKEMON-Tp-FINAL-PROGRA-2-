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


}


