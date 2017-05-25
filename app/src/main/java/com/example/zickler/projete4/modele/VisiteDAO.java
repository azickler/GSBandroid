package com.example.zickler.projete4.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by amael on 24/05/2017.
 */

public class VisiteDAO {
    private static String base = "BDprojet";
    private static int version = 6;
    private BdSQLiteOpenHelper accesBD;

    public VisiteDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);

    }

    public long addVisite(Visite uneVisite){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("DATE", uneVisite.getDATE() );
        value.put("BILAN", uneVisite.getBILAN() );
        value.put("MOTIF", uneVisite.getMOTIF() );
        value.put("VIS",uneVisite.getVIS() );
        value.put("INDCONF",uneVisite.getINDCONF() );
        value.put("CODEP",uneVisite.getCODEP() );
        ret = bd.insert("rapportvisite", null, value);

        return ret;
    }


    public void delVisite(String numR){
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        bd.delete("rapportvisite","NUMR = "+numR,null);
    }


    public ArrayList<Visite> getVisites(String codeP){
        Cursor curseur;
        String req = "select * from rapportvisite WHERE CODEP = "+codeP+";";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToVisArrayList(curseur);
    }


    public ArrayList<Visite> getVisites(String dateDeb, String dateFin){
        Cursor curseur;
        String req = "select * from rapportvisite WHERE DATE BETWEEN '"+dateDeb+"' AND '"+dateFin+"' ORDER BY DATE;";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToVisArrayList(curseur);
    }




    public Visite getVisite(int CODEP){
        Visite laVisite = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from rapportvisite where CODEP = "+CODEP+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            //leArticle = new Article(id,curseur.getString(1), curseur.getInt(2));
            laVisite = new Visite(curseur.getString(1),curseur.getString(2),curseur.getString(3),curseur.getString(4), curseur.getInt(5),curseur.getInt(6));

        }
        return laVisite;
    }


    private ArrayList<Visite> cursorToVisArrayList(Cursor curseur){
        ArrayList<Visite> listeVisites = new ArrayList<>();

        int CODEP, INDCONF,NUMR;
        String DATE;
        String BILAN;
        String MOTIF;
        String VISITEUR;
        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            NUMR = curseur.getInt(0);
            DATE = curseur.getString(1);
            BILAN = curseur.getString(2);
            MOTIF = curseur.getString(3);
            VISITEUR = curseur.getString(4);
            INDCONF = curseur.getInt(5);
            CODEP = curseur.getInt(6);
            listeVisites.add(new Visite(NUMR,DATE,BILAN,MOTIF,VISITEUR,INDCONF,CODEP));
            curseur.moveToNext();
        }

        return listeVisites;
    }

    public long modifVisite(Visite uneVisite,String numR){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("DATE", uneVisite.getDATE() );
        value.put("BILAN", uneVisite.getBILAN() );
        value.put("MOTIF", uneVisite.getMOTIF() );
        value.put("VIS",uneVisite.getVIS() );
        value.put("INDCONF",uneVisite.getINDCONF() );
        value.put("CODEP",uneVisite.getCODEP() );
        ret = bd.update("rapportvisite", value, "NUMR = "+ numR,null);

        return ret;
    }

}
