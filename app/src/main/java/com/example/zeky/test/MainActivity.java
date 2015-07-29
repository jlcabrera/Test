package com.example.zeky.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int ACTIVITY_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.include);
        //modificamos el nombre del Toolbar
        toolbar.setTitle("Medicament Recorder");

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Tratamiento> listaTratamient = new ArrayList<>();
        File directorio = new File(Environment.getExternalStorageDirectory(), "json");
        if(!directorio.exists()){
            directorio.mkdir();
        }else{
            try {
                Gson gson = new Gson();
                BufferedReader br = new BufferedReader(new FileReader(new File(directorio,"/ejemplo.json")));

                Type listType = new TypeToken<ArrayList<Tratamiento>>(){}.getType();
                listaTratamient = gson.fromJson(br, listType);

                ListView lv = (ListView) findViewById(R.id.listaTratamientos);
                lv.setAdapter(new MyAdapter(this, listaTratamient));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void addTratamiento(View v){
        Intent i = new Intent(this, FormularioTratamiento.class);
        startActivityForResult(i, ACTIVITY_OK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
