package de.dhbw.chessofduty.s3_domain_code;

import java.util.ArrayList;

public class Dame extends Figur{
    int spalte = -1;
    int zeile = -1;

    public Dame(int farbe, Feld startPosition){

        super(farbe, startPosition);

        this.setName("Dame");
        this.setAbkuerzung("D");
        this.setWert(9);
    }


}
