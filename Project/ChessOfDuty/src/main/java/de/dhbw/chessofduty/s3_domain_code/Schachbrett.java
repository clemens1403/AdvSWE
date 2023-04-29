package de.dhbw.chessofduty.s3_domain_code;

import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.Arrays;

public final class Schachbrett extends PApplet {

    private final Feld[][] felder;

    public Schachbrett(){

        felder = new Feld[8][8];

        for(int spalte = 1; spalte <= 8; spalte++){

            for(int zeile = 1; zeile<= 8; zeile++){
                felder[spalte-1][zeile-1] = new Feld(spalte,zeile);
            }
        }
    }
    @Override
    public boolean equals(Object objekt){
        if(objekt == this){
            return true;
        }

        if(!(objekt instanceof Schachbrett)){
            return false;
        }

        Schachbrett schachbrett = (Schachbrett) objekt;

        return Arrays.deepEquals(this.felder, schachbrett.felder);
    }

    @Override
    public int hashCode(){
        return Arrays.deepHashCode(felder);
    }

    public Feld getFeld(int spalte, int zeile){
        return felder[spalte-1][zeile-1];
    }

    public Feld[][] getFelder(){
        return this.felder;
    }
}

