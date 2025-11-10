package Enums;

public enum ENombre {
    CHARMANDER("Charmander", 95, 52, 35, ETipo.FUEGO),
    GROWLITHE("Growlithe", 100, 55, 40, ETipo.FUEGO),
    PONYTA("Ponyta", 100, 85, 55, ETipo.FUEGO),
    ARCANINE("Arcanine", 140, 90, 80, ETipo.FUEGO),
    CHARIZARD("Charizard", 150, 84, 78, ETipo.FUEGO),
    MOLTRES("Moltres", 160, 100, 90, ETipo.FUEGO),
    BULBASAUR("Bulbasaur", 105, 49, 45, ETipo.PLANTA),
    IVYSAUR("Ivysaur", 120, 62, 60, ETipo.PLANTA),
    VENUSAUR("Venusaur", 160, 82, 83, ETipo.PLANTA),
    ODDISH("Oddish", 90, 50, 55, ETipo.PLANTA),
    GLOOM("Gloom", 110, 65, 65, ETipo.PLANTA),
    VICTREEBEL("Victreebel", 125, 105, 65, ETipo.PLANTA),
    TANGELA("Tangela", 115, 55, 115, ETipo.PLANTA),
    GEODUDE("Geodude", 120, 45, 60, ETipo.ROCA),
    ONIX("Onix", 110, 45, 160, ETipo.ROCA),
    RHYDON("Rhydon", 180, 100, 120, ETipo.ROCA),
    KABUTOPS("Kabutops", 130, 115, 105, ETipo.ROCA),
    AERODACTYL("Aerodactyl", 150, 105, 65, ETipo.ROCA),
    SQUIRTLE("Squirtle",110, 48, 50, ETipo.AGUA),
    WARTORTLE("Wartortle", 125, 63, 80, ETipo.AGUA),
    BLASTOISE("Blastoise", 160, 83, 100, ETipo.AGUA),
    PSYDUCK("Psyduck", 100, 52, 48, ETipo.AGUA),
    GOLDUCK("Golduck", 140, 82, 78, ETipo.AGUA),
    TENTACRUEL("Tentacruel", 135, 70, 65, ETipo.AGUA),
    SLOWBRO("Slowbro", 150, 75, 110, ETipo.AGUA),
    CLOYSTER("Cloyster", 130, 95, 180, ETipo.AGUA),
    GYARADOS("Gyarados", 160, 125, 79, ETipo.AGUA),
    MAGIKARP("Magikarp", 70, 10, 40, ETipo.AGUA),
    LAPRAS("Lapras", 170, 85, 80, ETipo.AGUA),
    VAPOREON("Vaporeon", 160, 65, 60, ETipo.AGUA),
    PIKACHU("Pikachu",  100, 100, 50, ETipo.ELECTRICO),
    RAICHU("Raichu", 120, 90, 55, ETipo.ELECTRICO),
    ELECTRODE("Electrode", 100, 50, 70, ETipo.ELECTRICO),
    ZAPDOS("Zapdos", 160, 90, 85, ETipo.ELECTRICO),
    SNORUNT("Snorunt", 95, 45, 30, ETipo.HIELO),
    ARTICUNO("Articuno", 160, 85, 100, ETipo.HIELO),
    DRAGONITE("Dragonite", 150, 85, 70, ETipo.FUEGO);










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
