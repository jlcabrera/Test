package com.example.zeky.test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.google.gson.Gson;

import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormularioTratamiento extends ActionBarActivity {

    final Context contexto = this;
    private Tratamiento t = new Tratamiento();
    private List<Tratamiento> listaTratamientos = new ArrayList<Tratamiento>();
    private File directorio = new File(Environment.getExternalStorageDirectory(), "json");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_tratamiento);
        if(!directorio.exists()){
            directorio.mkdir();
        }else{
            Gson gson = new Gson();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(directorio + "/ejemplo.json"));
                listaTratamientos = gson.fromJson(br, ArrayList.class);
            } catch (FileNotFoundException e) {
                Log.e("Error al leer el json","Han ocurrido errores mientras se creaba la lista de tratamientos");
            }finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario_tratamiento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fechaInicio(View view){
        FragmentManager fm = getSupportFragmentManager();
        DateTime now = DateTime.now();
        CalendarDatePickerDialog calendarDatePickerDialog = CalendarDatePickerDialog
                .newInstance(new CalendarDatePickerDialog.OnDateSetListener() {
                                 @Override
                                 public void onDateSet(CalendarDatePickerDialog calendarDatePickerDialog, int year, int mes, int dia) {
                                     TextView tv = (TextView) findViewById(R.id.fechaInicio);
                                     String fecha = dia + "/" + mes + "/" + year;
                                     tv.setText(fecha);
                                     t.setFechaInicio(fecha);
                                 }
                             }, now.getYear(), now.getMonthOfYear() - 1,
                        now.getDayOfMonth());
        calendarDatePickerDialog.show(fm, "FechaInicio");
    }

    public void fechaFinal(View view){
        FragmentManager fm = getSupportFragmentManager();
        DateTime now = DateTime.now();
        CalendarDatePickerDialog calendarDatePickerDialog = CalendarDatePickerDialog
                .newInstance(new CalendarDatePickerDialog.OnDateSetListener() {
                                 @Override
                                 public void onDateSet(CalendarDatePickerDialog calendarDatePickerDialog, int year, int mes, int dia) {
                                     TextView tv = (TextView) findViewById(R.id.fechaFinal);
                                     String fecha = dia + "/" + mes + "/" + year;
                                     tv.setText(fecha);
                                     t.setFechaFinal(fecha);
                                 }
                             }, now.getYear(), now.getMonthOfYear() - 1,
                        now.getDayOfMonth());
        calendarDatePickerDialog.show(fm, "FechaFin");
    }

    public void addMedicament(View view){

        LayoutInflater layoutInflater = LayoutInflater.from(this);

        final View formularioMedicament = layoutInflater.inflate(R.layout.activity_formulario_medicamento, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(formularioMedicament);

        final EditText nombreMedicament = (EditText) formularioMedicament.findViewById(R.id.nombreMedicamento);
        final EditText cantidad = (EditText) formularioMedicament.findViewById(R.id.cantidad);
        final EditText vecesAlDia = (EditText) formularioMedicament.findViewById(R.id.vecesAlDia);

        final Medicamento m = new Medicamento();

        builder.setCancelable(false)
                .setPositiveButton("Aceptar", null)
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setTitle("Añadir Medicamento");

        final AlertDialog FormularioMedicamento = builder.create();
        FormularioMedicamento.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button b = FormularioMedicamento.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(comprobarDatos(nombreMedicament, cantidad, vecesAlDia)) {
                            m.setnMedicamento(nombreMedicament.getText().toString());
                            m.setMiligramos(Integer.valueOf(cantidad.getText().toString()));
                            m.setTomas(Integer.valueOf(vecesAlDia.getText().toString()));

                            Toast.makeText(contexto, "Se ha creado un medicamento", Toast.LENGTH_LONG).show();

                            mostrarEnPantalla(m);
                            t.añadirMedicamento(m);
                            FormularioMedicamento.dismiss();
                        }
                    }
                });
            }
        });
        FormularioMedicamento.show();
    }

    public boolean comprobarDatos(EditText nombreMedicament, EditText cantidad, EditText vecesAlDia){
        boolean validos = true;
        if(vecesAlDia.getText().toString().length() == 0) {
            vecesAlDia.setError("El dato es requerido");
            vecesAlDia.requestFocus();
            validos = false;
        }

        if(cantidad.getText().toString().length() == 0){
            cantidad.setError("El dato es requerido");
            cantidad.requestFocus();
            validos = false;
        }

        if(nombreMedicament.getText().toString().length() == 0){
            nombreMedicament.setError("El dato es requerido");
            nombreMedicament.requestFocus();
            validos = false;
        }

        return validos;
    }

    public void mostrarEnPantalla(Medicamento m){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.listaMedicamentos);

        TextView textView = new TextView(this);
        textView.setText(m.getnMedicamento());
        textView.setTextColor(getResources().getColor(android.R.color.black));
        textView.setId((int) (Math.random() * 10));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(15,0,0,0);

        textView.setLayoutParams(params);

        linearLayout.addView(textView);
    }

    public void guardarTratamiento(View view){
        EditText et = (EditText) findViewById(R.id.nombreTratamiento);
        t.setNombreDelTratamiento(et.getText().toString());
        listaTratamientos.add(t);
        Gson gson = new Gson();
        String json = gson.toJson(listaTratamientos);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File(directorio,"ejemplo.json")));
            bw.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        finish();
    }
}