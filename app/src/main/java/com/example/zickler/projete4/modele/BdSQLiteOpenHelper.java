package com.example.zickler.projete4.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class BdSQLiteOpenHelper extends SQLiteOpenHelper  {

    private String requete="create table praticien ( "
            + "CODEP INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "NOM TEXT NOT NULL,"
            + "ADRESSE TEXT NOT NULL,"
            + "CP TEXT NOT NULL,"
            + "VILLE TEXT NOT NULL);";

    private String requete2="create table typepraticien ( "
            + "CODET INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "LIBELLE TEXT NOT NULL,"
            + "LIEU TEXT NOT NULL);";

    private String requete3="create table rapportvisite ( "
            + "NUMR INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "DATE DATE NOT NULL, "
            + "BILAN TEXT NOT NULL, "
            + "MOTIF TEXT NOT NULL, "
            + "VIS TEXT NOT NULL, "
            + "INDCONF FLOAT NOT NULL, "
            + "CODEP INTEGER NOT NULL);"; // a essayer sinon prendre text


    public BdSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(requete);
        db.execSQL(requete2);
        db.execSQL(requete3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS rapportvisite");
        db.execSQL("DROP TABLE IF EXISTS typepraticien");
        db.execSQL("DROP TABLE IF EXISTS praticien");
        onCreate(db);
    }


}

