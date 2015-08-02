package com.example.zeky.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
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
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();

        }
        viewHolder.tratamientos.setText(t.getNombreDelTratamiento());


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
}
