package Gestoras;

import Model.Entrenador;
import Model.Pokemon;
import java.util.HashMap;
import java.util.HashSet;

public class GestorEquipo {
    private HashMap<Entrenador, HashSet<Pokemon>> equipos;

    public GestorEquipo() {
        this.equipos = new HashMap<>();
    }

    public void agregarEntrenador(Entrenador entrenador) {
        equipos.putIfAbsent(entrenador, new HashSet<>());
    }

    public void agregarPokemon(Entrenador entrenador, Pokemon pokemon) {
        equipos.computeIfAbsent(entrenador, e -> new HashSet<>()).add(pokemon);
    }

    public HashSet<Pokemon> obtenerEquipo(Entrenador entrenador) {
        return  equipos.get(entrenador);
    }

    public void mostrarEquipos() {
        for (var entry : equipos.entrySet()) {
            System.out.println("Entrenador: " + entry.getKey().getNombre());
            System.out.println("Equipo:");
            entry.getValue().forEach(p -> System.out.println(" - " + p));
            System.out.println();
        }


        //hola
    }
}

