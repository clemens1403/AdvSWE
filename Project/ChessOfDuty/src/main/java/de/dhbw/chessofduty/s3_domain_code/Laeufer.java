package de.dhbw.chessofduty.s3_domain_code;

import java.util.ArrayList;

public class Laeufer extends Figur{

    int spalte = -1;
    int zeile = -1;

    public Laeufer(int farbe, Feld startPosition){

        super(farbe, startPosition);

        this.setName("Läufer");
        this.setAbkuerzung("L");
        this.setWert(3);
    }

}

