package com.example.zeky.test;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Zeky on 27/3/15.
 */
public class Tratamiento{

    // Atributos de la clase tratamiento

    private String nombreDelTratamiento;
    private Date fechaInicio;
    private Date fechaFinal;
    private ArrayList<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();

    //constructor por parametros de los tratamientos
    public Tratamiento(String nombre, Date inicio, Date fin){

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


    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {

        this.fechaInicio = fechaInicio;
    }

    public ArrayList<Medicamento> getListaMedicamentos(){
        return this.listaMedicamentos;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void añadirMedicamento(Medicamento medicine){
        this.listaMedicamentos.add(medicine);
    }
}
