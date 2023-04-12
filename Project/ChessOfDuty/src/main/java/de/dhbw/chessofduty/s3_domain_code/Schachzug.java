package de.dhbw.chessofduty.s3_domain_code;

import java.util.Arrays;

public final class Schachzug {

    private final int farbe;
    private final String abkuerzung;
    private final Feld altePosition;
    private final Feld neuePosition;

    public Schachzug(int farbe, String abkuerzung, Feld altePosition, Feld neuePosition){
        this.farbe = farbe;
        this.abkuerzung = abkuerzung;
        this.altePosition = altePosition;
        this.neuePosition = neuePosition;
    }

    @Override
    public boolean equals(Object objekt){
        if (objekt == this){
            return true;
        }

        if(!(objekt instanceof Schachzug)){
            return false;
        }

        Schachzug schachzug = (Schachzug) objekt;

        return farbe == schachzug.farbe &&
                abkuerzung.equals(schachzug.abkuerzung) &&
                altePosition.equals(schachzug.altePosition) &&
                neuePosition.equals(schachzug.neuePosition);
    }
}

