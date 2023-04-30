package de.dhbw.chessofduty.s2_application_code.schachbrett;

import de.dhbw.chessofduty.s3_domain_code.Feld;

public class FeldDienst {

    public Feld pruefeFeldGeklickt(int mausX, int mausY, Feld feld){

        if((mausX > feld.getX()) && (mausX < feld.getX() + feld.getGroesse())){
            if((mausY > feld.getY()) && (mausY < feld.getY() + feld.getGroesse())){
                return feld;
            }
        }

        //Refactoring hier? Wir müssen was anderes als null wiedergeben z.B. Optional oder so
        return null;
    }
}
