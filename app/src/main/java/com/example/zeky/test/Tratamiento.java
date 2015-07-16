package com.example.zeky.test;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Zeky on 27/3/15.
 */
public class Tratamiento{

    // Atributos de la clase tratamiento

    private String nombreDelTratamiento;
    private String fechaInicio;
    private String fechaFinal;
    private ArrayList<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();

    //constructor por parametros de los tratamientos
    public Tratamiento(String nombre, String inicio, String fin){

        this.nombreDelTratamiento = nombre;
        this.fechaInicio = inicio;
        this.fechaFinal = fin;
    }

    public Tratamiento(){

    }

    public void setNombreDelTratamiento(String nombre){
        this.nombreDelTratamiento = nombre;
    }

    public String getNombreDelTratamiento(){
        return this.nombreDelTratamiento;
    }


    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void añadirMedicamento(Medicamento medicine){
        this.listaMedicamentos.add(medicine);
    }
}
