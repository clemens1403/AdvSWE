package de.dhbw.chessofduty.s3_domain_code;

import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;

public abstract class Figur extends PApplet {

    private int mausX, mausY;
    private String name;
    private String abkuerzung;
    private int wert;
    private int farbe;

    protected Feld position;

    public Figur(int farbe, Feld startPosition){

        this.farbe = farbe;
        this.position = startPosition;
    }

    public Feld getPosition(){
        return this.position;
    }

    public void setPosition(Feld feld){
        //Hier müssen definitiv noch einige Fehler abgefangen werden
        this.position = feld;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setWert(int wert){
        this.wert = wert;
    }

    public void setAbkuerzung(String abkuerzung){
        this.abkuerzung = abkuerzung;
    }

    public String getAbkuerzung(){
        return abkuerzung;
    }

    public int getFarbe(){
        return this.farbe;
    }
    public void setFarbe(int farbe){
        this.farbe = farbe;
    }

    @Override
    public String toString(){
        return abkuerzung
                + " "
                +  String.valueOf(farbe)
                + ": Spalte:"
                + String.valueOf(getPosition().getSpalte())
                + " Zeile:"
                + String.valueOf(getPosition().getZeile());
    }
}