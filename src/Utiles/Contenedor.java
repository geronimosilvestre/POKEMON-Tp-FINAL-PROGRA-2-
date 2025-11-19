package Utiles;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Contenedor<T> {

    protected ArrayList<T> lista = new ArrayList<>();

    // agrega un objeto pasado por parametro, las exceptions las implementa cada clase
    public boolean agregarObjeto(T objeto) {
       return lista.add(objeto);
    }
    //elimina un objeto de l alista

    public boolean eliminarObjeto(T objeto) {
       return lista.remove(objeto);
    }
    //retorna un obj random de la lista
    public T getObjRandom(){

        if (lista.isEmpty()) {

            throw new IllegalStateException("Contenedor vacío, no se puede obtener un objeto aleatorio.");
        }
        return lista.get((int)(Math.random()*lista.size()));
    }

}

