package com.example.zickler.projete4.controleur;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.zickler.projete4.R;
import com.example.zickler.projete4.modele.Praticien;
import com.example.zickler.projete4.modele.PraticienDAO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zickler on 27/03/2017.
 */

public class ListPraticiens extends Activity {
    private ListView listViewPraticiens;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_prat);
        Button buttonQuitter = (Button) findViewById(R.id.buttonQuitter);
        Button buttonAjout = (Button) findViewById(R.id.buttonAjouter);
        listViewPraticiens = (ListView) findViewById(R.id.ListViewPraticiens);
        // récupération des données
        final PraticienDAO accesDao = new PraticienDAO(this);
        ArrayList<Praticien> listePratRecherche;
        listePratRecherche = accesDao.getPraticiens();
        //Création de la ArrayList qui nous permettra de remplir la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;
        Toast.makeText(getApplicationContext(), "il y a " + listePratRecherche.size() + " articles dans la BD", Toast.LENGTH_LONG).show();
        //essai foreach
        for (Praticien unPrat : listePratRecherche
                ) {
            map = new HashMap<String, String>();
            Log.d("log",unPrat.toString());
            //on insère un élément _id que l'on récupérera dans le textView REF créé dans le fichier liste_entree.xml

            map.put("CODEP", unPrat.getCODEP());
            map.put("NOM", unPrat.getNOM());
            map.put("ADRESSE", unPrat.getADRESSE());
            map.put("CP", unPrat.getCP());
            map.put("VILLE", String.valueOf(unPrat.getVILLE()));
            listItem.add(map);
        }

        //Création d'un SimpleAdapter qui se chargera de mettre les items présents dans notre list (list_entree) dans la vue listeArticles
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.v_listprat, new String[] {"CODEP", "NOM", "ADRESSE", "CP","VILLE"}, new int[] {R.id.codep, R.id.nom, R.id.adresse, R.id.cp, R.id.ville });
        listViewPraticiens.setAdapter(mSchedule);

        listViewPraticiens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                final HashMap<String, String> map = (HashMap<String, String>) listViewPraticiens.getItemAtPosition(position);
                //on créé une boite de dialogue
                final AlertDialog.Builder adb = new AlertDialog.Builder(ListPraticiens.this);
                //on attribue un titre à notre boite de dialogue
                adb.setTitle("Sélection article");
                //on insère un message à notre boite de dialogue, et ici on affiche la designation de l'item cliqué
                adb.setMessage("Votre choix : ");
                //on indique que l'on veut le bouton ok à notre boite de dialogue
                adb.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(ListPraticiens.this, ModifPraticien.class);
                        intent.putExtra("CODEP",map.get("CODEP").toString());
                        startActivity(intent);
                    }});
                adb.setNegativeButton("Prestations", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(ListPraticiens.this, ListeVisitesPrat.class);
                        intent.putExtra("CODEP",map.get("CODEP").toString());
                        startActivity(intent);
                    }});
                adb.setNeutralButton("Supprimer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        accesDao.delPraticien(map.get("CODEP").toString());
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }});
                //on affiche la boite de dialogue
               adb.show();
            }
        });

       buttonQuitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();  //fermeture de la fenêtre
            }
        });
        buttonAjout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "ouverture fenêtre Ajout !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ListPraticiens.this, AjoutPraticien.class);
                startActivity(intent);
            }
        });

    }
}
