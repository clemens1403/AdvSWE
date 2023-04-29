package de.dhbw.chessofduty.s3_domain_code;

import java.util.ArrayList;

public class Springer extends Figur{
    public Springer(int farbe, Feld startPosition){

        super(farbe, startPosition);

        this.setName("Springer");
        this.setAbkuerzung("S");
        this.setWert(3);
    }

}

