package com.example.zickler.projete4.controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zickler.projete4.R;
import com.example.zickler.projete4.modele.Visite;
import com.example.zickler.projete4.modele.VisiteDAO;

/**
 * Created by amael on 24/05/2017.
 */

public class AjoutVisite extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_ajoutvisite);
        Button buttonQuitter = (Button) findViewById(R.id.quitter);
        // R.id.ButtonQuitterAjout fait référence à l’id du layout ajout_liste.xml

        final VisiteDAO accesDAO = new VisiteDAO(this);
        Button buttonAjout = (Button) findViewById(R.id.button);
        final Bundle extras = getIntent().getExtras();
        // R.id.ButtonQuitterAjout fait référence à l’id du layout ajout_liste.xml
        buttonAjout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText txtDate = (EditText) findViewById(R.id.date);
                EditText txtBilan = (EditText) findViewById(R.id.bilan);
                EditText txtMotif = (EditText) findViewById(R.id.motif);
                EditText txtVis = (EditText) findViewById(R.id.vis);
                EditText txtIndConf = (EditText) findViewById(R.id.indConf);
                String date=txtDate.getText().toString();
                String bilan=txtBilan.getText().toString();
                String motif = txtMotif.getText().toString();
                String vis=txtVis.getText().toString();
                int indconf=Integer.valueOf(txtIndConf.getText().toString());
                int codep= Integer.valueOf(extras.getString("CODEP"));
                Visite uneVisite = new Visite(date,bilan,motif,vis,indconf,codep);
                accesDAO.addVisite(uneVisite);
                finish();
                Intent i = new Intent(AjoutVisite.this, ListPraticiens.class);
                startActivityForResult(i, 1);
            }
        });
        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();  //fermeture de la fenêtre
            }
        });
    }
}
