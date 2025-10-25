import Gestoras.GestorEquipo;
import Model.Entrenador;
import Model.Pokemones.Caterpie;


public class Main {
    public static void main(String[] args) {


        Entrenador entrenador = new Entrenador("Carlos","Gardel");

        Caterpie caterpie = new Caterpie(10,10,10,10,"Marcelo");
        Caterpie caterpie2 = new Caterpie(10,10,10,10,"Emanuel");
        Caterpie caterpie3 = new Caterpie(10,10,10,10,"Nico");



        GestorEquipo equipoRoket = new GestorEquipo();

        equipoRoket.agregarEntrenador(entrenador);

        equipoRoket.agregarPokemon(entrenador,caterpie);
        equipoRoket.agregarPokemon(entrenador,caterpie2);
        equipoRoket.agregarPokemon(entrenador,caterpie3);

        equipoRoket.mostrarEquipos();

    }
}