package de.dhbw.chessofduty.s2_application_code.figuren;

import de.dhbw.chessofduty.s3_domain_code.Feld;
import de.dhbw.chessofduty.s3_domain_code.Figur;
import de.dhbw.chessofduty.s3_domain_code.Schachbrett;
import de.dhbw.chessofduty.s3_domain_code.Springer;

import java.util.ArrayList;

public class SpringerDienst extends FigurDienst{

    public Springer erzeugeSpringer(int farbe, Feld startPosition){
        return new Springer(farbe, startPosition);
    }

    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett, Springer springer){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        int spalte = springer.getPosition().getSpalte();
        int zeile = springer.getPosition().getZeile();

        moeglicheZuege.addAll(checkZugMoeglich(2,-1, figuren, schachbrett, spalte, zeile, springer));
        moeglicheZuege.addAll(checkZugMoeglich(2,1,figuren, schachbrett, spalte, zeile, springer));
        moeglicheZuege.addAll(checkZugMoeglich(1, 2,figuren, schachbrett, spalte, zeile, springer));
        moeglicheZuege.addAll(checkZugMoeglich(-1,2, figuren, schachbrett, spalte, zeile, springer));
        moeglicheZuege.addAll(checkZugMoeglich(-2,1, figuren, schachbrett, spalte, zeile, springer));
        moeglicheZuege.addAll(checkZugMoeglich(-2,-1, figuren,schachbrett, spalte, zeile, springer));
        moeglicheZuege.addAll(checkZugMoeglich(-1, -2, figuren, schachbrett, spalte, zeile, springer));
        moeglicheZuege.addAll(checkZugMoeglich(1, -2, figuren,schachbrett, spalte, zeile, springer));

        for (Feld eintrag : moeglicheZuege) {
            System.out.println("(" + eintrag.getSpalte() + ", " + eintrag.getZeile() + ")");
        }

        return moeglicheZuege;
    }
    private ArrayList<Feld> checkZugMoeglich(int zeilenBewegung, int spaltenBewegung, ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Springer springer){
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;

        if(zeile+ zeilenBewegung > 8 || zeile + zeilenBewegung < 1 || spalte +spaltenBewegung > 8 || spalte + spaltenBewegung < 1){
            return moeglicheZuege;
        }

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == (zeile + zeilenBewegung) && f.getPosition().getSpalte() == spalte + spaltenBewegung){
                kollisionGefunden = true;
                kollidierteFigur = f;
                break;
            }
        }
        if(kollisionGefunden){
            System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

            if(springer.getFarbe() == kollidierteFigur.getFarbe()){
                System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
            } else{
                System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                moeglicheZuege.add(schachbrett.getFeld(spalte + spaltenBewegung, zeile + zeilenBewegung));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte + spaltenBewegung, zeile + zeilenBewegung));
        }

        return moeglicheZuege;
    }
}
