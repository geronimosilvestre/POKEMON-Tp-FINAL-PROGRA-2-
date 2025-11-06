package Gestoras;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;

import java.util.*;

public class GestorEquipo {
    private HashMap<Entrenador, LinkedHashSet<Pokemon>> equipos;

    public GestorEquipo() {


        this.equipos = new HashMap<>();

    }

    //Crear equipo es para recibir el nombre, los pokemones entran gracias al metodo agregar pokemon
    public boolean crearEquipo(Entrenador entrenador)   {
        LinkedHashSet<Pokemon> mochilaDelEntrenador = new LinkedHashSet<>();
        equipos.put(entrenador,mochilaDelEntrenador);
        return true;
    }

            //mochila se refiere al equipo del entrenador
    //este bloque de codigo va a ir al main
/*    public LinkedHashSet<Pokemon> crearMochilaEntrenador() {


        //size menor a 4 porque es hasta 3 pokemones el equipo
        while (mochilaDelEntrenador.size() < 4) {

            Pokemon pokemonAleatorio = agregarPokemon();


            if (!descartar) {
                // si no se descarta lo  intento capturar
                boolean intentarCapturar = pokemonAleatorio.capturarPokemon();

                if (intentarCapturar) {
                    // si logro capturar entra en la mochila
                    mochilaDelEntrenador.add(pokemonAleatorio);
                }
            }


        }

        return mochilaDelEntrenador;

    }*/

    //Por parametro recibo el numero del equipo que quiero para despues usarlo en la batalla,
    // hay 2 equipos en el hashmap
    public LinkedHashSet<Pokemon> getEquipo(Entrenador entrenador)
    {

        if (equipos.containsKey(entrenador)) {
            return equipos.get(entrenador);
        }
        return null;
    }

    public Pokemon crearPokemonRandom(){
        GestorPokedex GP = new GestorPokedex();
        int numRandom = (int) (Math.random() * GP.pokedexSize());  // creo un numero random desde el 0 hasta la cantidad de pokemones que hay en la pokedex
        return GP.getPokemonEspecifico(numRandom);  //retorno un pokemon que encontro gracias al numero random
    }

    public boolean agregarPokemon(Entrenador entrenador, Pokemon pokemon) {

        if (equipos.containsKey(entrenador)) {
            LinkedHashSet<Pokemon> mochilaDelEntrenador = equipos.get(entrenador);
            mochilaDelEntrenador.add(pokemon);
            return true;
        }
        return false;
    }


    public boolean setPokemonPrincipal(Entrenador entrenador, Pokemon pokemon)
    {

        if (equipos.containsKey(entrenador)) {
            LinkedHashSet<Pokemon> mochilaDelEntrenador = equipos.get(entrenador);
            if(mochilaDelEntrenador.contains(pokemon))
            {
                LinkedHashSet<Pokemon> nuevaMochila = new LinkedHashSet<>();

                // Primero agregamos el elegido como principal
                nuevaMochila.add(pokemon);

                for (Pokemon p : mochilaDelEntrenador) {
                    if (!p.equals(pokemon)) {
                        nuevaMochila.add(p);


                    }
                }

                equipos.put(entrenador, nuevaMochila);
                return true;

            }

        }
        return false;

    }
    public boolean descartarPokemon(Pokemon pokemon) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Desea capturar este pokemon  y meterlo en la mochila ?" + pokemon.toString());
        int numero = sc.nextInt();
        sc.nextLine();
        return numero == 1;

    }
}

