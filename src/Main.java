import Gestoras.GestorEquipo;
import Model.Entrenador;
import Model.Pokemones.Caterpie;


public class Main {
    public static void main(String[] args) {


        Entrenador entrenador = new Entrenador("Carlos","Gardel");

        Caterpie caterpie = new Caterpie("Marcelo");
        Caterpie caterpie2 = new Caterpie("Emanuel");
        Caterpie caterpie3 = new Caterpie("Nico");



        GestorEquipo equipoRoket = new GestorEquipo();

        equipoRoket.agregarEntrenador(entrenador);

        equipoRoket.agregarPokemon(entrenador,caterpie);
        equipoRoket.agregarPokemon(entrenador,caterpie2);
        equipoRoket.agregarPokemon(entrenador,caterpie3);

        equipoRoket.mostrarEquipos();

    }
}