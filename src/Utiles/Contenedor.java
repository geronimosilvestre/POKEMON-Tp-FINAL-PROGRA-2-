package Utiles;

import java.util.ArrayList;

public class Contenedor <T>{

    protected ArrayList<T> lista=new ArrayList<>();

    public void agregar(T objeto) throws IllegalArgumentException{

        if(lista.contains(objeto)){
            throw new IllegalArgumentException("El objeto ya existe");
        }
        lista.add(objeto);
    }

    public void eliminar(T objeto) throws IllegalArgumentException{
        if(!lista.contains(objeto)){

            throw new IllegalArgumentException("El objeto no existe");
        }
        lista.remove(objeto);
    }
    public ArrayList<T> obtenerTodos(){
        return new ArrayList<>(lista);
    }

}
