package com.example.zickler.projete4.controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zickler.projete4.R;
import com.example.zickler.projete4.modele.Praticien;
import com.example.zickler.projete4.modele.PraticienDAO;

/**
 * Created by amael on 24/05/2017.
 */

public class ModifPraticien extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_modifprat);
        final Bundle extras = getIntent().getExtras();
        Toast.makeText(getApplicationContext(), "Fenêtre de modification du Praticien code : "+extras.getString("CODEP"), Toast.LENGTH_LONG).show();
        Button buttonQuitter = (Button) findViewById(R.id.quitter);
        // R.id.ButtonQuitterAjout fait référence à l’id du layout ajout_liste.xml

        final PraticienDAO accesDAO = new PraticienDAO(this);
        Button buttonAjout = (Button) findViewById(R.id.button);
        // R.id.ButtonQuitterAjout fait référence à l’id du layout ajout_liste.xml
        buttonAjout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText txtNom = (EditText) findViewById(R.id.nom);
                EditText txtAdresse = (EditText) findViewById(R.id.adresse);
                EditText txtCp = (EditText) findViewById(R.id.cp);
                EditText txtVille = (EditText) findViewById(R.id.ville);
                String nom=txtNom.getText().toString();
                String adresse=txtAdresse.getText().toString();
                String cp = txtCp.getText().toString();
                String ville=txtVille.getText().toString();
                Praticien unPraticien = new Praticien(nom,adresse,cp,ville);
                accesDAO.modifPraticien(unPraticien,extras.getString("CODEP"));
                finish();
                Intent i = new Intent(ModifPraticien.this, ListPraticiens.class);
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
