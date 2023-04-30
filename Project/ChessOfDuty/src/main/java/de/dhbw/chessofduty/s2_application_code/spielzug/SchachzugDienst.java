package de.dhbw.chessofduty.s2_application_code.spielzug;

import de.dhbw.chessofduty.s3_domain_code.Feld;
import de.dhbw.chessofduty.s3_domain_code.Figur;
import de.dhbw.chessofduty.s3_domain_code.spielzug.Schachzug;

public class SchachzugDienst {

    public Schachzug erstelleSchachzug(Figur figur, String abkuerzung,Feld neuePosition){
        return new Schachzug(figur.getFarbe(), abkuerzung, figur.getPosition(), neuePosition);
    }
}
