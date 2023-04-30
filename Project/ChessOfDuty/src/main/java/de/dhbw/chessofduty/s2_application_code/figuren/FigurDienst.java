package de.dhbw.chessofduty.s2_application_code.figuren;

import de.dhbw.chessofduty.s3_domain_code.*;

import java.util.ArrayList;

public class FigurDienst {

    protected ArrayList<Feld> ermittleMoeglicheZuege(Bewegungsrichtung bewegungsRichtung, int beweglicheFelder, int spalte, int zeile, ArrayList<Figur> figuren, Schachbrett schachbrett, Figur figur){
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        for(int i = 1; i <= beweglicheFelder; i++){
            Figur kollidierteFigur = findeKollision(figuren, spalte, zeile);

            if(kollidierteFigur != null){
                if(figur.getFarbe() != kollidierteFigur.getFarbe()){
                    moeglicheZuege.add(schachbrett.getFeld(spalte, zeile));
                    break;
                } else break;
            } else {
                moeglicheZuege.add(schachbrett.getFeld(spalte, zeile));
            }

            spalte += bewegungsRichtung.spaltenVerschiebung();
            zeile += bewegungsRichtung.zeilenVerschiebung();
        }

        return moeglicheZuege;
    }

    protected Figur findeKollision(ArrayList<Figur> figuren, int spalte, int zeile){
        Figur kollidierteFigur = null;

        for(Figur f : figuren){
            if(f.getPosition().getSpalte() == spalte && f.getPosition().getZeile() == zeile){
                kollidierteFigur = f;
                break;
            }
        }

        return kollidierteFigur;
    }

    public boolean checkFigurGeklickt(Figur figur, int mausX, int mausY){

        int feldX = figur.getPosition().getX();
        int feldY = figur.getPosition().getY();
        int feldGroesse = figur.getPosition().getGroesse();

        if((mausX > feldX) && (mausX < feldX+feldGroesse)){
            if((mausY > feldY) && (mausY < feldY+feldGroesse)){
                return true;
            }
        }

        return false;
    }


}
