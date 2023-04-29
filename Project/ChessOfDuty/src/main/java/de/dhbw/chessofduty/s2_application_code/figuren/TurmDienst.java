package de.dhbw.chessofduty.s2_application_code.figuren;

import de.dhbw.chessofduty.s3_domain_code.*;

import java.util.ArrayList;

public class TurmDienst extends FigurDienst{
    public void TurmService(){

    }

    public Turm erzeugeTurm(int farbe, Feld startPosition){
        return new Turm(farbe, startPosition);
    }

    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett, Turm turm){
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();



        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.OBEN, Bewegungsmatrizen.OBEN, figuren, schachbrett, turm));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.UNTEN, Bewegungsmatrizen.UNTEN, figuren, schachbrett, turm));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.LINKS, Bewegungsmatrizen.LINKS, figuren, schachbrett, turm));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.RECHTS, Bewegungsmatrizen.RECHTS, figuren, schachbrett, turm));

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicheZuegeRichtung(Bewegungsrichtung richtung, Bewegungsmatrizen matrix, ArrayList<Figur> figuren, Schachbrett schachbrett, Turm turm){
        int spalte = turm.getPosition().getSpalte();
        int zeile = turm.getPosition().getZeile();

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        int matrizenWert = matrix.erhalteMatrizenWert(spalte, zeile);

        if ((spalte == richtung.spaltenVerbot() && richtung.zeilenVerbot() != 0) || (richtung.zeilenVerbot() != 0 && zeile == richtung.zeilenVerbot())) {
            return moeglicheZuege;
        }

        int naechsteSpalte = spalte + richtung.spaltenVerschiebung();
        int naechsteZeile = zeile + richtung.zeilenVerschiebung();

        return ermittleMoeglicheZuege(richtung, matrizenWert, naechsteSpalte, naechsteZeile, figuren, schachbrett, turm);
    }
}
