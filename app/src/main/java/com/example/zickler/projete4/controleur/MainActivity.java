package com.example.zickler.projete4.controleur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zickler.projete4.R;
import com.example.zickler.projete4.modele.Praticien;
import com.example.zickler.projete4.modele.Visite;
import com.example.zickler.projete4.modele.VisiteDAO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonPracticien = (Button) findViewById(R.id.buttonPrat);
        Button buttonDernieresVisites = (Button) findViewById(R.id.buttonVisites);
        Button buttonPratAVis = (Button) findViewById(R.id.buttonAnciennesVisites);
        final EditText txtDateDeb = (EditText) findViewById(R.id.dateDeb);
        final EditText txtDateFin = (EditText) findViewById(R.id.dateFin);
        final EditText txtDateMax = (EditText) findViewById(R.id.dateMax);
       // testBd();
        View.OnClickListener onClickLister = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonVisites:
                        Intent intent = new Intent(MainActivity.this, ListVisitesBetween.class);
                        intent.putExtra("dateDebut",txtDateDeb.getText().toString());
                        intent.putExtra("dateFin",txtDateFin.getText().toString());
                        startActivity(intent);
                        break;
                    case R.id.buttonPrat:
                        Intent intentlArt = new Intent(MainActivity.this, ListPraticiens.class);
                        startActivity(intentlArt);
                        break;
                    case R.id.buttonAnciennesVisites:
                        Intent intentaVis = new Intent(MainActivity.this, ListeVisitesMax.class);
                        intentaVis.putExtra("dateMax",txtDateMax.getText().toString());
                        startActivity(intentaVis);
                        break;
                }
            }
        };
        buttonDernieresVisites.setOnClickListener(onClickLister);
        buttonPracticien.setOnClickListener(onClickLister);
        buttonPratAVis.setOnClickListener(onClickLister);
    }


    public void testBd() {
       // ArrayList<Praticien> listeArticleRecherchetest;

        VisiteDAO articleAccestest = new VisiteDAO(this);

        //Cr�ation d'un Article
        Praticien unArticle = new Praticien("JEAN EUDE","RUE COQUILLe","45000","ORLEANS");
        Visite uneVisite = new Visite("1999-06-28","Pas terrible","panique","Louis Armstrong",75,2);

        long ret = articleAccestest.addVisite(uneVisite);

        Toast.makeText(getApplicationContext(),"test : "+ ret, Toast.LENGTH_LONG).show();

        //Pour v�rifier que l'on a bien cr�� un Article dans la BDD
        //on extrait l�article de la BDD gr�ce � la d�signation de l'article que l'on a cr�� pr�c�demment
        Visite uneVisiteFromBdd = articleAccestest.getVisite(1);
        //Si un unArticle est retourn� (donc si le unArticle � bien �t� ajout� � la BDD)
        if (uneVisiteFromBdd != null) {
            //On affiche les infos de l�Article dans un Toast
            Toast.makeText(this, uneVisiteFromBdd.getDATE(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Article non trouvé", Toast.LENGTH_LONG).show();
        }
    }
}
