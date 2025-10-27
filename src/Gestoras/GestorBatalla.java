package Gestoras;

import Model.Entrenador;
import Model.Pokemon;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class GestorBatalla {
    GestorEquipo equipo;
    int ronda = 0;


    public GestorBatalla(GestorEquipo equipo) {
        this.equipo = equipo;
        this.ronda = 1;

    }

    public void iniciarBatalla(GestorEquipo equipo, Entrenador entrenador1, Entrenador entrenador2)
    {

        Set<Pokemon> equipoUno = equipo.getEquipo(entrenador1);
        Set<Pokemon> equipoDos = equipo.getEquipo(entrenador2);

        Pokemon pokemonEquipoUno = null;
        Pokemon pokemonEquipoDos = null;

        pokemonEquipoUno = equipoUno.iterator().next();
        pokemonEquipoDos = equipoDos.iterator().next();


        atacarPokemon(pokemonEquipoUno, pokemonEquipoDos);


        while(ronda<3)
        {
            ronda++;
        }
    }



        //listar pokemones en el equipo, el elegido va a ser el indice 1 en el linked hashset.
    public boolean elegirPokemonParaPelear(GestorEquipo equipo,Entrenador entrenador)
    {
        LinkedHashSet<Pokemon> equipoElegido = equipo.getEquipo(entrenador);

        if(! equipoElegido.isEmpty())
        {

            System.out.println("Elija el pokemon con el que pelear ");
            int i = 0;
            for(Pokemon p : equipoElegido)
            {
                System.out.println(i);
                p.toString();
                i++;

            }
            Scanner sc = new Scanner(System.in);
            int numeroElegido = sc.nextInt();
            sc.nextLine();

            i= 0;
            for(Pokemon p : equipoElegido)
            {
                if(i == numeroElegido)
                {
                    p.setElegidoParaPelear(true);

                } else {
                    p.setElegidoParaPelear(false);
                }
                i++;

            }


        }
        return true;
    }

    public void atacarPokemon(Pokemon atacante, Pokemon defensor)
    {

        int ataque = atacante.getAtaque();
        int numRandom = (int) (Math.random() * 1000);

        if(numRandom > 700){
            ataque = ataque*2;
        }

        int defensa = defensor.getDefensa();

        if(ataque > defensa)
        {
            int dañoRecibido = ataque -  defensa;

            defensor.setVida(defensor.getVida()-dañoRecibido);
        }
    }

}
