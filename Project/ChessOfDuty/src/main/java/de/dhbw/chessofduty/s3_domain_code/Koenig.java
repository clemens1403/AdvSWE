package de.dhbw.chessofduty.s3_domain_code;

import java.util.ArrayList;

public class Koenig extends Figur{

    private boolean imSchach = false;
    private boolean schachMatt = false;

    public Koenig(int farbe, Feld startPosition){

        super(farbe, startPosition);

        this.setName("König");
        this.setAbkuerzung("K");
    }

}

