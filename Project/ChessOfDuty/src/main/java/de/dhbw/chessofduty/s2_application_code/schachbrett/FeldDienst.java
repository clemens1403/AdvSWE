package de.dhbw.chessofduty.s2_application_code.schachbrett;

import de.dhbw.chessofduty.s3_domain_code.Feld;

public class FeldDienst {

    private Feld feld;

    public FeldDienst(int spalte, int zeile) {
        this.feld = new Feld(spalte, zeile);
    }

    public Feld pruefeFeldGeklickt(int mausX, int mausY){

        if((mausX > feld.getX()) && (mausX < feld.getX() + feld.getGroesse())){
            if((mausY > feld.getY()) && (mausY < feld.getY() + feld.getGroesse())){
                return feld;
            }
        }

        //Refactoring hier? Wir müssen was anderes als null wiedergeben z.B. Optional oder so
        return null;
    }

    public Feld getFeld(){
        return this.feld;
    }
}
