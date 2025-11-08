import Enums.ENombre;
import Enums.ETipo;
import Exceptions.archivoYaExisteException;
import Exceptions.nombreYaEncontradoException;
import Exceptions.vacioException;
import Gestoras.Equipos;
import Gestoras.Mochila;
import Gestoras.Pokedex;
import Menu.Menu;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;
import Utiles.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TP GRUPAL POKEDEX (Flores, Jimenez, Pascuan, Silvestre).

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        Pokedex pokedex = new Pokedex();
        Pokemon pikachu = new Pokemon(ENombre.PIKACHU, ETipo.ELECTRICO, 100, 100, 50, 30);
        Pokemon charmander = new Pokemon(ENombre.CHARMANDER, ETipo.FUEGO, 95, 95, 52, 35);
        Pokemon squirtle = new Pokemon(ENombre.SQUIRTLE, ETipo.AGUA, 110, 110, 48, 50);
        Pokemon bulbasaur = new Pokemon(ENombre.BULBASAUR, ETipo.PLANTA, 105, 105, 49, 45);
        Pokemon geodude = new Pokemon(ENombre.GEODUDE, ETipo.ROCA, 120, 120, 45, 60);
        Pokemon growlithe = new Pokemon(ENombre.GROWLITHE, ETipo.FUEGO, 100, 100, 55, 40);
        Pokemon jigglypuff = new Pokemon(ENombre.JIGGLYPUFF, ETipo.HIELO, 115, 115, 45, 20); // ← cambiado
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
            pokedex.grabar(array, "Pokedex.json");
        } catch (archivoYaExisteException e) {
            System.out.println( e.getMessage());
        }

        ArrayList<Pokemon> lista = new ArrayList<>();

        try {
             lista = (ArrayList<Pokemon>) Pokedex.leer("Pokedex.json");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        try {
            for (Pokemon p : lista) {
                pokedex.agregar(p);
            }
        } catch (nombreYaEncontradoException e) {
            System.out.println(e.getMessage());
        }
        Scanner sc = new Scanner(System.in);
        Equipos equipos = new Equipos();


        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Equipos");
            System.out.println("2. Pokédex");
            System.out.println("3. Batallar");
            System.out.println("0. Salir");
            System.out.print("Elegí una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> menuEquipos(sc, pokedex, equipos);
                case 2 -> menuPokedex(sc, pokedex);
                case 3 -> {
                    try {
                       // equipos.batallar();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 0 -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }

        System.out.println("Programa finalizado.");
        sc.close();
    }

    private static void menuEquipos(Scanner sc, Pokedex pokedex, Equipos equipos) {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n===== MENÚ EQUIPOS =====");
            System.out.println("1. Agregar peleador");
            System.out.println("2. Ver peleadores y sus Pokémon");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> agregarPeleador(sc, pokedex, equipos);
                case 2 -> {
                    try {
                        System.out.println(equipos.listar());
                    } catch (vacioException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 0 -> volver = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void agregarPeleador(Scanner sc, Pokedex pokedex, Equipos equipos) {
        System.out.println("\n=== Crear nuevo peleador ===");
        System.out.print("Nombre del entrenador: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido del entrenador: ");
        String apellido = sc.nextLine();

        Entrenador entrenador = new Entrenador(nombre, apellido);
        Mochila mochila = new Mochila();

        boolean volverMochila = false;
        while (!volverMochila) {
            System.out.println("\n--- Menú Mochila ---");
            System.out.println("1. Rellenar automáticamente (3 random)");
            System.out.println("2. Rellenar manualmente");
            System.out.println("3. Ver mochila");
            System.out.println("4. Eliminar Pokémon");
            System.out.println("5. Terminar y guardar peleador");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {
                    for (int i = 0; i < 3; i++) {
                        Pokemon p = pokedex.getRandom();
                        mochila.agregar(p);
                        System.out.println("Agregado: " + p);
                    }
                }
                case 2 -> {
                    Pokemon random = pokedex.getRandom();
                    System.out.println("Te tocó: " + random);
                    System.out.print("¿Querés agregarlo? (s/n): ");
                    String resp = sc.nextLine();
                    if (resp.equalsIgnoreCase("s")) {
                        try {
                            mochila.agregar(random);
                            System.out.println("Agregado con éxito.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Descartado.");
                    }
                }
                case 3 -> {
                    try {
                        System.out.println(mochila.listar());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        System.out.println(mochila.listar());
                        System.out.print("Nombre del Pokémon a eliminar: ");
                        String nombrePoke = sc.nextLine();
                        mochila.eliminar(nombrePoke);
                        System.out.println("Eliminado correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 5 -> {
                    try {
                        equipos.agregarEquipo(entrenador, mochila);
                        System.out.println("Peleador agregado con éxito.");
                        volverMochila = true;
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 0 -> volverMochila = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuPokedex(Scanner sc, Pokedex pokedex) {
        System.out.println("\n===== POKÉDEX =====");
        System.out.println(pokedex.listar()); // muestra la lista con índices

        System.out.print("Ingresá la posición del Pokémon para ver su info completa: ");
        int pos = sc.nextInt();
        sc.nextLine();
        try {
            Pokemon p = pokedex.buscar(pos);
            System.out.println("\n--- Información del Pokémon ---");
            System.out.println(p);
        } catch (NumberFormatException e) {
            System.out.println("Error: Debés ingresar un número válido.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: No existe un Pokémon en esa posición.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

//        for (Pokemon p : lista){
//            pokedex.agregar(p);
//        }
//
//        //Tiene que estar creado una SOLA instancia de equipos y de pokedex.
//        // System.out.println(pokedex.listar());
//
//        Equipos equipos = new Equipos();
//
//        int contadorDeEquipos = 1;
//        //Ver equipos(tira exeption si tan vacios
//        //Crear nuevo Equipo
//        //batallar(tira exeption si no hay equipos)
//        //ver pokedex
//
//        //Primero pregunta por el entrenador despues por  su mochila seguidamente
//
//
//        String nombre = "Rober";
//        String apellido = "Tito";
//        Entrenador entrenador = new Entrenador(nombre,apellido);
//
//        //  mochila del entrenador
//
//              //Rellenar Autoamticamente
//                     //Mete 3 random
//              //Rellenar Manualmente
//                    //Agregar pokemonm random
//                         Pokemon random = pokedex.getRandom();
//                        // System.out.println("Te toco este pokemon");
//                            //agregar o descartar
//                                    //Agregar
//                                        Mochila mochila = new Mochila();
//                                        mochila.agregar(random);
//                                        //System.out.println("ya existe");
//                                        // System.out.println("agregado con exito");
//                                        //Si ya existe vuelvo al menu de la mochila
//                                    //Descartar
//                                        //Volver atras
//
//
//             //Ver mochila (tira excepcion si ta vacia)
//             //Eliminar pokemon   (tira excepcion si ta vacia)
//
//
//        System.out.println(mochila.listar());
//        //Agregar Entrenador y su mochila al equipo
//
//        try {
//            equipos.agregarEquipo(entrenador, mochila);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//
//
//        //Crear entrenador
//
//        String nombre2= "Michael";
//        String apellido2="Jackson";
//
//        Entrenador entrenador2 = new Entrenador(nombre2,apellido2);
//        Pokemon random2 = pokedex.getRandom();
//        Mochila mochila2 = new Mochila();
//        mochila2.agregar(random2);
//
//
//
//        try {
//            equipos.agregarEquipo(entrenador2,mochila2);
//            System.out.println("Agregado con exito");
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        //Iniciar batalla// SI no hay 2 entrenadores en el equipo no deberia dejar












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
// ll

        //Menu.mainmenu(args);




    }
