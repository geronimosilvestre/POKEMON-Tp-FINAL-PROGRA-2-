import Gestoras.GestorEquipo;
import Model.Entrenador;
import Model.Pokemon;
import Model.Pokemones.Caterpie;


public class Main {
    public static void main(String[] args) {



        Entrenador entrenador = new Entrenador("Joel","Gardel");

        Pokemon caterpie = new Caterpie("Marcelo");
        Pokemon caterpie2 = new Caterpie("Emanuel");
        Pokemon caterpie3 = new Caterpie("Nico");



        GestorEquipo gestorEquipo = new GestorEquipo();

        gestorEquipo.crearEquipo(entrenador);

        gestorEquipo.agregarPokemon(entrenador,caterpie);
        gestorEquipo.agregarPokemon(entrenador,caterpie2);
        gestorEquipo.agregarPokemon(entrenador,caterpie3);

        gestorEquipo.mostrarEquipos();

    }
}