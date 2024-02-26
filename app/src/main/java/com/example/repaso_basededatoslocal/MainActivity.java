package com.example.repaso_basededatoslocal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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


    }
}