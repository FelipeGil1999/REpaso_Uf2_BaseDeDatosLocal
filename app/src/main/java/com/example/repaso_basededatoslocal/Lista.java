package com.example.repaso_basededatoslocal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class Lista extends AppCompatActivity {

    public RecyclerView viewLlista;

    private List<Coordinate> coordenadas;

    ManegadorDades db = new ManegadorDades(Lista.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        coordenadas = db.getAllCoordinates();
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(coordenadas);
        viewLlista = (RecyclerView) findViewById(R.id.lista_coordenadas);
        viewLlista.setAdapter(adapter);
    }
}