package com.example.zickler.projete4.modele;

public class Praticien {


    private int CODEP;
    private String NOM;
    private String ADRESSE;
    private String CP;
    private String VILLE;

    public Praticien(int _id, String REF, String DES, String PU, String QTE) {
        this.CODEP = _id;
        this.NOM = REF;
        this.ADRESSE = DES;
        this.CP = PU;
        this.VILLE = QTE;
    }

    public Praticien(String REF, String DES, String PU, String QTE) {
        this.CODEP = -1;
        this.NOM = REF;
        this.ADRESSE = DES;
        this.CP = PU;
        this.VILLE = QTE;
    }

    public String getCODEP() {
        return String.valueOf(CODEP);
    }

    public String getNOM() {
        return NOM;
    }

    public String getADRESSE() {
        return ADRESSE;
    }

    public String getCP() {
        return CP;
    }

    public String getVILLE() {
        return VILLE;
    }
}