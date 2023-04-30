package de.dhbw.chessofduty.s2_application_code.spielzug;

import de.dhbw.chessofduty.s3_domain_code.spielzug.Schachzug;
import de.dhbw.chessofduty.s3_domain_code.spielzug.Spielzug;
import de.dhbw.chessofduty.s3_domain_code.spielzug.SpielzugRepository;

import java.util.ArrayList;

public class SpielzugDienst {

    public Spielzug erstelleSpielzug(ArrayList<Spielzug> spielzuege){
        return new Spielzug(spielzuege.size() + 1);
    }

    public Spielzug setzteSchachzugWeiss(Spielzug spielzug, Schachzug schachzug){
        spielzug.setSchachzugWeiss(schachzug);
        return spielzug;
    }

    public Spielzug setzteSchachzugSchwarz(Spielzug spielzug, Schachzug schachzug){
        spielzug.setSchachzugSchwarz(schachzug);
        return spielzug;
    }

}
