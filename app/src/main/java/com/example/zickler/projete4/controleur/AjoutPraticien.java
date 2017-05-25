package com.example.zickler.projete4.controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zickler.projete4.R;
import com.example.zickler.projete4.modele.Praticien;
import com.example.zickler.projete4.modele.PraticienDAO;

/**
 * Created by amael on 24/05/2017.
 */

public class AjoutPraticien extends MainActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_ajoutprat);
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
                accesDAO.addPraticien(unPraticien);
                finish();
                Intent i = new Intent(AjoutPraticien.this, ListPraticiens.class);
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
