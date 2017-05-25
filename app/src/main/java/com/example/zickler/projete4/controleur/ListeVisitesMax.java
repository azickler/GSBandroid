package com.example.zickler.projete4.controleur;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.zickler.projete4.R;
import com.example.zickler.projete4.modele.Praticien;
import com.example.zickler.projete4.modele.PraticienDAO;
import com.example.zickler.projete4.modele.Visite;
import com.example.zickler.projete4.modele.VisiteDAO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by amael on 25/05/2017.
 */

public class ListeVisitesMax extends Activity {

    private ListView listViewPraticiens;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_pratavisiter);
        Button buttonQuitter = (Button) findViewById(R.id.buttonQuitter);
        Button buttonAjout = (Button) findViewById(R.id.buttonAjouter);
        listViewPraticiens = (ListView) findViewById(R.id.ListViewPraticiens);
        // récupération des données
        final Bundle extras = getIntent().getExtras();
        final PraticienDAO accesDao = new PraticienDAO(this);
        ArrayList<Praticien> listePratRecherche;
        listePratRecherche = accesDao.getVisitesBefore(extras.getString("dateMax"));
        //Création de la ArrayList qui nous permettra de remplir la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;
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


        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();  //fermeture de la fenêtre
            }
        });
    }
}
