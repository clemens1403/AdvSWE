package de.dhbw.chessofduty.s2_application_code.spiellogik;

import de.dhbw.chessofduty.s2_application_code.schachbrett.SchachbrettDienst;
import de.dhbw.chessofduty.s3_domain_code.Schachspiel;

public class SchachspielDienst {

    private SchachbrettDienst schachbrettDienst;

    public SchachspielDienst(SchachbrettDienst schachbrettDienst){
        this.schachbrettDienst = schachbrettDienst;
    }

    public Schachspiel erstelleSchachspiel(){
        return new Schachspiel(schachbrettDienst.createSchachbrett());
    }
}
