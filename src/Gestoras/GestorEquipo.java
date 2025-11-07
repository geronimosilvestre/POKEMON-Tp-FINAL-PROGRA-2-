package Gestoras;
import Model.Entrenador;
import Model.Pokemon;

import java.util.*;

public class GestorEquipo {
    private Map<Entrenador, Set<Pokemon>> equipos;

    public GestorEquipo() {
        this.equipos = new HashMap<>();
    }

    public boolean crearEquipo(Entrenador entrenador)   {  //Crear equipo es para recibir el nombre, los pokemones entran gracias al metodo agregar pokemon
        equipos.put(entrenador,crearMochilaEntrenador());
    }


    public LinkedHashSet<Pokemon> crearMochilaEntrenador() {
        LinkedHashSet<Pokemon> mochilaDelEntrenador = new LinkedHashSet<>();

        while (mochilaDelEntrenador.size() < 4) {  // size menor a 4 porque es hasta 3 pokemones en la mochila, de ultima se puede cambiar por exception???
            Pokemon pokemonAleatorio = agregarPokemon(); //Obtengo un pokemon aleatorio nuevo
            boolean descartar = descartarPokemon(pokemonAleatorio); // Se le pregunta al usuario si quiere utilizar el nuevo pokemon encontrado, hay que hacerlo en el main??
            if (!descartar) { // si no se descarta lo  intento capturar
                boolean intentarCapturar = pokemonAleatorio.capturarPokemon();

                if (intentarCapturar) { // si logro capturar entra en la mochila
                    mochilaDelEntrenador.add(pokemonAleatorio);
                }
            }


        }

        return mochilaDelEntrenador;

    }



    public Pokemon agregarPokemon() { //este metodo se usa internamente dentro de crearEquipo solamente

        GestorPokedex GP = new GestorPokedex();
        int numRandom = (int) (Math.random() * GP.pokedexSize());  // creo un numero random desde el 0 hasta la cantidad de pokemones que hay en la pokedex
        return GP.getPokemonEspecifico(numRandom);  //retorno un pokemon que encontro gracias al numero random


    }// aca va lo de captura de pokemon

    public boolean descartarPokemon(Pokemon pokemon) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Desea capturar este pokemon  y meterlo en la mochila ?" + pokemon.toString());
        int numero = sc.nextInt();
        sc.nextLine();
        if (numero == 1) {
            return true;
        } else {
            return false;
        }

    }

    public Set<Pokemon> getEquipo(int numeroDelEquipo)  //Por parametro recibo el numero del equipo que quiero para despues usarlo en la batalla, hay 2 equipos en el hashmap
    {

        Set<Pokemon> mochila =  new HashSet<>();
       Iterator<Set<Pokemon>> iterator = equipos.values().iterator();
        if (iterator.hasNext() && numeroDelEquipo == 1) {
            return iterator.next();
        }

        if (iterator.hasNext() && numeroDelEquipo == 2) {
          return iterator.next();

        }

        return null;

    }
}

