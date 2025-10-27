package Gestoras;

import Model.Entrenador;
import Model.Pokemon;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GestorBatalla {
    GestorEquipo equipo;
    int ronda = 0;


    public GestorBatalla(GestorEquipo equipo) {
        this.equipo = equipo;
        this.ronda = 1;

    }

    public void iniciarBatalla(GestorEquipo equipo)
    {

        Set<Pokemon> equipoUno = equipo.getEquipo(1);
        Set<Pokemon> equipoDos = equipo.getEquipo(2);

        Pokemon pokemonEquipoUno = null;
        Pokemon pokemonEquipoDos = null;

        pokemonEquipoUno = equipoUno.iterator().next();
        pokemonEquipoDos = equipoDos.iterator().next();







        while(ronda<3)
        {


            ronda++;
        }
    }


    public boolean elegirPokemonParaPelear( int numeroEquipo)
    {
        Set<Pokemon> equipoElegido = equipo.getEquipo(numeroEquipo);

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

    public int getVidaTotalDelEquipo(Set<Pokemon> equipo)
    {
        int vidaTotal = 0;
        for (Pokemon p: equipo) {

            vidaTotal += p.getVida();
        }
        return vidaTotal;
    }

    public boolean restarVidaTotalDelEquipo(int vida, String equipo )
    {
        if(vida >=1) {

        }
        return true;
    }

    public boolean atacarOtroPokemon(Pokemon atacante, Pokemon defensor)
    {
        int ataque = atacante.getAtaque();
        int defensa = defensor.getDefensa();
        if(ataque > defensa)
        {
            //restarVidaTotalDelEquipo(ataque,);
            return true;
        }
        return false;


    }





}
