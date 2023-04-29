package de.dhbw.chessofduty.s2_application_code.schachbrett;

import de.dhbw.chessofduty.s3_domain_code.Feld;
import processing.core.PGraphics;

import static processing.core.PConstants.CORNER;

public class FeldService {

    Feld feld;
    int mausX, mausY;

    public FeldService(int spalte, int zeile) {
        this.feld = new Feld(spalte, zeile);

    }

    public void zeichne(PGraphics g, int mausX, int mausY){
        this.mausX = mausX;
        this.mausY = mausY;

        g.pushMatrix();
        g.fill(255 * feld.getFarbe());
        g.rectMode(CORNER);
        g.rect(feld.getX(), feld.getY(), feld.getGroesse(), feld.getGroesse());
        g.popMatrix();
    }

    public Feld pruefeFeldGeklickt(){

        if((mausX > feld.getX()) && (mausX < feld.getX() + feld.getGroesse())){
            if((mausY > feld.getY()) && (mausY < feld.getY() + feld.getGroesse())){
                return feld;
            }
        }

        //Refactoring hier? Wir müssen was anderes als null wiedergeben z.B. Optional oder so
        return null;
    }

}
