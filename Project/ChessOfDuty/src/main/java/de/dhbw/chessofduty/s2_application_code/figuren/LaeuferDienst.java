package de.dhbw.chessofduty.s2_application_code.figuren;

import de.dhbw.chessofduty.s3_domain_code.*;

import java.util.ArrayList;

public class LaeuferDienst extends FigurDienst{


    public Laeufer erzeugeLaeufer(int farbe, Feld startPosition){
        return new Laeufer(farbe, startPosition);
    }

    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett, Laeufer laeufer){
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.OBEN_RECHTS, Bewegungsmatrizen.OBEN_RECHTS, figuren, schachbrett, laeufer));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.OBEN_LINKS, Bewegungsmatrizen.OBEN_LINKS, figuren, schachbrett, laeufer));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.UNTEN_RECHTS, Bewegungsmatrizen.UNTEN_RECHTS, figuren, schachbrett, laeufer));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.UNTEN_LINKS, Bewegungsmatrizen.UNTEN_LINKS, figuren, schachbrett, laeufer));

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicheZuegeRichtung(Bewegungsrichtung richtung, Bewegungsmatrizen matrix, ArrayList<Figur> figuren, Schachbrett schachbrett, Laeufer laeufer){
        int spalte = laeufer.getPosition().getSpalte();
        int zeile = laeufer.getPosition().getZeile();

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        int platzhalter = matrix.erhalteMatrizenWert(spalte, zeile);

        if ((spalte == richtung.spaltenVerbot() && richtung.zeilenVerbot() != 0) || (richtung.zeilenVerbot() != 0 && zeile == richtung.zeilenVerbot())) {
            return moeglicheZuege;
        }

        int naechsteSpalte = spalte + richtung.spaltenVerschiebung();
        int naechsteZeile = zeile + richtung.zeilenVerschiebung();

        return ermittleMoeglicheZuege(richtung, platzhalter, naechsteSpalte, naechsteZeile, figuren, schachbrett, laeufer);
    }
}
