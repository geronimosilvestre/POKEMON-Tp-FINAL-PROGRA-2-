package Gestoras;

import Enums.ETipo;
import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;

import java.util.*;

public class GestorBatalla {
    private Equipos equipos;


    public GestorBatalla(Equipos equipos) {
        this.equipos = equipos;
    }




    public boolean atacar(Pokemon atacante, Pokemon defensor)
    {

        int ataque = atacante.getAtaque();

        int defensa = defensor.getDefensa();

        if(ataque > defensa)
        {
            int damage = ataque -  defensa;

            ETipo tipo = atacante.getTipo();
            damage = (int) tipo.calcularEfectividad(defensor.getTipo());

            if(damage > 0)
            {
                defensor.setVidaRestante(atacante.getVidaCompleta()-damage);
            }
        }
    }

}
