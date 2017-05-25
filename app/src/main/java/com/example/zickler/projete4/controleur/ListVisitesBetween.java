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
import com.example.zickler.projete4.modele.Visite;
import com.example.zickler.projete4.modele.VisiteDAO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zickler on 27/03/2017.
 */

public class ListVisitesBetween extends Activity {
    private ListView listViewVisites;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_visitebetween);
        Button buttonQuitter = (Button) findViewById(R.id.buttonQuitter);
        listViewVisites = (ListView) findViewById(R.id.ListViewVisites);

        Bundle extras = getIntent().getExtras();
        // récupération des données
        final VisiteDAO accesDao = new VisiteDAO(this);
        ArrayList<Visite> listeVisiteRecherche;
        listeVisiteRecherche = accesDao.getVisites(extras.getString("dateDebut"),extras.getString("dateFin"));
        //Création de la ArrayList qui nous permettra de remplir la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;
        //essai foreach
        for (Visite uneVisite : listeVisiteRecherche
                ) {
            map = new HashMap<String, String>();
            Log.d("log",uneVisite.toString());
            //on insère un élément _id que l'on récupérera dans le textView REF créé dans le fichier liste_entree.xml

            map.put("NUMR", String.valueOf(uneVisite.getNUMR()));
            map.put("DATE", uneVisite.getDATE());
            map.put("BILAN", uneVisite.getBILAN());
            map.put("MOTIF", uneVisite.getMOTIF());
            map.put("VIS", uneVisite.getVIS());
            map.put("INDCONF", String.valueOf(uneVisite.getINDCONF()));
            listItem.add(map);
        }

        //Création d'un SimpleAdapter qui se chargera de mettre les items présents dans notre list (list_entree) dans la vue listeArticles
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.v_listvisitesprat, new String[] {"NUMR", "DATE", "BILAN", "MOTIF","VIS","INDCONF","CODEP"}, new int[] {R.id.num, R.id.date, R.id.bilan, R.id.motif, R.id.vis,R.id.indConf });
        listViewVisites.setAdapter(mSchedule);

        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();  //fermeture de la fenêtre
            }
        });
    }
}
