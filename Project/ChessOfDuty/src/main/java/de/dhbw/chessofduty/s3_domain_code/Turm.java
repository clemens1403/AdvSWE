package de.dhbw.chessofduty.s3_domain_code;

import java.util.ArrayList;

public class Turm extends Figur{

    public Turm(int farbe, Feld startPosition){

        super(farbe, startPosition);

        this.setName("Turm");
        this.setAbkuerzung("T");
        this.setWert(5);
    }

}

