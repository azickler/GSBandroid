package com.example.zickler.projete4.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by amael on 23/05/2017.
 */

public class PraticienDAO {
    private static String base = "BDprojet";
    private static int version = 6;
    private BdSQLiteOpenHelper accesBD;

    public PraticienDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);

    }

    public void delPraticien(String codeP){
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        bd.delete("praticien","CODEP = "+codeP,null);
    }


    public ArrayList<Praticien> getVisitesBefore(String dateMax){
        Cursor curseur;
        String req = "select DISTINCT * from praticien WHERE CODEP NOT IN (select codep from rapportvisite WHERE DATE > '"+dateMax+"');";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToPratArrayList(curseur);
    }


    public ArrayList<Praticien> getPraticiens(){
        Cursor curseur;
        String req = "select * from praticien;";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToPratArrayList(curseur);
    }


    private ArrayList<Praticien> cursorToPratArrayList(Cursor curseur){
        ArrayList<Praticien> listePrats = new ArrayList<>();

        int CODEP;
        String NOM;
        String ADRESSE;
        String CP;
        String VILLE;
        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            CODEP = curseur.getInt(0);
            NOM = curseur.getString(1);
            ADRESSE = curseur.getString(2);
            CP = curseur.getString(3);
            VILLE = curseur.getString(4);
            listePrats.add(new Praticien(CODEP,NOM,ADRESSE,CP,VILLE));
            curseur.moveToNext();
        }

        return listePrats;
    }

    public long addPraticien(Praticien unPraticien){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("NOM", unPraticien.getNOM() );
        value.put("ADRESSE", unPraticien.getADRESSE() );
        value.put("CP", unPraticien.getCP() );
        value.put("VILLE",unPraticien.getVILLE() );
        ret = bd.insert("praticien", null, value);

        return ret;
    }


    public long modifPraticien(Praticien unPraticien,String codeP){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("NOM", unPraticien.getNOM() );
        value.put("ADRESSE", unPraticien.getADRESSE() );
        value.put("CP", unPraticien.getCP() );
        value.put("VILLE",unPraticien.getVILLE() );
        ret = bd.update("praticien", value, "CODEP = "+ codeP,null);

        return ret;
    }


}
