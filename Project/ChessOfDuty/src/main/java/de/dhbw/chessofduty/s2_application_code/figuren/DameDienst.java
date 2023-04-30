package de.dhbw.chessofduty.s2_application_code.figuren;

import de.dhbw.chessofduty.s3_domain_code.*;

import java.util.ArrayList;

public class DameDienst extends FigurDienst {


    public void DameDienst(){

    }

    public Dame erzeugeDame(int farbe, Feld startPosition){
        return new Dame(farbe, startPosition);
    }

    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett, Dame dame){
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.OBEN_RECHTS, Bewegungsmatrizen.OBEN_RECHTS, figuren, schachbrett, dame));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.OBEN_LINKS, Bewegungsmatrizen.OBEN_LINKS, figuren, schachbrett, dame));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.UNTEN_RECHTS, Bewegungsmatrizen.UNTEN_RECHTS, figuren, schachbrett, dame));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.UNTEN_LINKS, Bewegungsmatrizen.UNTEN_LINKS, figuren, schachbrett, dame));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.OBEN, Bewegungsmatrizen.OBEN, figuren, schachbrett, dame));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.UNTEN, Bewegungsmatrizen.UNTEN, figuren, schachbrett, dame));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.LINKS, Bewegungsmatrizen.LINKS, figuren, schachbrett, dame));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.RECHTS, Bewegungsmatrizen.RECHTS, figuren, schachbrett, dame));

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicheZuegeRichtung(Bewegungsrichtung richtung, Bewegungsmatrizen matrix, ArrayList<Figur> figuren, Schachbrett schachbrett, Dame dame){
        int spalte = dame.getPosition().getSpalte();
        int zeile = dame.getPosition().getZeile();

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        int platzhalter = matrix.erhalteMatrizenWert(spalte, zeile);

        if ((spalte == richtung.spaltenVerbot() && richtung.zeilenVerbot() != 0) || (richtung.zeilenVerbot() != 0 && zeile == richtung.zeilenVerbot())) {
            return moeglicheZuege;
        }

        int naechsteSpalte = spalte + richtung.spaltenVerschiebung();
        int naechsteZeile = zeile + richtung.zeilenVerschiebung();

        return ermittleMoeglicheZuege(richtung, platzhalter, naechsteSpalte, naechsteZeile, figuren, schachbrett, dame);
    }
}
