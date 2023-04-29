package de.dhbw.chessofduty.s2_application_code.figuren;

import de.dhbw.chessofduty.s3_domain_code.*;

import java.util.ArrayList;

public class TurmDienst extends FigurDienst{
    private Turm turm;
    private int spalte;
    private int zeile;

    public void TurmService(int farbe, Feld startPosition){
        this.turm = new Turm(farbe, startPosition);
    }

    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett){
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        this.spalte = this.turm.getPosition().getSpalte();
        this.zeile = this.turm.getPosition().getZeile();

        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.OBEN, Bewegungsmatrizen.OBEN, figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.UNTEN, Bewegungsmatrizen.UNTEN, figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.LINKS, Bewegungsmatrizen.LINKS, figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.RECHTS, Bewegungsmatrizen.RECHTS, figuren, schachbrett));

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicheZuegeRichtung(Bewegungsrichtung richtung, Bewegungsmatrizen matrix, ArrayList<Figur> figuren, Schachbrett schachbrett){
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        int matrizenWert = matrix.erhalteMatrizenWert(spalte, zeile);

        if ((spalte == richtung.spaltenVerbot() && richtung.zeilenVerbot() != 0) || (richtung.zeilenVerbot() != 0 && zeile == richtung.zeilenVerbot())) {
            return moeglicheZuege;
        }

        int naechsteSpalte = spalte + richtung.spaltenVerschiebung();
        int naechsteZeile = zeile + richtung.zeilenVerschiebung();

        return ermittleMoeglicheZuege(richtung, matrizenWert, naechsteSpalte, naechsteZeile, figuren, schachbrett, this.turm);
    }
}
