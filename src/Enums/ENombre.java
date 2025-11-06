package Enums;

public enum ENombre {
        ARBOK("Arbok"),
        BULBASAUR("Bulbasaur"),
        CATERPIE("Caterpie"),
        CHARMANDER("Charmander"),
        DRAGONITE("Dragonite"),
        GEODUDE("Geodude"),
        GROWLITHE("Growlithe"),
        JIGGLYPUFF("Jigglypuff"),
        PIKACHU("Pikachu"),
        SQUIRTLE("Squirtle");



    private String nombre;

    ENombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
