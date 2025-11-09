import Enums.ENombre;
import Enums.ETipo;
import Exceptions.archivoYaExisteException;
import Exceptions.capacidadInvalidaException;
import Exceptions.emptyNameException;
import Exceptions.existException;

import Gestoras.Equipos;
import Gestoras.GestorBatalla;
import Gestoras.Mochila;
import Gestoras.Pokedex;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.Scanner;


// TP GRUPAL POKEDEX (Flores, Jimenez, Pascuan, Silvestre).

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        Pokedex pokedex = new Pokedex();
        Pokemon pikachu = new Pokemon(ENombre.PIKACHU, ETipo.ELECTRICO);
        Pokemon charmander = new Pokemon(ENombre.CHARMANDER, ETipo.FUEGO);
        Pokemon squirtle = new Pokemon(ENombre.SQUIRTLE, ETipo.AGUA);
        Pokemon bulbasaur = new Pokemon(ENombre.BULBASAUR, ETipo.PLANTA);
        Pokemon geodude = new Pokemon(ENombre.GEODUDE, ETipo.ROCA);
        Pokemon growlithe = new Pokemon(ENombre.GROWLITHE, ETipo.FUEGO);
        Pokemon jigglypuff = new Pokemon(ENombre.JIGGLYPUFF, ETipo.HIELO); // ← cambiado
        Pokemon caterpie = new Pokemon(ENombre.CATERPIE, ETipo.PLANTA);
        Pokemon arbok = new Pokemon(ENombre.ARBOK, ETipo.PLANTA);
        Pokemon dragonite = new Pokemon(ENombre.DRAGONITE, ETipo.FUEGO);
        Pokemon magnetite = new Pokemon(ENombre.MAGNETITE, ETipo.ELECTRICO);
        Pokemon snorunt = new Pokemon(ENombre.SNORUNT, ETipo.HIELO);


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




        // SE GRABAN POKEMONS EN JSON
        try {
            pokedex.grabar(array, "Pokedex.json");
        } catch (archivoYaExisteException e) {
            System.out.println( e.getMessage());
        }

        ArrayList<Pokemon> lista = new ArrayList<>();

        //LEEN POKEMON DE JSON
        try {
             lista = (ArrayList<Pokemon>) Pokedex.leer("Pokedex.json");
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
            System.out.println("3.   Iniciar batalla  ");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {

                    try {
                        agregarPeleador(sc, pokedex, equipos);
                    } catch (capacidadInvalidaException e) {
                        System.out.println(e.getMessage());
                    }


                }
                case 2 -> {
                    try {
                        System.out.println(equipos.listar());
                    } catch (capacidadInvalidaException  | existException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 3->{
                    GestorBatalla gestorBatalla= new GestorBatalla(equipos);
                    iniciarBatalla(equipos, gestorBatalla);
                }
                case 0 -> volver = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void agregarPeleador(Scanner sc, Pokedex pokedex, Equipos equipos)  throws capacidadInvalidaException  {


        if (equipos.size() >= 2) {
            throw new capacidadInvalidaException("No se pueden agregar mas peleadores");
        }

        System.out.println("\n=== Crear nuevo peleador ===");
        System.out.print("Nombre del entrenador: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Apellido del entrenador: ");
         String apellido = sc.nextLine().trim();


        Entrenador entrenador = new Entrenador(nombre, apellido);
        Mochila mochila = new Mochila();

        boolean volverMochila = false;
        while (!volverMochila) {
            System.out.println("\n--- Menú Mochila ---");
            System.out.println("1. Rellenar / completar automáticamente");
            System.out.println("2. Rellenar manualmente");
            System.out.println("3. Ver mochila");
            System.out.println("4. Eliminar Pokémon");
            System.out.println("5. Guardar peleador y volver");
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {
                   int tamanio= mochila.size();

                    while (tamanio <3) {
                        Pokemon p = pokedex.getRandom();
                        try {
                            mochila.agregar(p);
                            System.out.println("Agregado: " + p.getNombre());
                        } catch (capacidadInvalidaException | existException e ) {
                            System.out.println("No se pudo agregar el Pokémon: " + e.getMessage());

                        }
                        tamanio++;
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
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
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
                        System.out.println("Error: " + e.getMessage());
                    } catch ( capacidadInvalidaException e) {
                        System.out.println("Error: " + e.getMessage());
                    }catch (IllegalArgumentException e)
                    {
                        System.out.println("Nombre mal escrito");
                    }
                }
                case 5 ->{
                    boolean agregado = false;

                    while (!agregado) {
                        try {
                            equipos.agregarEquipo(entrenador, mochila);
                            System.out.println("Peleador agregado con éxito.");

                            volverMochila = true;
                            agregado = true;

                        } catch (emptyNameException e) {
                            System.out.println("El entrenador no tiene nombre o apellido \n");

                            System.out.print("Nombre del entrenador: ");
                         nombre = sc.nextLine().trim();

                            System.out.print("Apellido del entrenador: ");
                          apellido = sc.nextLine().trim();

                            entrenador.setNombre(nombre);
                            entrenador.setApellido(apellido);


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

    private static void menuPokedex(Scanner sc, Pokedex pokedex) {
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
            System.out.println("Error: Debés ingresar un número válido.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: No existe un Pokémon en esa posición.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

//



    public static void iniciarBatalla(Equipos equipos, GestorBatalla gestorBatalla) {
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

        while (batallaActiva) {
            System.out.println("=== Turno " + turno + " ===");

            Entrenador entrenadorAtacante;
            Entrenador entrenadorDefensor;
            Pokemon pokemonAtacante;
            Pokemon pokemonDefensor;

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
                gestorBatalla.atacar(pokemonAtacante, pokemonDefensor);
                System.out.println(pokemonAtacante.getNombre() + " atacó a " + pokemonDefensor.getNombre());
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
                            System.out.println(equipos.getMochila(entrenadorDefensor.getNombre(), entrenadorDefensor.getApellido()).listar());
                        } catch (capacidadInvalidaException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.print("Nombre del nuevo Pokémon: ");
                        String nuevoNombre = sc.nextLine().trim().toUpperCase();

                        Pokemon nuevoPoke = new Pokemon(nuevoNombre);


                        if (nuevoPoke != null) {
                            if (turno % 2 != 0) pokemon2 = nuevoPoke;
                            else pokemon1 = nuevoPoke;
                            System.out.println(nuevoPoke.getNombre() + " entra en combate!");
                        } else {
                            System.out.println("No se encontró ese Pokémon, pierde el turno.");
                        }
                    }
                }

            } else if (opcion == 2) {
                System.out.println(entrenadorAtacante.getNombre() + ", elegí otro Pokémon:");
                try {
                    System.out.println(equipos.getMochila(entrenadorAtacante.getNombre(), entrenadorAtacante.getApellido()).listar());
                } catch (capacidadInvalidaException e) {
                    System.out.println(e.getMessage());
                }
                System.out.print("Nombre del nuevo Pokémon: ");
                String nuevoNombre = sc.nextLine().trim().toUpperCase();

                Pokemon nuevoPoke = new Pokemon(nuevoNombre);


                if (nuevoPoke != null) {
                    if (turno % 2 != 0) pokemon1 = nuevoPoke;
                    else pokemon2 = nuevoPoke;
                    System.out.println("Nuevo Pokémon activo: " + nuevoPoke.getNombre());
                } else {
                    System.out.println("No se encontró ese Pokémon.");
                }
            }

            if (batallaActiva) turno++;
        }
    }
}