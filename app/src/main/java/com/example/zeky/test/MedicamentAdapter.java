package com.example.zeky.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MedicamentAdapter extends ArrayAdapter {

    private final Context context;
    private final ArrayList<Medicamento> lista;


    // Constructor de Adaptador
    public MedicamentAdapter(Context c, ArrayList<Medicamento> lista){
        super(c, R.layout.fila_tratamiento, lista);
        this.context = c;
        this.lista = lista;
    }

    // Clase privada para el viewHolder(adapter mas óptimo)
    static class ViewHolder{
        TextView medicamento;
        TextView tomasTotales;
        TextView tomasRestantes;
        TextView siguienteToma;
    }

    // Metodo donde sacaremos la información necesaria para crear la lista
    public View getView(int position, View convertView, ViewGroup parent){

        Medicamento m = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.fila_tratamiento, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.medicamento = (TextView) convertView.findViewById(R.id.nTratamiento);
            viewHolder.tomasTotales = (TextView) convertView.findViewById(R.id.tvFechaInicio);
            viewHolder.tomasRestantes = (TextView) convertView.findViewById(R.id.tvFechaFinal);
            viewHolder.siguienteToma = (TextView) convertView.findViewById(R.id.tvRestantes);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.medicamento.setText(m.getnMedicamento());
        viewHolder.tomasTotales.setText("Total tomas: " + m.getTotalTomas());
        viewHolder.tomasRestantes.setText("Tomas restantes: " + m.getTomas());

        Date fecha = new Date(m.getSiguienteHora());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        viewHolder.siguienteToma.setText(String.valueOf("Siguiente Toma: " + format.format(fecha)));

        return convertView;
    }

    public int getCount(){
        return this.lista.size();
    }

    public Medicamento getItem(int arg0){
       return lista.get(arg0);
   }

    public long getItemId(int position){
       return position;
   }

    //public int calcularDiasRestantes(Date fechaFinal){
    //    Date hoy = new Date();
    //    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    //    String date = format.format(hoy);
    //    Date today = null;
    //    try {
    //         today = format.parse(date);
    //    } catch (ParseException e) {
    //        e.printStackTrace();
    //    }
    //    return Days.daysBetween(new DateTime(today), new DateTime(fechaFinal)).getDays();
    //}
}