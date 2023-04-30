package de.dhbw.chessofduty.s2_application_code.figuren;

import de.dhbw.chessofduty.s3_domain_code.*;

import java.util.ArrayList;

public class KoenigDienst {
    public void KoenigDienst(){

    }

    public Koenig erzeugeKoenig(int farbe, Feld startPosition){
        return new Koenig(farbe, startPosition);
    }

    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett, Koenig koenig){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        int spalte = koenig.getPosition().getSpalte();
        int zeile = koenig.getPosition().getZeile();

        moeglicheZuege.addAll(moeglicherZugNachVorne(figuren, schachbrett, spalte, zeile, koenig));
        moeglicheZuege.addAll(moeglicheZuegeNachVorneLinks(figuren, schachbrett, spalte, zeile, koenig));
        moeglicheZuege.addAll(moeglicherZugNachVorneRechts(figuren, schachbrett, spalte, zeile, koenig));
        moeglicheZuege.addAll(moeglicherZugNachHinten(figuren, schachbrett, spalte, zeile, koenig));
        moeglicheZuege.addAll(moeglicherZugNachHintenLinks(figuren, schachbrett, spalte, zeile, koenig));
        moeglicheZuege.addAll(moeglicherZugNachHintenRechts(figuren,schachbrett, spalte, zeile, koenig));
        moeglicheZuege.addAll(moeglicherZugNachRechts(figuren, schachbrett, spalte, zeile, koenig));
        moeglicheZuege.addAll(moeglicherZugNachLinks(figuren,schachbrett, spalte, zeile, koenig));

        for (Feld eintrag : moeglicheZuege) {
            System.out.println("(" + eintrag.getSpalte() + ", " + eintrag.getZeile() + ")");
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicherZugNachVorne(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Koenig koenig) {

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 8) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == (zeile+1) && f.getPosition().getSpalte() == spalte){
                kollisionGefunden = true;
                kollidierteFigur = f;
                break;
            }
        }

        if(kollisionGefunden){
            System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

            if(koenig.getFarbe() == kollidierteFigur.getFarbe()){
                System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
            } else{
                System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                moeglicheZuege.add(schachbrett.getFeld(spalte, zeile+1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte, zeile+1));
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicheZuegeNachVorneLinks(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Koenig koenig){

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 8 || spalte == 1) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == zeile+1 && f.getPosition().getSpalte() == spalte-1){
                kollisionGefunden = true;
                kollidierteFigur = f;
                break;
            }
        }

        if(kollisionGefunden){
            System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

            if(koenig.getFarbe() == kollidierteFigur.getFarbe()){
                System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
            } else{
                System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile+1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile+1));
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicherZugNachVorneRechts(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Koenig koenig){

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 8 || spalte == 8) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == zeile+1 && f.getPosition().getSpalte() == spalte+1){
                kollisionGefunden = true;
                kollidierteFigur = f;
                break;
            }
        }

        if(kollisionGefunden){
            System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

            if(koenig.getFarbe() == kollidierteFigur.getFarbe()){
                System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
            } else{
                System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile+1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile+1));
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicherZugNachHinten(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Koenig koenig){

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 1) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == zeile-1 && f.getPosition().getSpalte() == spalte){
                kollisionGefunden = true;
                kollidierteFigur = f;
                break;
            }
        }

        if(kollisionGefunden){
            System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

            if(koenig.getFarbe() == kollidierteFigur.getFarbe()){
                System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
            } else{
                System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                moeglicheZuege.add(schachbrett.getFeld(spalte, zeile-1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte, zeile-1));
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicherZugNachHintenLinks(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Koenig koenig){

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 1 || spalte == 1) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == zeile-1 && f.getPosition().getSpalte() == spalte-1){
                kollisionGefunden = true;
                kollidierteFigur = f;
                break;
            }
        }

        if(kollisionGefunden){
            System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

            if(koenig.getFarbe() == kollidierteFigur.getFarbe()){
                System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
            } else{
                System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile-1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile-1));
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicherZugNachHintenRechts(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Koenig koenig) {

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 1 || spalte == 8) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == zeile-1 && f.getPosition().getSpalte() == spalte+1){
                kollisionGefunden = true;
                kollidierteFigur = f;
                break;
            }
        }

        if(kollisionGefunden){
            System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

            if(koenig.getFarbe() == kollidierteFigur.getFarbe()){
                System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
            } else{
                System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile-1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile-1));
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicherZugNachRechts(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Koenig koenig){

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 8) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == zeile && f.getPosition().getSpalte() == spalte+1){
                kollisionGefunden = true;
                kollidierteFigur = f;
                break;
            }
        }

        if(kollisionGefunden){
            System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

            if(koenig.getFarbe() == kollidierteFigur.getFarbe()){
                System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
            } else{
                System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile));
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicherZugNachLinks(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile, Koenig koenig){

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 1) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == zeile && f.getPosition().getSpalte() == spalte-1){
                kollisionGefunden = true;
                kollidierteFigur = f;
                break;
            }
        }

        if(kollisionGefunden){
            System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

            if(koenig.getFarbe() == kollidierteFigur.getFarbe()){
                System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
            } else{
                System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile));
        }

        return moeglicheZuege;
    }
}
