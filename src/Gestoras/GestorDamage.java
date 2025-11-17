package Gestoras;

import Colecctions.Equipos;
import Colecctions.Mochila;
import Enums.ETipo;
import Exceptions.capacidadInvalidaException;
import Exceptions.existException;
import Exceptions.noIndexFoundException;
import Exceptions.pokemonDebilitadoException;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;
import Menu.Menu;

import java.util.Scanner;

public class GestorDamage {

    private Equipos equipos;

    public GestorDamage(Equipos equipos) {
        this.equipos = equipos;
    }

    // Atacar entre dos Pokémon
    public String atacar(Pokemon atacante, Pokemon defensor) {
      ETipo tipoAtacante= atacante.getTipo();
      ETipo tipoDefensa= defensor.getTipo();
        double efectividad = tipoAtacante.calcularEfectividad(tipoDefensa);

        // Daño base = ataque - defensa (mínimo 1)
        int baseDamage = (int) Math.max(1, atacante.ataqueNormal() - defensor.getDefensa());

        // Ajustamos por efectividad
        int damage = (int) Math.round(baseDamage * efectividad);

        // Restamos la vida al defensor
        int nuevaVida = defensor.getVidaRestante() - damage;
        if (nuevaVida < 0) nuevaVida = 0;
        defensor.setVidaRestante(nuevaVida);

        return Menu.mostrarResultadoAtaque(atacante,defensor,efectividad,damage);

    }

    // Suma la vida total de todos los Pokémon de una mochila
    public int calcularVidaTotal(Mochila mochila) {
        int total = 0;
        for (Pokemon p : mochila.obtenerTodos()) {
            total += p.getVidaRestante();
        }
        return total;
    }
    public Entrenador devolverGanador(Entrenador a, Entrenador b){

        Mochila mochilaA = equipos.getMochila(a.getNombre(),a.getApellido());
        Mochila mochilaB = equipos.getMochila(b.getNombre(),b.getApellido());

        int vidaA=calcularVidaTotal(mochilaA);
        int vidaB=calcularVidaTotal(mochilaB);
        //retorna el entrenador que ttiene mas vida resttante en su mochila
        if(vidaA>vidaB){return a;}

        return b;
    }

    // Muestra la vida total del equipo de un entrenador
    public void mostrarVidaEquipo(Entrenador entrenador, Mochila mochila) {
        int vidaTotal = calcularVidaTotal(mochila);
        System.out.println("<3 Vida total del equipo de " + entrenador.getNombre() + ": " + vidaTotal);
    }



    //Cuando muere o cambia el turno devuelve otro pokemon para usar este ultimo

    public static Pokemon seleccionarNuevoPokemon(Scanner sc, Equipos equipos, Entrenador entrenador, int turno, boolean porMuerte // true si el Pokémon murió, false si es cambio normal
    ) {
        Pokemon nuevoPokemon = null;

        while (nuevoPokemon == null) {
            try {
                if (porMuerte) {
                    System.out.print("Su Pokémon ha muerto (ಥ_ಥ)  Escribí el numero de otro Pokémon de la mochila: ");
                } else {
                    System.out.print("Para reemplazar el Pokémon activo, escribí el numero de otro: ");
                }

                int indice=sc.nextInt();
                sc.nextLine();

                Mochila mochila = equipos.getMochila(entrenador.getNombre(), entrenador.getApellido());
                nuevoPokemon = mochila.getPokemonIndex(indice);

                if (nuevoPokemon.getVidaRestante() <= 0) {
                    nuevoPokemon = null;
                    throw new pokemonDebilitadoException("El Pokémon elegido está debilitado. Elegí otro.");
                }

                System.out.println(nuevoPokemon.getNombre() + " entra en combate!");

            } catch (pokemonDebilitadoException|IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Intentá con otro nombre.\n");
            } catch (capacidadInvalidaException e) {
                System.out.println("error: "+ e.getMessage());
            } catch (noIndexFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        return nuevoPokemon;
    }


}