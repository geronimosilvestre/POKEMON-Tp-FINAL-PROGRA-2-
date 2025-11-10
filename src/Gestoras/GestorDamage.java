package Gestoras;

import Colecctions.Equipos;
import Colecctions.Mochila;
import Enums.ETipo;
import Exceptions.capacidadInvalidaException;
import Exceptions.existException;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;

import java.util.Scanner;

public class GestorDamage {

    private Equipos equipos;

    public GestorDamage(Equipos equipos) {
        this.equipos = equipos;
    }

    // ✅ Atacar entre dos Pokémon
    public void atacar(Pokemon atacante, Pokemon defensor) {
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

        // Mensajes
        System.out.println( atacante.getNombre() + " ataco a" + defensor.getNombre());
        System.out.println("Tipo atacante: " + atacante.getTipo() + " → Tipo defensor: " + defensor.getTipo());
        System.out.println("Efectividad: x" + efectividad);
        System.out.println("Daño causado: " + damage);
        System.out.println("💔 Vida restante de " + defensor.getNombre() + ": " + defensor.getVidaRestante());
    }

    // ✅ Suma la vida total de todos los Pokémon de una mochila
    public int calcularVidaTotal(Mochila mochila) {
        int total = 0;
        for (Pokemon p : mochila.obtenerTodos()) {
            total += p.getVidaRestante();
        }
        return total;
    }

    // ✅ Muestra la vida total del equipo de un entrenador
    public void mostrarVidaEquipo(Entrenador entrenador, Mochila mochila) {
        int vidaTotal = calcularVidaTotal(mochila);
        System.out.println("❤️ Vida total del equipo de " + entrenador.getNombre() + ": " + vidaTotal);
    }

//    public static Pokemon elegirNuevoPokemon(Scanner sc, Equipos equipos, Entrenador entrenadorDefensor, int turno,Pokemon pokemon1,Pokemon pokemon2)
//    {
//        System.out.println(entrenadorDefensor.getNombre() + ", elegí otro Pokémon:");
//        try {
//            Mochila mochilita =  equipos.getMochila(entrenadorDefensor.getNombre(), entrenadorDefensor.getApellido());
//            StringBuilder sb= new StringBuilder();
//            for (int i = 0; i < mochilita.size(); i++) {
//                Pokemon p = mochilita.getPokemonIndex(i);
//                sb.append(p.getNombre() + " - " + p.getVidaRestante() + "\n");
//
//            }
//
//            System.out.println(sb);
//        } catch (capacidadInvalidaException e) {
//            System.out.println(e.getMessage());
//        }
//
//        Pokemon cambioPokemon = null;
//
//        while (cambioPokemon == null) {
//            try {
//                System.out.print("Su pokemon se murio, escribe a mano el nombre de otro Pokémon de la mochila: ");
//                String nuevo = sc.nextLine();
//
//
//
//                cambioPokemon = equipos.getMochila(entrenadorDefensor.getNombre(), entrenadorDefensor.getApellido()).getPokemon(nuevo);
//
//                if (cambioPokemon != null) {
//                    if (turno % 2 != 0)
//                        pokemon2 = cambioPokemon;
//                    else
//                        pokemon1 = cambioPokemon;
//
//                    System.out.println(cambioPokemon.getNombre() + " entra en combate!");
//                }
//
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            } catch (existException e) {
//                System.out.println(e.getMessage());
//                System.out.println("No se encontró ese Pokémon, vuelva a buscar.");
//            }
//        }
//        return  cambioPokemon;
//    }

    public static Pokemon seleccionarNuevoPokemon(Scanner sc, Equipos equipos, Entrenador entrenador, int turno, boolean porMuerte // true si el Pokémon murió, false si es cambio normal
    ) {
        Pokemon nuevoPokemon = null;

        while (nuevoPokemon == null) {
            try {
                if (porMuerte) {
                    System.out.print("Su Pokémon se murió. Escribí el nombre de otro Pokémon de la mochila: ");
                } else {
                    System.out.print("Para reemplazar el Pokémon activo, escribí el nombre de otro: ");
                }

                String nombreNuevo = sc.nextLine();

                Mochila mochila = equipos.getMochila(entrenador.getNombre(), entrenador.getApellido());
                nuevoPokemon = mochila.getPokemon(nombreNuevo);

                if (nuevoPokemon.getVidaRestante() <= 0) {
                    nuevoPokemon = null;
                    throw new IllegalArgumentException("El Pokémon elegido está debilitado. Elegí otro.");
                }

                System.out.println(nuevoPokemon.getNombre() + " entra en combate!");

            } catch (IllegalArgumentException | existException e) {
                System.out.println(e.getMessage());
                System.out.println("Intentá con otro nombre.\n");
            }
        }

        return nuevoPokemon;
    }


}