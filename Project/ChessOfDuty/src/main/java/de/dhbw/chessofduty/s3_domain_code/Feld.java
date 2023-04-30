package de.dhbw.chessofduty.s3_domain_code;

import processing.core.PApplet;
import processing.core.PGraphics;

public final class Feld extends PApplet {

    private int mausX, mausY;
    private final int zeile;
    private final int spalte;
    private final int farbe;
    private final int groesse;
    private final int x;
    private final int y;

    public Feld(int spalte, int zeile){
        this.zeile = zeile;
        this.spalte = spalte;
        this.groesse = 100;
        if((zeile + spalte) % 2 == 0){
            this.farbe = 0;
        }else{
            this.farbe = 1;
        }

        this.x = ((spalte-1)*groesse) + 200;
        this.y = 700 - ((zeile-1)*groesse) + 200;
    }

/*    public void zeichne(PGraphics g){
        g.pushMatrix();
        g.fill(255 * farbe);
        g.rectMode(CORNER);
        g.rect(x, y, groesse, groesse);
        g.popMatrix();
    }*/

    @Override
    public boolean equals(Object objekt){
        if(this == objekt){
            return true;
        }

        if(objekt == null || this.getClass() != objekt.getClass()){
            return false;
        }

        Feld feld = (Feld) objekt;

        return zeile == feld.zeile &&
                spalte == feld.spalte &&
                farbe == feld.farbe &&
                groesse == feld.groesse &&
                x == feld.x &&
                y == feld.y;
    }

    @Override
    public int hashCode(){
        int ergebnis = 187;
        ergebnis = 31 * ergebnis + zeile;
        ergebnis = 31 * ergebnis + spalte;
        ergebnis = 31 * ergebnis + farbe;
        ergebnis = 31 * ergebnis + groesse;
        ergebnis = 31 * ergebnis + x;
        ergebnis = 31 * ergebnis + y;

        return ergebnis;
    }

    @Override
    public String toString(){
        return "Spalte: " + this.getSpalte() + ";  Zeile: " + this.getZeile() + "\n";
    }

    public int getZeile(){
        return this.zeile;
    }

    public int getSpalte(){
        return this.spalte;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getGroesse(){
        return this.groesse;
    }

    public int getFarbe(){ return this.farbe; }
}

