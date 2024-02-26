package com.example.repaso_basededatoslocal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class ManegadorDades extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    //NOM DE LA BASE DE DADES
    private static final String DATABASE_NAME = "coordenadas";
    // NOM DE LA TAULA
    private static final String TABLE_COORDENADAS = "registro";
    //CAMPS DE LA BASE DE DADES
    private static final String KEY_LATITUD = "latitud";
    private static final String KEY_LONGITUD = "longitud";


    public ManegadorDades(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table = "CREATE TABLE " + TABLE_COORDENADAS + "(" + KEY_LATITUD + " TEXT PRIMARY KEY, " + KEY_LONGITUD + " TEXT " + ")";

        System.out.println(Create_Table);

        db.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //DROP SI EXISTEIX
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_COORDENADAS);
        // CREAR LA TABLA DE NOU
        onCreate(db);

    }

    //AFEGIS UN VIDEOJOC NOU
    public void addCoordenada(Coordinate coordenada) {
        //
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valors = new ContentValues();
        valors.put(KEY_LATITUD, coordenada.getLatitude());
        valors.put(KEY_LONGITUD, coordenada.getLongitude());



        //Instertar a un registre
        db.insert(TABLE_COORDENADAS,null,valors);
        // tancar  conexio base de dades
        //db.close();
    }

    //Query que retorna tots els  registres de la taula
    public List<Coordinate> getAllCoordinates(){
        List<Coordinate> vg = new ArrayList<Coordinate>();
        // QUERY DE TOTS ELS REGISTRES DE LA TAULA
        String selectTots ="SELECT * FROM "+ TABLE_COORDENADAS;
        //Executem la query  //CUANDO SOLO RETORNA UN REGISTRO ES getReadableDatabase si son mas de uno seria getWritableDatabase()
        SQLiteDatabase db = this.getWritableDatabase();
        // guarda los resultados en la variable "cursor"
        Cursor cursor = db.rawQuery(selectTots, null);
        //desplaçament pel cursor

        if (cursor.moveToFirst()) {
            do{
                //creamos el objeto "game" de la clase VideoGame
                Coordinate coordinate = new Coordinate();
                //creamos los atributos de la clase game
                coordinate.setLatitude(cursor.getString(0));
                coordinate.setLongitude(cursor.getString(1));


                //añadimos ala LISTA el objeto "game"
                vg.add(coordinate);

            } while (cursor.moveToNext());
        }

        return vg;

    }

    //Query que retorna un sol registre de la taula
    public Coordinate vCoordinate(String matricula){
        SQLiteDatabase db =this.getReadableDatabase();
        //Creem un cursor per desplaçarse pels registres per localitzar l'element en concrete
        Cursor cursor = db.query(TABLE_COORDENADAS, new String[]{ KEY_LATITUD, KEY_LONGITUD},
                KEY_LATITUD+ "= ?",new String[]{String.valueOf(matricula)}, null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        Coordinate c = new Coordinate(cursor.getString(0), cursor.getString(1));

        return c;
    }

    //Query per actualitzar un registre de la Taula
    public int updateCoordenada(Coordinate c){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues valors = new ContentValues();
        valors.put(KEY_LATITUD, c.getLatitude());
        valors.put(KEY_LONGITUD, c.getLongitude());


        //Actualitzem a la Base de dades
        return db.update(TABLE_COORDENADAS, valors, KEY_LATITUD+"=?",
                new String[]{String.valueOf(c.getLatitude())});

    }

    //Query per esborrar un registre de la Taula
    public void deleteCoordenada(Coordinate c){
        SQLiteDatabase db =this.getWritableDatabase();
        db.delete(TABLE_COORDENADAS, KEY_LATITUD+ "= ?",
                new String[]{String.valueOf(c.getLatitude())});
        //db.close();
    }
}


