package com.example.zickler.projete4.modele;

public class Visite {
    private int NUMR;
    private String DATE;
    private String BILAN;
    private String MOTIF;
    private String VIS;
    private int INDCONF;
    private int CODEP;

    public Visite(String date, String bilan, String motif, String visiteur, int indconf, int CODEP) {
        this.NUMR=-1;
        this.DATE = date;
        this.BILAN = bilan;
        this.MOTIF = motif;
        this.VIS = visiteur;
        this.INDCONF = indconf;
        this.CODEP = CODEP;
    }

    public Visite(int NUMR, String date, String bilan, String motif, String visiteur, int indconf, int CODEP) {
        this.NUMR=NUMR;
        this.DATE = date;
        this.BILAN = bilan;
        this.MOTIF = motif;
        this.VIS = visiteur;
        this.INDCONF = indconf;
        this.CODEP = CODEP;
    }

    public int getNUMR() {
        return NUMR;
    }

    public String getDATE() {
        return DATE;
    }

    public String getBILAN() {
        return BILAN;
    }

    public String getMOTIF() {
        return MOTIF;
    }

    public String getVIS() {
        return VIS;
    }

    public int getINDCONF() {
        return INDCONF;
    }

    public int getCODEP() {
        return CODEP;
    }
}