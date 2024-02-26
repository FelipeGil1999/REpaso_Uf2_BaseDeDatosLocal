package com.example.repaso_basededatoslocal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText latitud;
    EditText longitud;
    Button aplicar;
    Button ver;
    RadioGroup opcion;
    RadioButton añadir;
    RadioButton modificar;
    RadioButton eliminar;
    RadioButton consultar;

    ManegadorDades db = new ManegadorDades(MainActivity.this);

    private List<Coordinate> coordenadas = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordenadas = db.getAllCoordinates();
        db = new ManegadorDades(MainActivity.this);

        latitud = (EditText) findViewById(R.id.latitud);
        longitud = (EditText) findViewById(R.id.longitud);
        aplicar = (Button) findViewById(R.id.aplicar);
        ver = (Button) findViewById(R.id.ver);
        opcion = (RadioGroup) findViewById(R.id.opcion);
        añadir = (RadioButton) findViewById(R.id.añadir);
        modificar = (RadioButton) findViewById(R.id.modificar);
        eliminar = (RadioButton) findViewById(R.id.eliminar);
        consultar = (RadioButton) findViewById(R.id.consultar);

        aplicar.setOnClickListener(this);
        ver.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int opcionID = opcion.getCheckedRadioButtonId();

        if (v.getId() == R.id.aplicar) {
            if(opcionID == añadir.getId()){

                boolean existe = false;
                boolean vacio = false;

                if (latitud.getText().toString().isEmpty()) {
                    vacio = true;
                }

                for (Coordinate c : coordenadas) {
                    if (c.getLatitude() != null && c.getLatitude().equals(latitud.getText().toString()))
                        {
                        existe = true;
                    }
                }

                if (!existe) {
                    if(!vacio) {
                        Coordinate c = new Coordinate();
                        c.getLatitude();
                        c.getLongitude();
                        db.addCoordenada(c);
                        coordenadas.add(c);
                    }else{
                        Toast.makeText(this, "No se puede añadir un coche sin matricula", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Ya existe un coche con esa matricula", Toast.LENGTH_SHORT).show();
                }
                //viewLlista.getAdapter().notifyItemInserted(coches.size()-1);
            }
            if(opcionID == R.id.eliminar) {

                boolean existe = false;

                boolean vacio = false;

                for (Coordinate c : coordenadas) {
                    if (c.getLatitude().equals(latitud.getText().toString())) {
                        existe = true;
                    }
                }

                if (existe) {
                    Coordinate c = new Coordinate();
                    c.setLatitude(latitud.getText().toString());
                    c.setLongitude(longitud.getText().toString());
                    db.deleteCoordenada(c);
                    coordenadas.remove(c);

                    latitud.setText("");
                    longitud.setText("");
                    //viewLlista.getAdapter().notifyItemRemoved(coches.indexOf(c));
                }
            }

            if(opcionID == R.id.modificar) {

                boolean existe = false;

                for (Coordinate c : coordenadas) {
                    if (c.getLatitude().equals(latitud.getText().toString())) {
                        existe = true;
                    }
                }

                if (existe) {
                    Coordinate c = new Coordinate();
                    c.setLatitude(latitud.getText().toString());
                    c.setLongitude(longitud.getText().toString());
                    db.updateCoordenada(c);
                    coordenadas.add(c);
                }else{
                    Toast.makeText(this, "No existe un coche con esa matricula", Toast.LENGTH_SHORT).show();
                }
                //viewLlista.getAdapter().notifyItemChanged(coches.indexOf(c));
            }
            if(opcionID == R.id.consultar){
                latitud.setText("");
                longitud.setText("");

                boolean existe = false;

                for (Coordinate c : coordenadas) {
                    if (c.getLatitude().equals(latitud.getText().toString())) {
                        existe = true;
                    }
                }

                if (existe) {
                    Coordinate c = new Coordinate();
                    c.setLatitude(latitud.getText().toString());

                    Coordinate c2 = db.vCoordinate(c.getLatitude());

                    latitud.setText(c2.getLatitude());
                    longitud.setText(c2.getLongitude());
                }else{
                    Toast.makeText(this, "No existe un coche con esa matricula", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (v.getId() == R.id.ver) {
            Intent intent = new Intent(this, Lista.class);
            startActivity(intent);
        }

    }
}