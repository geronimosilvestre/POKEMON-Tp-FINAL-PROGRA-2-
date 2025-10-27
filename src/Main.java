import Gestoras.GestorEquipo;
import Model.Entrenador;
import Model.Pokemon;
import Model.Pokemones.Caterpie;

import java.util.LinkedHashSet;


public class Main {
    public static void main(String[] args) {




        Pokemon caterpie = new Caterpie("Marcelo");
        Pokemon caterpie2 = new Caterpie("Emanuel");
        Pokemon caterpie3 = new Caterpie("Nico");



        Entrenador entrenador = new Entrenador("Joel","Gardel");
        GestorEquipo gestorEquipo = new GestorEquipo();
        LinkedHashSet<Pokemon> mochilaDelEntrenador = gestorEquipo.crearMochilaEntrenador();






       gestorEquipo.crearEquipo(entrenador)


    }
}