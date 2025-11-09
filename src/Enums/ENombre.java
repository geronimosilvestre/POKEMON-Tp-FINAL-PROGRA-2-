package Enums;

import java.util.UUID;

public enum ENombre {
        ARBOK("Arbok", 95, 60, 44),
        BULBASAUR("Bulbasaur", 105, 49, 45),
        CATERPIE("Caterpie", 80, 30, 35),
        CHARMANDER("Charmander", 95, 52, 35),
        DRAGONITE("Dragonite", 150, 85, 70),
        GEODUDE("Geodude", 120, 45, 60),
        GROWLITHE("Growlithe", 100, 55, 40),
        SNORUNT("Snorunt", 95, 45, 30),
        MAGNETITE("Magnetite", 90, 55, 35),
        PIKACHU("Pikachu",  100, 100, 50),
        SQUIRTLE("Squirtle",110, 48, 50),
        JIGGLYPUFF("Jigglypuff", 115, 45, 20);



    private String nombre;
    private int vidaCompleta;
    private int ataque;
    private int defensa;

    ENombre(String nombre,  int vidaCompleta, int ataque, int defensa) {
        this.nombre = nombre;
        this.vidaCompleta = vidaCompleta;
        this.ataque = ataque;
        this.defensa = defensa;
    }


    public String getNombre() {
        return nombre;
    }
    public int getVidaCompleta() {
        return vidaCompleta;
    }
    public int getAtaque() {
        return ataque;
    }
    public int getDefensa() {
        return defensa;
    }
}
