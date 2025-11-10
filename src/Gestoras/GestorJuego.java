package Gestoras;

import Colecctions.Equipos;
import Colecctions.Mochila;
import Colecctions.Pokedex;
import Exceptions.capacidadInvalidaException;
import Exceptions.emptyNameException;
import Exceptions.existException;
import Menu.Menu;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;
import Utiles.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static Gestoras.GestorDamage.seleccionarNuevoPokemon;

public class GestorJuego {

    public  static Equipos menuEquipos(Scanner sc, Pokedex pokedex, Equipos equipos) {
        boolean volver = false;

        while (!volver) {

            //se muestra menu equipos

            Menu.menuEquipos();

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {

                    try {

                        agregarPeleador(sc, pokedex, equipos);

                    } catch (capacidadInvalidaException e) {
                        System.out.println(e.getMessage());
                    }catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }


                }
                case 2 -> {
                    try {
                        System.out.println(equipos.listar());
                    } catch (capacidadInvalidaException | existException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3->{
                    System.out.println("ingrese el nombre del archivo");

                    String nombreArchivo = sc.nextLine();
                    JSONTokener tokener = null;

                    try {
                        tokener = JsonUtiles.leerUnJson(nombreArchivo);
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                        break;
                    }



                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(tokener);
                    } catch (JSONException e) {
                        System.out.println(e.getMessage());
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }


                    try {
                        equipos =  equipos.fromJSON(jsonArray);

                    } catch (JSONException e) {
                        System.out.println(e.getMessage());
                    }


                    try {
                        System.out.println(equipos.listar());
                    } catch (capacidadInvalidaException e) {
                        System.out.println(e.getMessage());
                    } catch (existException e) {
                        System.out.println(e.getMessage());
                    }



                }
                case 0 -> volver = true;
                default -> System.out.println("Opción inválida.");
            }
        }
        return equipos;
    }

    public static void agregarPeleador(Scanner sc, Pokedex pokedex, Equipos equipos)  throws capacidadInvalidaException, IllegalArgumentException  {


        if (equipos.size() >= 2) {
            throw new capacidadInvalidaException("No se pueden agregar mas peleadores");
        }

        System.out.println("\n=== Crear nuevo peleador ===");
        System.out.print("Nombre del entrenador: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Apellido del entrenador: ");
        String apellido = sc.nextLine().trim();

        Entrenador entrenador = null;
        try{
            entrenador = new Entrenador(nombre, apellido);

        }catch(IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
        }


        Mochila mochila = new Mochila();

        boolean volverMochila = false;
        while (!volverMochila) {

            //se muestra el menu para la mochila
            Menu.menuMochila();

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {
                    int tamanio= mochila.size();

                    while (tamanio < 3) {
                        Pokemon p = pokedex.getRandom();
                        try {
                            mochila.agregar(p);
                            tamanio++;
                            System.out.println("Agregado: " + p.getNombre());
                        } catch (capacidadInvalidaException | existException e ) {
                            System.out.println("No se pudo agregar el Pokémon: " + e.getMessage());
                        }

                    }
                    if(tamanio == 3) {
                        System.out.println("La mochila ya contiene 3 pokemones");
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
                            System.out.println("Error al agregar: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Descartado.");
                    }
                }
                case 3 -> {
                    try {
                        System.out.println(mochila.listar());
                    } catch (capacidadInvalidaException e) {
                        System.out.println(e.getMessage());
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        System.out.println(mochila.listar());
                        System.out.print("Nombre del Pokémon a eliminar: ");
                        String nombrePoke = sc.nextLine();
                        System.out.println(" nombre  " +  nombrePoke + "\n");
                        mochila.eliminar(nombrePoke);
                        System.out.println("Eliminado correctamente.");
                    } catch (existException e) {
                        System.out.println(e.getMessage());
                    } catch ( capacidadInvalidaException e) {
                        System.out.println(e.getMessage());
                    }catch (IllegalArgumentException e) {
                        System.out.println("Nombre mal escrito");
                    }
                }
                case 5 ->{
                    boolean agregado = false;

                    while (!agregado) {
                        try {


                            System.out.print("Nombre del entrenador: ");
                            nombre = sc.nextLine().trim();

                            System.out.print("Apellido del entrenador: ");
                            apellido = sc.nextLine().trim();

                            entrenador.setNombre(nombre);
                            entrenador.setApellido(apellido);

                            equipos.agregarEquipo(entrenador, mochila);

                            agregado = true;
                            volverMochila = true;


                            System.out.println("Peleador agregado con éxito.");


                        } catch (emptyNameException e) {
                            System.out.println("El entrenador no tiene nombre o apellido \n");
                        } catch (existException | capacidadInvalidaException e) {
                            System.out.println("Error al guardar el equipo: " + e.getMessage());
                            agregado = true;
                        }
                    }
                }

                default -> System.out.println("Opción inválida.");
            }
        }
    }

    public static void menuPokedex(Scanner sc, Pokedex pokedex) {
        System.out.println("\n===== POKÉDEX =====");
        System.out.println(pokedex.listar()); // muestra la lista con índices

        System.out.print("Ingresá la posición del Pokémon para ver su info completa: ");
        int pos = sc.nextInt();
        sc.nextLine();
        try {
            Pokemon p = pokedex.buscar(pos);
            System.out.println("\n--- Información del Pokémon ---");
            System.out.println(p.toString());
        } catch (NumberFormatException e) {
            System.out.println("Debés ingresar un número válido.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No existe un Pokémon en esa posición.");
        } catch (Exception e) {
            System.out.println("Error inesperado:  " + e.getMessage());
        }
    }

    public static void menuBatalla(Scanner sc, Equipos equipos) {
        boolean volver = false;
        GestorDamage gestorDamage = new GestorDamage(equipos);

        while (!volver) {
            Menu.menuBatalla();
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 ->
                        iniciarBatalla(equipos, gestorDamage);

                case 2 -> {
                    // Guardar datos de la batalla previa
                    try {
                        if (equipos.size() != 2) {
                            throw new capacidadInvalidaException("Los equipos estan  incompletos");
                        }
                        JsonUtiles.grabarUnJson(equipos.toJSON(), "BatallaReciente.json");
                        System.out.println("Batalla guardada correctamente en BatallaReciente.json");
                        for (Entrenador e : equipos.getEntrenadores()) {
                            for(Pokemon p : equipos.getMochila(e.getNombre(),e.getApellido()).obtenerTodos()){
                                p.setVidaRestante(p.getVidaCompleta());
                            }
                        }

                    } catch (capacidadInvalidaException e) {
                        System.out.println("Error al guardar la batalla: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error al guardar la batalla: " + e.getMessage());
                    }
                }
                case 0 -> volver = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    public static void iniciarBatalla(Equipos equipos, GestorDamage gestorDamage) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== COMIENZA LA BATALLA ===");
        System.out.println("Listado de equipos:");
        try {
            System.out.println(equipos.listar());
        } catch (capacidadInvalidaException | existException e) {
            System.out.println(e.getMessage());
        }


        ArrayList<Entrenador> entrenadores = equipos.getEntrenadores();



        // === SELECCIÓN DE ENTRENADORES ===

        Entrenador entrenador1 =  entrenadores.get(0);
        System.out.println("Nombre del primer entrenador: " + entrenador1.getNombre());




        Entrenador entrenador2 =   entrenadores.get(1);


        Mochila mochila1 = equipos.getMochila(entrenador1.getNombre(),entrenador1.getApellido());
        Mochila mochila2 = equipos.getMochila(entrenador2.getNombre(),entrenador2.getApellido());
        Pokemon pokemon1= null;
        Pokemon pokemon2 = null;

        System.out.println("Nombre del primer entrenador: " + entrenador2.getNombre());

        // === ELECCIÓN DE POKÉMON PRINCIPALES ===
        System.out.println("\nPokémon del equipo de " + entrenador1.getNombre() + ":");

        try {
            System.out.println(mochila1.listar());
        } catch (capacidadInvalidaException e) {
            System.out.println(e.getMessage());
        }
        System.out.print("Ingresá el numero para seleccionar un   Pokémon como principal: ");

        try {
            pokemon1 = mochila1.getPokemonIndex(sc.nextInt());
            sc.nextLine();
        } catch (capacidadInvalidaException e) {
            System.out.println(e.getMessage());
        }






        System.out.println("\nPokémon del equipo de " + entrenador2.getNombre() + ":");
        try {
            System.out.println(mochila2.listar());
        } catch (capacidadInvalidaException e) {
            System.out.println(e.getMessage());
        }
        System.out.print("Ingresá el nombre del Pokémon principal: ");
        try {
            pokemon2 = mochila2.getPokemonIndex(sc.nextInt());
            sc.nextLine();
        } catch (capacidadInvalidaException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\n¡La batalla comienza entre " + pokemon1.getNombre() + " y " + pokemon2.getNombre() + "!\n");

        // === BUCLE DE BATALLA ===
        boolean batallaActiva = true;
        int turno = 1;

        Entrenador entrenadorAtacante;
        Entrenador entrenadorDefensor;
        Pokemon pokemonAtacante;
        Pokemon pokemonDefensor;

        while (batallaActiva) {
            System.out.println("=== Turno " + turno + " ===");


            if (turno % 2 != 0) {
                entrenadorAtacante = entrenador1;
                entrenadorDefensor = entrenador2;
                pokemonAtacante = pokemon1;
                pokemonDefensor = pokemon2;
            } else {
                entrenadorAtacante = entrenador2;
                entrenadorDefensor = entrenador1;
                pokemonAtacante = pokemon2;
                pokemonDefensor = pokemon1;
            }

            System.out.println("\nTurno de " + entrenadorAtacante.getNombre() + " (" + pokemonAtacante.getNombre() + ")");
            System.out.println("Vida de tu Pokémon: " + pokemonAtacante.getVidaRestante());
            System.out.println("Vida del oponente: " + pokemonDefensor.getVidaRestante());
            System.out.println("1. Atacar");
            System.out.println("2. Cambiar Pokémon");
            System.out.print("Elegí una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {
                gestorDamage.atacar(pokemonAtacante, pokemonDefensor);
                System.out.println(pokemonAtacante.getNombre() + " atacó a  " + pokemonDefensor.getNombre());
                System.out.println("Vida de " + pokemonDefensor.getNombre() + ": " + pokemonDefensor.getVidaRestante());

                if (pokemonDefensor.getVidaRestante() <= 0) {
                    System.out.println("\n" + pokemonDefensor.getNombre() + " ha sido derrotado.");

                    // Verificar si todos los Pokémon del defensor están debilitados
                    boolean todosDebilitados = true;
                    for (Pokemon p : equipos.getMochila(entrenadorDefensor.getNombre(), entrenadorDefensor.getApellido()).obtenerTodos()) {
                        if (p.getVidaRestante() > 0) {
                            todosDebilitados = false;
                            break;
                        }
                    }

                    if (todosDebilitados) {
                        System.out.println("\n" + entrenadorAtacante.getNombre() + " ha ganado la batalla!");
                        batallaActiva = false;
                    } else {

                        System.out.println(entrenadorDefensor.getNombre() + ", elegí otro Pokémon:");
                        try {
                            Mochila mochilita = equipos.getMochila(entrenadorDefensor.getNombre(), entrenadorDefensor.getApellido());
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < mochilita.size(); i++) {
                                Pokemon p = mochilita.getPokemonIndex(i);
                                sb.append(p.getNombre() + " - vida restante: " + p.getVidaRestante() + "\n");

                            }

                            System.out.println(sb);
                        } catch (capacidadInvalidaException e) {
                            System.out.println(e.getMessage());
                        }

                        Pokemon cambioPokemon = seleccionarNuevoPokemon(sc, equipos, entrenadorDefensor, turno, true);
                        if (turno % 2 != 0)
                            pokemon2 = cambioPokemon;
                        else
                            pokemon1 = cambioPokemon;


                    }

                }
            } else if (opcion == 2) {
                System.out.println(entrenadorAtacante.getNombre() + ", elegí otro Pokémon:");

                try {
                    Mochila mochilita = equipos.getMochila(entrenadorAtacante.getNombre(), entrenadorAtacante.getApellido());
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mochilita.size(); i++) {
                        Pokemon p = mochilita.getPokemonIndex(i);
                        sb.append(p.getNombre() + " - " + p.getVidaRestante() + "\n");

                    }

                    System.out.println(sb);
                } catch (capacidadInvalidaException e) {
                    System.out.println(e.getMessage());
                }


                Pokemon reemplazo = seleccionarNuevoPokemon(sc, equipos, entrenadorAtacante, turno, false);

                if (turno % 2 != 0)
                    pokemon1 = reemplazo;
                else
                    pokemon2 = reemplazo;


            }

            if (batallaActiva) turno++;
        }
    }
}
