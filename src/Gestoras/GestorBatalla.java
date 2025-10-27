package Gestoras;

import Model.Entrenador;
import Model.Pokemon;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GestorBatalla {
<<<<<<< HEAD
    //comentario
    //comenatrio2
=======
    GestorEquipo equipo;
    int vidaEquipoUno;
    int vidaEquipoDos;
    int ronda;


    public GestorBatalla(GestorEquipo equipo) {
        this.equipo = equipo;
        this.vidaEquipoUno = 0;
        this.vidaEquipoDos = 0;
        this.ronda = 1;
    }

    public int getVidaEquipoUno() {
        return vidaEquipoUno;
    }

    public void setVidaEquipoUno(int vidaEquipoUno) {
        this.vidaEquipoUno = vidaEquipoUno;
    }

    public int getVidaEquipoDos() {
        return vidaEquipoDos;
    }

    public void setVidaEquipoDos(int vidaEquipoDos) {
        this.vidaEquipoDos = vidaEquipoDos;
    }

    public void iniciarBatalla(GestorEquipo equipo) //Prototipo de batalla, falta mejorarlo y separarlo en mas metodos quizas?, probablemente deba de eir en el main
    {

        Set<Pokemon> equipoUno = equipo.getEquipo(1);
        Set<Pokemon> equipoDos = equipo.getEquipo(2);

        Pokemon pokemonEquipoUno = null;
        Pokemon pokemonEquipoDos = null;

        setVidaEquipoUno(getVidaTotalDelEquipo(equipoUno));
        setVidaEquipoDos(getVidaTotalDelEquipo(equipoDos));

        for (Pokemon p : equipoUno) {
            if (p.isCapturado()) {
                pokemonEquipoUno = p;
                break;
            }
        }

        for (Pokemon p : equipoDos) {
            if (p.isCapturado()) {
                pokemonEquipoDos = p;
                break;
            }
        }

        if (pokemonEquipoUno != null && pokemonEquipoDos != null) {
            atacarOtroPokemon(pokemonEquipoUno, pokemonEquipoDos);


        }

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

    }

    public int getVidaTotalDelEquipo(Set<Pokemon> equipo)
    {
        int vidaTotal = 0;
        for (Pokemon p: equipo) {

            vidaTotal += p.getVida();
        }
    }

    public boolean restarVidaTotalDelEquipo(int vida, String equipo )
    {
        if(vida >=1) {

            {
                if(equipo.equals("uno")) {
                    this.vidaEquipoUno -= vida;
                }
                else if(equipo.equals("dos")) {
                    this.vidaEquipoDos -= vida;
                }
            }

        }
        return true;
    }

    public boolean atacarOtroPokemon(Pokemon atacante, Pokemon defensor)
    {
        int ataque = atacante.getAtaque();
        int defensa = defensor.getDefensa();
        if(ataque > defensa)
        {
            restarVidaTotalDelEquipo(ataque,'dos');
            return true;
        }
        return false;


    }





>>>>>>> 7662752c2d4595bd6f9ba5dcb76479d4e2660b84
}
