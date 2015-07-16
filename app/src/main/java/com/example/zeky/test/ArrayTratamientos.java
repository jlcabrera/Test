package com.example.zeky.test;

/**
 * Created by Zeky on 19/3/15.
 */
import java.util.ArrayList;


public class ArrayTratamientos{

    private ArrayList<Tratamiento> arrayTratamientos;

    public ArrayTratamientos(){
        arrayTratamientos = new ArrayList<Tratamiento>();


    }

    //metodo para añadir un nuevo tratamiento a la lista
    public void añadirTratamiento(Tratamiento t){
        this.arrayTratamientos.add(t);
    }

    //metodo para devolver el array list
    public ArrayList<Tratamiento> getArrayTratamientos(){
        return this.arrayTratamientos;
    }
}
