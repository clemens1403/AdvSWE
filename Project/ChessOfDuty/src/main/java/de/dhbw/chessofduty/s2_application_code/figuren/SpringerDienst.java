package de.dhbw.chessofduty.s2_application_code.figuren;

import de.dhbw.chessofduty.s3_domain_code.Feld;
import de.dhbw.chessofduty.s3_domain_code.Figur;
import de.dhbw.chessofduty.s3_domain_code.Schachbrett;
import de.dhbw.chessofduty.s3_domain_code.Springer;

import java.util.ArrayList;

public class SpringerDienst extends FigurDienst{
    public void SpringerDienst(){

    }

    public Springer erzeugeSpringer(int farbe, Feld startPosition){
        return new Springer(farbe, startPosition);
    }

    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett, Springer springer){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        int spalte = springer.getPosition().getSpalte();
        int zeile = springer.getPosition().getZeile();

        moeglicheZuege.addAll(zweiNachVorneEinsNachLinks(figuren, schachbrett, spalte, zeile, springer));
        moeglicheZuege.addAll(zweiNachVorneEinsNachRechts(figuren, schachbrett, spalte, zeile, springer));
        moeglicheZuege.addAll(zweiNachRechtsEinesNachOben(figuren, schachbrett, spalte, zeile, springer));
        moeglicheZuege.addAll(zweiNachRechtsEinesNachUnten(figuren, schachbrett, spalte, zeile, springer));
        moeglicheZuege.addAll(zweiNachUntenEinesNachRechts(figuren, schachbrett, spalte, zeile, springer));
        moeglicheZuege.addAll(zweiNachUntenEinesNachLinks(figuren,schachbrett, spalte, zeile, springer));
        moeglicheZuege.addAll(zweiNachLinksEinesNachUnten(figuren, schachbrett, spalte, zeile, springer));
        moeglicheZuege.addAll(zweiNachLinksEinensNachOben(figuren,schachbrett, spalte, zeile, springer));

        for (Feld eintrag : moeglicheZuege) {
            System.out.println("(" + eintrag.getSpalte() + ", " + eintrag.getZeile() + ")");
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> zweiNachVorneEinsNachLinks(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Springer springer){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;

        if(zeile >= 7 || spalte == 1) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == (zeile+2) && f.getPosition().getSpalte() == spalte-1){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile+2));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile+2));
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> zweiNachVorneEinsNachRechts(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Springer springer){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;

        if(zeile >= 7 || spalte == 8) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == (zeile+2) && f.getPosition().getSpalte() == spalte+1){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile+2));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile+2));
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> zweiNachRechtsEinesNachOben(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Springer springer){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;

        if(zeile == 8 || spalte >= 7) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == (zeile+1) && f.getPosition().getSpalte() == spalte+2){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte+2, zeile+1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte+2, zeile+1));
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> zweiNachRechtsEinesNachUnten(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Springer springer){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;

        if(zeile == 1 || spalte >= 7) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == (zeile-1) && f.getPosition().getSpalte() == spalte+2){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte+2, zeile-1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte+2, zeile-1));
        }

        return moeglicheZuege;}

    private ArrayList<Feld> zweiNachUntenEinesNachRechts(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Springer springer){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;

        if(zeile <= 2 || spalte == 8) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == (zeile-2) && f.getPosition().getSpalte() == spalte+1){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile-2));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile-2));
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> zweiNachUntenEinesNachLinks(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Springer springer){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;

        if(zeile <= 2 || spalte == 1) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == (zeile-2) && f.getPosition().getSpalte() == spalte-1){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile-2));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile-2));
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> zweiNachLinksEinesNachUnten(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Springer springer){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;

        if(zeile == 1 || spalte <= 2) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == (zeile-1) && f.getPosition().getSpalte() == spalte-2){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte-2, zeile-1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte-2, zeile-1));
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> zweiNachLinksEinensNachOben(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Springer springer){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;

        if(zeile == 8 || spalte <= 2) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == (zeile+1) && f.getPosition().getSpalte() == spalte-2){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte-2, zeile+1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte-2, zeile+1));
        }

        return moeglicheZuege;
    }
}
