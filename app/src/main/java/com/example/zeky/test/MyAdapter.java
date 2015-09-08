package com.example.zeky.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MyAdapter extends ArrayAdapter {

    private final Context context;
    private final ArrayList<Tratamiento> lista;


    // Constructor de Adaptador
    public MyAdapter(Context c, ArrayList<Tratamiento> lista){
        super(c, R.layout.fila_tratamiento, lista);
        this.context = c;
        this.lista = lista;
    }

    // Clase privada para el viewHolder(adapter mas óptimo)
    static class ViewHolder{
        TextView tratamientos;
        TextView fechaInicio;
        TextView fechaFinal;
        TextView diasRestantes;
    }

    // Metodo donde sacaremos la información necesaria para crear la lista
    public View getView(int position, View convertView, ViewGroup parent){

        Tratamiento t = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.fila_tratamiento, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tratamientos = (TextView) convertView.findViewById(R.id.nTratamiento);
            viewHolder.fechaInicio = (TextView) convertView.findViewById(R.id.tvFechaInicio);
            viewHolder.fechaFinal = (TextView) convertView.findViewById(R.id.tvFechaFinal);
            viewHolder.diasRestantes = (TextView) convertView.findViewById(R.id.tvRestantes);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        viewHolder.tratamientos.setText(t.getNombreDelTratamiento());
        viewHolder.fechaInicio.setText("Fecha de inicio: " + format.format(t.getFechaInicio()));
        viewHolder.fechaFinal.setText("Fecha de fin: " + format.format(t.getFechaFinal()));
        viewHolder.diasRestantes.setText(String.valueOf("Dias restantes: " + calcularDiasRestantes(t.getFechaFinal())));

        return convertView;
    }

    public int getCount(){
        return this.lista.size();
    }

    public Tratamiento getItem(int arg0){
       return lista.get(arg0);
   }

    public long getItemId(int position){
       return position;
   }

    public int calcularDiasRestantes(Date fechaFinal){
        Date hoy = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String date = format.format(hoy);
        Date today = null;
        try {
             today = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Days.daysBetween(new DateTime(today), new DateTime(fechaFinal)).getDays();
    }
}