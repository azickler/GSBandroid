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
 * Created by amael on 24/05/2017.
 */

public class ListeVisitesPrat extends Activity {
    private ListView ListViewVisites;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_visprat);
        Button buttonQuitter = (Button) findViewById(R.id.buttonQuitter);
        Button buttonAjout = (Button) findViewById(R.id.buttonAjout);
        ListViewVisites = (ListView) findViewById(R.id.ListViewVisites);
        // récupération des données
        final Bundle extras = getIntent().getExtras();
        final VisiteDAO accesDao = new VisiteDAO(this);
        ArrayList<Visite> listeVisiteRecherche;
        listeVisiteRecherche = accesDao.getVisites(extras.getString("CODEP"));
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
        ListViewVisites.setAdapter(mSchedule);

        ListViewVisites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                final HashMap<String, String> map = (HashMap<String, String>) ListViewVisites.getItemAtPosition(position);
                //on créé une boite de dialogue
                final AlertDialog.Builder adb = new AlertDialog.Builder(ListeVisitesPrat.this);
                //on attribue un titre à notre boite de dialogue
                adb.setTitle("Sélection article");
                //on insère un message à notre boite de dialogue, et ici on affiche la designation de l'item cliqué
                adb.setMessage("Votre choix : ");
                //on indique que l'on veut le bouton ok à notre boite de dialogue
                adb.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(ListeVisitesPrat.this, ModifVisite.class);
                        intent.putExtra("NUMR",map.get("NUMR").toString());
                        intent.putExtra("CODEP",extras.getString("CODEP"));
                        startActivity(intent);
                    }});
                adb.setNeutralButton("Supprimer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        accesDao.delVisite(map.get("NUMR").toString());
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
                Intent intent = new Intent(ListeVisitesPrat.this, AjoutVisite.class);
                intent.putExtra("CODEP",extras.get("CODEP").toString());
                startActivity(intent);
            }
        });

    }
}
