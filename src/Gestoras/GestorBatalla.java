package Gestoras;

import Enums.ETipo;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;

public class GestorBatalla {

    private Equipos equipos;

    public GestorBatalla(Equipos equipos) {
        this.equipos = equipos;
    }

    // ✅ Atacar entre dos Pokémon
    public void atacar(Pokemon atacante, Pokemon defensor) {
      ETipo tipoAtacante= atacante.getTipo();
      ETipo tipoDefensa= defensor.getTipo();
        double efectividad = tipoAtacante.calcularEfectividad(tipoDefensa);

        // Daño base = ataque - defensa (mínimo 1)
        int baseDamage = Math.max(1, atacante.getAtaque() - defensor.getDefensa());

        // Ajustamos por efectividad
        int damage = (int) Math.round(baseDamage * efectividad);

        // Restamos la vida al defensor
        int nuevaVida = defensor.getVidaRestante() - damage;
        if (nuevaVida < 0) nuevaVida = 0;
        defensor.setVidaRestante(nuevaVida);

        // Mensajes
        System.out.println("💥 " + atacante.getNombre() + " atacó a " + defensor.getNombre());
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
}