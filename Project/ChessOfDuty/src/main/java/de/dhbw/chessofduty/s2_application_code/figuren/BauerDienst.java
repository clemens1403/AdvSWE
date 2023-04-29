package de.dhbw.chessofduty.s2_application_code.figuren;

import de.dhbw.chessofduty.s3_domain_code.*;

import java.util.ArrayList;

public class BauerDienst extends FigurDienst{

    public void BauerDienst(){
    }

    public Bauer erzeugeBauer(int farbe, Feld startPosition){
        return new Bauer(farbe, startPosition);
    }

    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett, Bauer bauer){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        Feld aktuellePosition = bauer.getPosition();
        if(aktuellePosition != bauer.getStartposition()){
            bauer.setDoppelschrittMoeglich(false);
        }
        int aktuelleZeile = aktuellePosition.getZeile();
        int aktuelleSpalte = aktuellePosition.getSpalte();


        boolean einzelschritt = true;
        boolean doppelschritt = bauer.getDoppelschrittMoeglich();

        if(bauer.getFarbe() == 1){
            for(Figur f : figuren){
                Feld positionFigur = f.getPosition();
                int zeileFigur = positionFigur.getZeile();
                int spalteFigur = positionFigur.getSpalte();
                if(aktuelleSpalte == spalteFigur){
                    if(aktuelleZeile == zeileFigur - 1){
                        einzelschritt = false;
                        doppelschritt = false;
                    }else if(doppelschritt){
                        if(aktuelleZeile == zeileFigur - 2){
                            doppelschritt = false;
                        }
                    }
                }

                if(aktuelleSpalte - 1 == spalteFigur || aktuelleSpalte + 1 == spalteFigur){
                    if(aktuelleZeile == zeileFigur - 1){
                        if(bauer.getFarbe() != f.getFarbe()){
                            moeglicheZuege.add(positionFigur);
                        }
                    }
                }
            }

            if(einzelschritt){
                moeglicheZuege.add(schachbrett.getFeld(aktuelleSpalte, aktuelleZeile + 1));
            }
            if(doppelschritt){
                moeglicheZuege.add(schachbrett.getFeld(aktuelleSpalte, aktuelleZeile + 2));
            }

        } else {
            for(Figur f : figuren){
                Feld positionFigur = f.getPosition();
                int zeileFigur = positionFigur.getZeile();
                int spalteFigur = positionFigur.getSpalte();
                if(aktuelleSpalte == spalteFigur){
                    if(aktuelleZeile == zeileFigur + 1){
                        einzelschritt = false;
                        doppelschritt = false;
                    }else if(doppelschritt){
                        if(aktuelleZeile == zeileFigur + 2){
                            doppelschritt = false;
                        }
                    }
                }

                if(aktuelleSpalte - 1 == spalteFigur || aktuelleSpalte + 1 == spalteFigur){
                    if(aktuelleZeile == zeileFigur + 1){
                        if(bauer.getFarbe() != f.getFarbe()){
                            moeglicheZuege.add(positionFigur);
                        }
                    }
                }
            }

            if(einzelschritt){
                moeglicheZuege.add(schachbrett.getFeld(aktuelleSpalte, aktuelleZeile - 1));
            }
            if(doppelschritt){
                moeglicheZuege.add(schachbrett.getFeld(aktuelleSpalte, aktuelleZeile - 2));
            }
        }

        for (Feld eintrag : moeglicheZuege) {
            System.out.println("(" + eintrag.getSpalte() + ", " + eintrag.getZeile() + ")");
        }

        return moeglicheZuege;
    }
}
