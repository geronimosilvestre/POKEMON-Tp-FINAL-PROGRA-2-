package Enums;

import java.util.UUID;

public enum ENombre {
        ARBOK("Arbok", 95, 60, 44, ETipo.PLANTA ),
        BULBASAUR("Bulbasaur", 105, 49, 45, ETipo.PLANTA),
        CATERPIE("Caterpie", 80, 30, 35, ETipo.PLANTA),
        CHARMANDER("Charmander", 95, 52, 35, ETipo.FUEGO),
        DRAGONITE("Dragonite", 150, 85, 70, ETipo.FUEGO),
        GEODUDE("Geodude", 120, 45, 60, ETipo.ROCA),
        GROWLITHE("Growlithe", 100, 55, 40, ETipo.FUEGO),
        SNORUNT("Snorunt", 95, 45, 30, ETipo.HIELO),
        MAGNETITE("Magnetite", 90, 55, 35, ETipo.ELECTRICO),
        PIKACHU("Pikachu",  100, 100, 50, ETipo.ELECTRICO),
        SQUIRTLE("Squirtle",110, 48, 50,  ETipo.AGUA),
        JIGGLYPUFF("Jigglypuff", 115, 45, 20, ETipo.HIELO);



    private String nombre;
    private int vidaCompleta;
    private int ataque;
    private int defensa;
    private ETipo tipo;

    ENombre(String nombre,  int vidaCompleta, int ataque, int defensa, ETipo tipo) {
        this.nombre = nombre;
        this.vidaCompleta = vidaCompleta;
        this.ataque = ataque;
        this.defensa = defensa;
        this.tipo = tipo;
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
    public  ETipo getTipo() {
        return tipo;
    }
}
