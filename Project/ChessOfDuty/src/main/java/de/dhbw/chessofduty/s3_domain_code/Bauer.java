package de.dhbw.chessofduty.s3_domain_code;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class Bauer extends Figur{

    private boolean doppelschrittMoeglich = true;
    private Feld startPosition;

    public Bauer(int farbe, Feld startPosition){
        super(farbe, startPosition);
        this.setName("Bauer");
        this.setAbkuerzung("B");
        this.setWert(1);
        this.startPosition = startPosition;
    }

    public void wirdUmgewandelt(){

    }

    public void istEnPassantMoeglich(){
        // Implementierung der En-Passant-Regel für den Bauern
    }

    public boolean istDoppelschrittMoeglich(){
        return this.doppelschrittMoeglich;
    }

    public void doppelschrittNichtMehrMoeglich(){
        this.doppelschrittMoeglich = false;
    }

    @Override
    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett){
        print("\n Berechne moegliche Zuege \n");
        /*
        Prüfe mögliche Züge für die Figur 'Bauer'

        Ein Bauer kann sich in folgende Richtungen bewegen:
            - zwei Felder nach vorne (sofern noch nicht bewegt)
            - ein Feld nach vorne (sofern bereits bewegt wurden und keine Figur blockiert)
            - ein Feld schräg nach links oder rechts vorne (andere Figur wird geschlagen)
            - ein Feld schräg nach links oder rechts vorne (En Passent)*/


        // Farbe: 0 = Schwarz, 1 = Weiß

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        Feld aktuellePosition = this.getPosition();
        if(aktuellePosition != this.startPosition){
            this.doppelschrittMoeglich = false;
        }
        int aktuelleZeile = aktuellePosition.getZeile();
        print("aktuelleZeile: " + aktuelleZeile + "\n");
        int aktuelleSpalte = aktuellePosition.getSpalte();


        boolean einzelschritt = true;
        boolean doppelschritt = this.doppelschrittMoeglich;

        // Bauern können sich nur vorwärt auf dem Spielfeld bewegen
        if(this.getFarbe() == 1){
            //Die weißen Figuren bewegen sich bei den Zeilen in aufsteigende Richtung

            //Einfacher Schritt nach vorne
            //int einfacherSchritt = aktuellePosition.getZeile() + 1;
            //moeglicheZuege.add((aktuellePosition.getSpalte(), einfacherSchritt));


            for(Figur f : figuren){
                Feld positionFigur = f.getPosition();
                int zeileFigur = positionFigur.getZeile();
                int spalteFigur = positionFigur.getSpalte();
                if(aktuelleSpalte == spalteFigur){
                    if(aktuelleZeile == zeileFigur - 1){
                        print("Kein Zug möglich wegen Kollision mit \n");
                        print(f.getAbkuerzung() + " Spalte: " + spalteFigur + " Zeile: " + zeileFigur);
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
                        if(this.getFarbe() != f.getFarbe()){
                            print("\nSchlagen möglich");
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
            //Die schwarzen Figuren bewegen sich bei den Zeilen in absteigende Richtung

            //Einfacher Schritt nach vorne
            //int einfacherSchritt = aktuellePosition.getZeile() - 1;
            //moeglicheZuege.add(new SimpleEntry<>(aktuellePosition.getSpalte(), einfacherSchritt));


            for(Figur f : figuren){
                Feld positionFigur = f.getPosition();
                int zeileFigur = positionFigur.getZeile();
                int spalteFigur = positionFigur.getSpalte();
                if(aktuelleSpalte == spalteFigur){
                    if(aktuelleZeile == zeileFigur + 1){
                        print("Kein Zug möglich wegen Kollision mit \n");
                        print(f.getAbkuerzung() + " Spalte: " + spalteFigur + " Zeile: " + zeileFigur);
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
                        if(this.getFarbe() != f.getFarbe()){
                            print("\nSchlagen möglich");
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

        //int zeile = aktuellePosition.getZeile();

        for (Feld eintrag : moeglicheZuege) {
            System.out.println("(" + eintrag.getSpalte() + ", " + eintrag.getZeile() + ")");
        }

        return moeglicheZuege;
    }
}
