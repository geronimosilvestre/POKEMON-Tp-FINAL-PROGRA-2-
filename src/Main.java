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
        } catch (existException e) {
            System.out.println(e.getMessage());
        }
        Scanner sc = new Scanner(System.in);
        Equipos equipos = new Equipos();

        Mochila mochilita = new Mochila();



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
                   int tamanio=0;

                    while (mochila.size()<3) {
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
            System.out.println(p);
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

        // === SELECCIÓN DE ENTRENADORES ===
        System.out.print("\nIngresá el nombre del primer entrenador: ");
        String nombre1 = sc.nextLine().trim();
        System.out.print("Ingresá el apellido del primer entrenador: ");
        String apellido1 = sc.nextLine().trim();

        Entrenador entrenador1 = equipos.getEntrenador(nombre1, apellido1);
        if (entrenador1 == null) {
            System.out.println("No se encontró el entrenador " + nombre1 + " " + apellido1);
            return;
        }

        System.out.print("\nIngresá el nombre del segundo entrenador: ");
        String nombre2 = sc.nextLine().trim();
        System.out.print("Ingresá el apellido del segundo entrenador: ");
        String apellido2 = sc.nextLine().trim();

        Entrenador entrenador2 = equipos.getEntrenador(nombre2, apellido2);
        if (entrenador2 == null) {
            System.out.println("No se encontró el entrenador " + nombre2 + " " + apellido2);
            return;
        }

        // === ELECCIÓN DE POKÉMON PRINCIPALES ===
        System.out.println("\nPokémon del equipo de " + entrenador1.getNombre() + ":");
        try {
            System.out.println(equipos.getMochila(entrenador1.getNombre(), entrenador1.getApellido()).listar());
        } catch (capacidadInvalidaException e) {
            System.out.println(e.getMessage());
        }
        System.out.print("Ingresá el nombre del Pokémon principal: ");
        String nombrePoke1 = sc.nextLine().trim().toUpperCase();

        Pokemon poke1 = equipos.getMochila(entrenador1.getNombre(),entrenador1.getApellido()).getPokemon(nombrePoke1);




        System.out.println("\nPokémon del equipo de " + entrenador2.getNombre() + ":");
        try {
            System.out.println(equipos.getMochila(entrenador2.getNombre(), entrenador2.getApellido()).listar());
        } catch (capacidadInvalidaException e) {
            System.out.println(e.getMessage());
        }
        System.out.print("Ingresá el nombre del Pokémon principal: ");
        String nombrePoke2 = sc.nextLine().trim().toUpperCase();

        Pokemon poke2 = equipos.getMochila(entrenador2.getNombre(),entrenador2.getApellido()).getPokemon(nombrePoke2);


        System.out.println("\n¡La batalla comienza entre " + poke1.getNombre() + " y " + poke2.getNombre() + "!\n");

        // === BUCLE DE BATALLA ===
        boolean batallaActiva = true;
        int turno = 1;

        while (batallaActiva) {
            System.out.println("=== Turno " + turno + " ===");

            Entrenador atacante = (turno % 2 != 0) ? entrenador1 : entrenador2;
            Entrenador defensor = (turno % 2 != 0) ? entrenador2 : entrenador1;

            Pokemon pokeAtacante = (turno % 2 != 0) ? poke1 : poke2;
            Pokemon pokeDefensor = (turno % 2 != 0) ? poke2 : poke1;

            System.out.println("\nTurno de " + atacante.getNombre() + " (" + pokeAtacante.getNombre() + ")");
            System.out.println("Vida de tu Pokémon: " + pokeAtacante.getVidaRestante());
            System.out.println("Vida del oponente: " + pokeDefensor.getVidaRestante());
            System.out.println("1. Atacar");
            System.out.println("2. Cambiar Pokémon");
            System.out.print("Elegí una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            if (opcion == 1) {
                gestorBatalla.atacar(pokeAtacante, pokeDefensor);
                System.out.println(pokeAtacante.getNombre() + " atacó a " + pokeDefensor.getNombre());
                System.out.println("Vida de " + pokeDefensor.getNombre() + ": " + pokeDefensor.getVidaRestante());

                if (pokeDefensor.getVidaRestante() <= 0) {
                    System.out.println("\n" + pokeDefensor.getNombre() + " ha sido derrotado.");

                    // Verificar si todos los Pokémon del defensor están debilitados
                    boolean todosDebilitados = true;
                    for (Pokemon p : equipos.getMochila(defensor.getNombre(), defensor.getApellido()).obtenerTodos()) {
                        if (p.getVidaRestante() > 0) {
                            todosDebilitados = false;
                            break;
                        }
                    }

                    if (todosDebilitados) {
                        System.out.println("\n" + atacante.getNombre() + " ha ganado la batalla!");
                        batallaActiva = false;
                    } else {
                        System.out.println(defensor.getNombre() + ", elegí otro Pokémon:");
                        try {
                            System.out.println(equipos.getMochila(defensor.getNombre(), defensor.getApellido()).listar());
                        } catch (capacidadInvalidaException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.print("Nombre del nuevo Pokémon: ");
                        String nuevoNombre = sc.nextLine().trim().toUpperCase();

                        Pokemon nuevoPoke = new Pokemon(nuevoNombre);


                        if (nuevoPoke != null) {
                            if (turno % 2 != 0) poke2 = nuevoPoke;
                            else poke1 = nuevoPoke;
                            System.out.println(nuevoPoke.getNombre() + " entra en combate!");
                        } else {
                            System.out.println("No se encontró ese Pokémon, pierde el turno.");
                        }
                    }
                }

            } else if (opcion == 2) {
                System.out.println(atacante.getNombre() + ", elegí otro Pokémon:");
                try {
                    System.out.println(equipos.getMochila(atacante.getNombre(), atacante.getApellido()).listar());
                } catch (capacidadInvalidaException e) {
                    System.out.println(e.getMessage());
                }
                System.out.print("Nombre del nuevo Pokémon: ");
                String nuevoNombre = sc.nextLine().trim().toUpperCase();

                Pokemon nuevoPoke = new Pokemon(nuevoNombre);


                if (nuevoPoke != null) {
                    if (turno % 2 != 0) poke1 = nuevoPoke;
                    else poke2 = nuevoPoke;
                    System.out.println("Nuevo Pokémon activo: " + nuevoPoke.getNombre());
                } else {
                    System.out.println("No se encontró ese Pokémon.");
                }
            }

            if (batallaActiva) turno++;
        }
    }
}




