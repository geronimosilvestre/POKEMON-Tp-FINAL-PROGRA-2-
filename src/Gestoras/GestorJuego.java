package Gestoras;

import Colecctions.Equipos;
import Colecctions.Mochila;
import Colecctions.Pokedex;
import Exceptions.*;
import Menu.Menu;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;
import Utiles.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static Gestoras.GestorDamage.seleccionarNuevoPokemon;

public class GestorJuego {

    public  static Equipos menuEquipos(Scanner sc, Pokedex pokedex, Equipos equipos) {
        boolean volver = false;

        while (!volver) {

            //se muestra menu equipos

            Menu.menuEquipos();

            int opcion = 9;
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida");
            }
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    //caso 1 crear todos el sistema de entrenador y pokemon
                    try {

                        agregarPeleador(sc, pokedex, equipos);

                    } catch (capacidadInvalidaException e) {
                        System.out.println(e.getMessage());
                    }catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }


                }
                case 2 -> {

                    //caso 2 muestra los 2 equipos enteros o 1 solo creado
                    try {
                        System.out.println(equipos.listar());
                    } catch (capacidadInvalidaException | existException e) {
                        System.out.println(e.getMessage());
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                case 3->{

                    //busca un equipo para importarlo
                    System.out.println("ingrese el nombre del archivo");

                    String nombreArchivo = sc.nextLine();
                    JSONTokener tokener = null;
                    //Tokener a partir del nombre
                    try {
                        tokener = JsonUtiles.leerUnJson(nombreArchivo);
                    }catch (IllegalArgumentException e) {
                        System.out.println("Uno de sus pokemones no se encuentra en nuestra lista");
                    }
                    catch (FileNotFoundException e){
                        System.out.println(e.getMessage());
                        break;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }



                    JSONArray jsonArray = null;
                        //Metemos el tokener en el array
                    try {
                        jsonArray = new JSONArray(tokener);
                    } catch (JSONException e) {
                        System.out.println(e.getMessage());
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    //Asignar al equipo creado la deserealizacion del array

                    try {
                        equipos =  equipos.fromJSON(jsonArray);
                    }catch (IllegalArgumentException e) {
                        System.out.println("Uno de sus pokemones no se encuentra en nuestra lista");
                    } catch (JSONException e) {
                        System.out.println(e.getMessage());
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }




                    try {
                        System.out.println(equipos.listar());
                    } catch (capacidadInvalidaException e) {
                        System.out.println(e.getMessage());
                    } catch (existException e) {
                        System.out.println(e.getMessage());
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }



                }
                case 0 -> volver = true;
                default -> System.out.println("Opción inválida.");
            }
        }
        return equipos;
    }

    public static void agregarPeleador(Scanner sc, Pokedex pokedex, Equipos equipos)  throws capacidadInvalidaException, IllegalArgumentException, mochilaVaciaException  {


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


            int op = 9;
            try {
                op = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida");
            }
            sc.nextLine();

            switch (op) {
                case 1 -> {
                    int tamanio= mochila.size();
                //La mochila se agrega con pomenones random y se completa hasta que haya 3 pokemones dentro suyo
                    while (tamanio < 3) {
                        Pokemon p = pokedex.getRandom();
                        try {
                            mochila.agregar(p);
                            tamanio++;
                            System.out.println("Agregado: " + p.getNombre());
                        } catch (capacidadInvalidaException | existException e ) {
                            System.out.println("No se pudo agregar el Pokémon: " + e.getMessage());
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                        }

                    }
                    if(tamanio == 3) {
                        System.out.println("La mochila ya contiene 3 pokemones");
                    }

                }
                case 2 -> {

                    //Si se elige agregar a mano van saliendo pokemones random de la pokedex para que el usuario decida si le gusta el que le toco o no
                    //Puede meter menos 1 2 o 3 pokemones
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
                    //Lista la mochila con los pokemones que hay dentro
                    try {
                        System.out.println(mochila.listar());
                    } catch (capacidadInvalidaException e) {
                        System.out.println(e.getMessage());
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {

                    //Se elimina un pokemon de la mochila cuando el usuario escribe el nombre a mano
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
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                case 5 ->{

                    //Sistema para guardar en las variables los entrenadores y pokemones para que esten listos para la batalla
                    boolean agregado = false;

                    while (!agregado) {
                        try {



                             entrenador.setNombre(nombre);
                             entrenador.setApellido(apellido);

                             equipos.agregarEquipo(entrenador, mochila);



                            agregado = true;
                            volverMochila = false;
                            System.out.println("Peleador agregado con éxito.");


                        }catch (mochilaVaciaException e){
                            System.out.println("No se pudo guardar el equipo: "+ e.getMessage());
                            volverMochila = false;
                            agregado = true;
                        }catch (existException | capacidadInvalidaException e) {
                            System.out.println("Error al guardar el equipo: " + e.getMessage());
                            agregado = true;
                        }catch(Exception e){
                            System.out.println(e.getMessage());
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
    //Ademas de listar los pokemones agregamos una interaccion para mostrar toda la info completa de un pokemon especifico que el usuario quiera mediante el indice
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
            int opcion = 9;
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida");
            }
            sc.nextLine();

            //Case 1 entra en la batalla

            switch (opcion) {
                case 1 -> {
                    try {
                        if (equipos.size() != 2) {
                            throw new emptyTeamsException("Debe haber exactamente 2 equipos para iniciar la batalla");
                        }

                        iniciarBatalla(equipos, gestorDamage);

                    }
                    catch(emptyTeamsException e){
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                    // Case 2 Guardar datos de la batalla previa
                case 2 -> {
                    try {
                        if (equipos.size() != 2) {
                            throw new capacidadInvalidaException("Los equipos estan  incompletos");
                        }
                        boolean algunEquipoDerrotado = false;

                        for (Entrenador e : equipos.getEntrenadores()) {
                            Mochila aux = equipos.getMochila(e.getNombre(), e.getApellido());
                            int contadorVivos = 0;

                            for (Pokemon p : aux.obtenerTodos()) {
                                if (p.getVidaRestante() > 0) {
                                    contadorVivos++;
                                }

                            }

                            if (contadorVivos == 0) {
                                algunEquipoDerrotado = true;
                            }//Si uno de los 2 equipos suma 0 vivos entonces ya puedo decir que uno de los 2 perdio
                        }

                        //Si encuentra una mochila derrotada por completo entonces no puede iniciar la batalla
                        if (!algunEquipoDerrotado) {
                            throw new teamsStillAliveException("No se puede guardar la batalla hasta que uno de los equipos sea derrotado por completo.");
                        }

                        //La batalla se guarda similar a como se importa un equipo desde fuera, solo que cuando se guardan la vida restante de algunos pokemones esta en 0

                        JsonUtiles.grabarUnJson(equipos.toJSON(), "BatallaReciente.json");
                        System.out.println("Batalla guardada correctamente en BatallaReciente.json");
                        for (Entrenador e : equipos.getEntrenadores()) {
                            for(Pokemon p : equipos.getMochila(e.getNombre(),e.getApellido()).obtenerTodos()){
                                p.setVidaRestante(p.getVidaCompleta());
                            }
                        }

                    }catch (teamsStillAliveException e){
                        System.out.println(e.getMessage());
                    }
                    catch (capacidadInvalidaException e) {
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

    public static void iniciarBatalla(Equipos equipos, GestorDamage gestorDamage) throws emptyTeamsException {
        if (equipos.size() != 2){
            throw new emptyTeamsException("complete los equipos antes de iniciar la batalla");
        }
        for (Entrenador e : equipos.getEntrenadores()) {
            for (Pokemon p : equipos.getMochila(e.getNombre(), e.getApellido()).obtenerTodos()) {
               p.setVidaRestante(p.getVidaCompleta()); //Sin importar la vida anterior con la que se inicia la batalla se le restaura a la original
            }
        }
        Scanner sc = new Scanner(System.in);

        System.out.println("=== COMIENZA LA BATALLA ===");
        System.out.println("Listado de equipos:");
        try {
            System.out.println(equipos.listar());
        } catch (capacidadInvalidaException | existException e) {
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        //Necesitamos obtener del hashmap dentro de equipos los 2 entrenadores sin importar su nombre para que uno sea el atacante y el otro el defensor de manera random
        //Lo guardamos en un arraylist para usar los indices
        ArrayList<Entrenador> entrenadores = equipos.getEntrenadores();



        // === SELECCIÓN DE ENTRENADORES ===

        Entrenador entrenador1 =  entrenadores.get(0);
        System.out.println("Nombre del primer entrenador: " + entrenador1.getNombre());




        Entrenador entrenador2 =   entrenadores.get(1);


        Mochila mochila1 = equipos.getMochila(entrenador1.getNombre(),entrenador1.getApellido());
        Mochila mochila2 = equipos.getMochila(entrenador2.getNombre(),entrenador2.getApellido());
        Pokemon pokemon1 = null;
        Pokemon pokemon2 = null;

        System.out.println("Nombre del segundo entrenador: " + entrenador2.getNombre());

        // === ELECCIÓN DE POKÉMON PRINCIPALES ===
        System.out.println("\nPokémon del equipo de " + entrenador1.getNombre() + ":");


        try {
            System.out.println(mochila1.listar());
        } catch (capacidadInvalidaException e) {
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        //Bucle while  para cuando  el usuario no  ingresa bien el pokemon principal por consola
        boolean flag = false;
        System.out.print("Ingresá el numero para seleccionar un Pokémon como principal: ");
        while (!flag) {

            try {
                int indice = sc.nextInt();
                pokemon1 = mochila1.getPokemonIndex(indice);
                sc.nextLine();
                flag = true;
            } catch (noIndexFoundException e) {
                sc.nextLine();
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("No ha ingresado un numero");
            } catch (capacidadInvalidaException e) {
                sc.nextLine();
                System.out.println(e.getMessage());
            } catch (Exception e) {
                sc.nextLine();
                System.out.println(e.getMessage());
            }
        }






        System.out.println("\nPokémon del equipo de " + entrenador2.getNombre() + ":");
        try {
            System.out.println(mochila2.listar());
        } catch (capacidadInvalidaException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.print("Ingresá el numero para seleccionar un Pokémon como principal: ");
         flag = false;

        //Bucle while   para el defensor
        while (!flag) {
            try {
                int indice = sc.nextInt();
                pokemon2 = mochila2.getPokemonIndex(indice);
                sc.nextLine();
                flag = true;
            } catch (noIndexFoundException e) {
                sc.nextLine();
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("No ha ingresado un numero");
            } catch (capacidadInvalidaException e) {
                sc.nextLine();
                System.out.println(e.getMessage());
            } catch (Exception e) {
                sc.nextLine();
                System.out.println(e.getMessage());
            }



    }



        System.out.println("\n¡La batalla comienza entre " + pokemon1.getNombre() + " y " + pokemon2.getNombre() + "!\n");

        // === BUCLE DE BATALLA ===
        boolean batallaActiva = true;
        int turno = 1;

        Entrenador entrenadorAtacante;
        Entrenador entrenadorDefensor;
        Pokemon pokemonAtacante;
        Pokemon pokemonDefensor;


        //Se intercambia atacante y defensor dependiendo si el turno es par o impar
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

            //Opcion 2 es atacar, la 3 es cambiar de pokemon
            System.out.println(Menu.opcionesBatalla(entrenadorAtacante,pokemonAtacante,pokemonDefensor));

            int opcion = 9;
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida");
            }
            sc.nextLine();

            if (opcion == 4) {
                //Calculos de ataque
                System.out.println(gestorDamage.atacar(pokemonAtacante, pokemonDefensor));
                System.out.println(pokemonAtacante.getNombre() + " atacó a  " + pokemonDefensor.getNombre());
                System.out.println("Vida de " + pokemonDefensor.getNombre() + ": " + pokemonDefensor.getVidaRestante());

                //Comprobar su murio
                if (pokemonDefensor.getVidaRestante() <= 0) {
                    System.out.println("\n" + pokemonDefensor.getNombre() + " ha sido derrotado.");

                //Comprobar si murieron todos
                    boolean todosDebilitados = true;
                    for (Pokemon p : equipos.getMochila(entrenadorDefensor.getNombre(), entrenadorDefensor.getApellido()).obtenerTodos()) {
                        if (p.getVidaRestante() > 0) {
                            todosDebilitados = false;
                            break;
                        }
                    }

                    if (todosDebilitados) {
                        System.out.println(
                                String.format("""
        +-------------------------------------------+
        |                                           |
        |   %s %s ha ganado la batalla!             |
        |                                           |
        +-------------------------------------------+
        """,
                                        entrenadorAtacante.getNombre(),
                                        entrenadorAtacante.getApellido()
                        ));

                        //Termina la batalla si murieron todos los pokemones de un solo entrenador
                        batallaActiva = false;
                    } else {//Si el pokemon defensor esta muerto y le quedan vivos se le pide cambiarlo


                        System.out.println("\n" + entrenadorDefensor.getNombre() + ", elegí otro Pokémon:");
                        try {
                            Mochila mochilita = equipos.getMochila(entrenadorDefensor.getNombre(), entrenadorDefensor.getApellido());
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < mochilita.size(); i++) {
                                Pokemon p = mochilita.getPokemonIndex(i);
                            sb.append("    "+p.getNombre() + " `♡´ vida restante: " + p.getVidaRestante() + "\n");

                            }
                            //Se maneja el error si se elige un pokemon deshabilitado

                            System.out.println(sb);
                        }catch (noIndexFoundException e) {
                            System.out.println(e.getMessage());
                        } catch (capacidadInvalidaException e) {
                            System.out.println(e.getMessage());
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                        }

                        Pokemon cambioPokemon = seleccionarNuevoPokemon(sc, equipos, entrenadorDefensor, turno, true);
                        //Se le asigna el pokemon al defensor
                        if (turno % 2 != 0)
                            pokemon2 = cambioPokemon;
                        else
                            pokemon1 = cambioPokemon;


                    }

                }
            } else if (opcion == 5) {//Opcion 3 del menu para que el atacante cambie su pokemon principal por otro a gusto propio
                System.out.println(entrenadorAtacante.getNombre() + ", elegí otro Pokémon:");

                try {
                    Mochila mochilita = equipos.getMochila(entrenadorAtacante.getNombre(), entrenadorAtacante.getApellido());
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mochilita.size(); i++) {
                        Pokemon p = mochilita.getPokemonIndex(i);
                        sb.append("    "+p.getNombre() + " `♡´ vida restante: " + p.getVidaRestante() + "\n");

                    }

                    System.out.println(sb);
                } catch (noIndexFoundException e) {
                    System.out.println(e.getMessage());
                }catch (capacidadInvalidaException e) {
                    System.out.println(e.getMessage());
                }catch(Exception e){
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
