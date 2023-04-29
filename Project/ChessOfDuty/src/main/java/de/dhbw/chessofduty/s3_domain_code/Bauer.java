package de.dhbw.chessofduty.s3_domain_code;

import java.util.ArrayList;

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

    @Override
    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett){
        print("\n Berechne moegliche Zuege \n");

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

        if(this.getFarbe() == 1){
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

        for (Feld eintrag : moeglicheZuege) {
            System.out.println("(" + eintrag.getSpalte() + ", " + eintrag.getZeile() + ")");
        }

        return moeglicheZuege;
    }
}
