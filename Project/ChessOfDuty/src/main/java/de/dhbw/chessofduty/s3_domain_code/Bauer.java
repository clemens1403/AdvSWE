package de.dhbw.chessofduty.s3_domain_code;

import java.util.ArrayList;

public class Bauer extends Figur{

    private boolean doppelschrittMoeglich = true;
    private Feld startPosition;

    public Bauer(int farbe, Feld startPosition){
        super(farbe, startPosition);
        this.setName("Bauer");
        this.setAbkuerzung("B");
        this.setWert(1);
        this.startPosition = startPosition;
    }

    public void setDoppelschrittMoeglich(boolean doppelschrittMoeglich) {
        this.doppelschrittMoeglich = doppelschrittMoeglich;
    }

    public boolean getDoppelschrittMoeglich(){
        return this.doppelschrittMoeglich;
    }


    public Feld getStartposition(){
        return this.startPosition;
    }
}
