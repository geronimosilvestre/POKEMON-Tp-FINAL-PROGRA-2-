package Gestoras;

import Exceptions.falloAgregarEntrenadorException;
import Model.Entrenador;
import Model.Pokemon;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class GestorEquipo {
    private HashMap<Entrenador, LinkedHashSet<Pokemon>> equipos;

    public GestorEquipo() {
        this.equipos = new HashMap<>();
    }

    public void crearEquipo(Entrenador entrenador) throws falloAgregarEntrenadorException {
        equipos.putIfAbsent(entrenador, new LinkedHashSet<>());
    }

    public boolean agregarPokemon(Entrenador entrenador, Pokemon pokemon) {
        equipos.computeIfAbsent(entrenador, e -> new LinkedHashSet<>()).add(pokemon);
        return true;
    }// aca va lo de captura de pokemon

    public LinkedHashSet<Pokemon> obtenerEquipo(Entrenador entrenador) {
        return  equipos.get(entrenador);
    }

    public void mostrarEquipos() {
        for (var entry : equipos.entrySet()) {
            System.out.println("Entrenador: " + entry.getKey().getNombre());
            System.out.println("Equipo:");
            entry.getValue().forEach(p -> System.out.println(" - " + p));
            System.out.println();
        }
    }
}

