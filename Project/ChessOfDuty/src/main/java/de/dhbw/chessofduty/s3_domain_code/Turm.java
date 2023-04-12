package de.dhbw.chessofduty.s3_domain_code;

import java.util.ArrayList;

public class Turm extends Figur{
    int spalte = -1;
    int zeile = -1;

    public Turm(int farbe, Feld startPosition){

        super(farbe, startPosition);

        this.setName("Turm");
        this.setAbkuerzung("T");
        this.setWert(5);
    }

    @Override
    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett){
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        this.spalte = this.getPosition().getSpalte();
        this.zeile = this.getPosition().getZeile();
        /*
            Die Figur Turm kann folgende Bewegungen ausführen:
                - alle Felder in der aktuellen Spalte
                - alle Felder in der aktuellen Zeile
        */

        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.OBEN, Bewegungsmatrizen.OBEN, figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.UNTEN, Bewegungsmatrizen.UNTEN, figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.LINKS, Bewegungsmatrizen.LINKS, figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.RECHTS, Bewegungsmatrizen.RECHTS, figuren, schachbrett));

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicheZuegeRichtung(Bewegungsrichtung richtung, Bewegungsmatrizen matrix, ArrayList<Figur> figuren, Schachbrett schachbrett){
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        int platzhalter = matrix.erhalteMatrizenWert(spalte, zeile);

        if ((spalte == richtung.spaltenVerbot() && richtung.zeilenVerbot() != 0) || (richtung.zeilenVerbot() != 0 && zeile == richtung.zeilenVerbot())) {
            return moeglicheZuege;
        }

        int naechsteSpalte = spalte + richtung.spaltenVerschiebung();
        int naechsteZeile = zeile + richtung.zeilenVerschiebung();

        return ermittleMoeglicheZuege(richtung, platzhalter, naechsteSpalte, naechsteZeile, figuren, schachbrett);
    }
}

