package com.example.zeky.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class VistaMedicamento extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_medicamento);

        Bundle datos = getIntent().getExtras();

        TextView tratamiento = (TextView) findViewById(R.id.tvNombreTratamiento);
        tratamiento.setText("Medicamentos del tratamiento " + datos.getString("nombreTratamiento"));
        ArrayList<Medicamento> medicamentList = datos.getParcelableArrayList("lista");

        ListView listaMedicamentos = (ListView)findViewById(R.id.lvMedicamentos);
        listaMedicamentos.setAdapter(new MedicamentAdapter(this, medicamentList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vista_tratamiento, menu);
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
}
