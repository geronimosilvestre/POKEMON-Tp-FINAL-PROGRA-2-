package Utiles;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Contenedor<T> {

    protected ArrayList<T> lista = new ArrayList<>();

    // NO LANZA EXCEPCIONES -> las lanza cada subclase según sus reglas
    public boolean agregarObjeto(T objeto) {
       return lista.add(objeto);
    }

    public boolean eliminarObjeto(T objeto) {
       return lista.remove(objeto);
    }

    public LinkedHashSet<T> obtenerTodosObjetos() {
        return new LinkedHashSet<>(lista);
    }
}

