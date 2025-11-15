package Utiles;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Contenedor<T> {

    protected ArrayList<T> lista = new ArrayList<>();

    // agrega un objeto pasado por parametro, las exceptions las implementa cada clase
    public boolean agregarObjeto(T objeto) {
       return lista.add(objeto);
    }

    public boolean eliminarObjeto(T objeto) {
       return lista.remove(objeto);
    }
    public ArrayList<T> getLista() {
        return lista;
    }

    public LinkedHashSet<T> obtenerTodosObjetos() {
        return new LinkedHashSet<>(lista);
    }
}

