package de.dhbw.chessofduty.s3_domain_code;

import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.Arrays;

public final class Schachbrett extends PApplet {
    private PGraphics g;
    private final Feld[][] felder;

    public Schachbrett(){

        felder = new Feld[8][8];

        for(int spalte = 1; spalte <= 8; spalte++){

            for(int zeile = 1; zeile<= 8; zeile++){
                felder[spalte-1][zeile-1] = new Feld(spalte,zeile);
            }
        }
    }

    public void renderSchachbrett(PGraphics g, int mausX, int mausY){
        for(int i = 1; i <= 8; i++){
            for(int j = 1; j <= 8; j++){
                Feld f = felder[i-1][j-1];
                f.render(g, mausX, mausY);
            }
        }

        for (int i = 1; i <= 8; i++){
            g.pushStyle();
            g.fill(200);
            g.textAlign(CENTER, CENTER);
            g.textSize(20);
            g.text(integerZuBuchstabe(i-1), 250 + (i-1)*100, 1020);
            g.text(i, 180, 950 - (i-1)*100);
            g.popStyle();
        }
    }

    public String integerZuBuchstabe(int i){

        if(i < 0){
            return null;
        }
        String s = Integer.toString(i, 26);

        char[] buchstaben = s.toCharArray();

        String ergebnis = "";
        for(char b : buchstaben){
            int x = Integer.parseInt(Character.valueOf(b).toString(), 26);
            ergebnis += String.valueOf((char)(x + 'A'));
        }

        return ergebnis;
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

    public Feld[][] getFelder(){
        return felder;
    }

    public Feld getFeld(int spalte, int zeile){
        return felder[spalte-1][zeile-1];
    }
}

