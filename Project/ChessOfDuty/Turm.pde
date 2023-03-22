public class Turm extends Figur{
    public Turm(int farbe, Feld startPosition){
        
        super(farbe, startPosition);
        
        this.setName("Turm");
        this.setAbkuerzung("T");
        this.setWert(5);
    }

    @Override
    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett){

        //ArrayList<SimpleEntry<Integer, Integer>> moeglicheZuege = new ArrayList<>();
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        /*
            Die Figur Turm kann folgende Bewegungen ausführen:
                - alle Felder in der aktuellen Spalte
                - alle Felder in der aktuellen Zeile
        */

        moeglicheZuege.addAll(getBewegungInZeile(figuren, schachbrett));
        moeglicheZuege.addAll(getBewegungInSpalte(figuren, schachbrett));

        for (Feld eintrag : moeglicheZuege) {
            System.out.println("(" + eintrag.getSpalte() + ", " + eintrag.getZeile() + ")");
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> getBewegungInZeile(ArrayList<Figur> figuren, Schachbrett schachbrett){
        
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        
        //Erstelle alle möglichen Züge innerhalb einer Zeile
        if(spalte == 1){
            //Felder 2 bis 8 sind möglich
            System.out.println("SPALTE 1");

            for(int spaltenNummer = 2; spaltenNummer <= 8; spaltenNummer++){
                boolean kollisionGefunden = false;
                Figur kollidierteFigur = null;

                for(Figur f : figuren){
                    if(f.getPosition().getZeile() == zeile && f.getPosition().getSpalte() == spaltenNummer){
                        kollisionGefunden = true;
                        kollidierteFigur = f;
                        break;
                    } 
                }

                if(kollisionGefunden){
                    System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                        moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeile));
                    }
                    break;
                } else {
                    moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeile));
                }
            }

        } else if(spalte > 1 && spalte < 8){
            //Felder 1 bis (x-1) und (x+1) bis 8
            System.out.println("SPALTE 2-7");

            //Felder von 1 bis (x-1)
            for(int spaltenNummer = (spalte-1); spaltenNummer >= 1; spaltenNummer--){
                boolean kollisionGefunden = false;
                Figur kollidierteFigur = null;

                for(Figur f : figuren){
                    if(f.getPosition().getZeile() == zeile && f.getPosition().getSpalte() == spaltenNummer){
                        kollisionGefunden = true;
                        kollidierteFigur = f;
                        break;
                    } 
                }

                if(kollisionGefunden){
                    System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                        moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeile));
                    }
                    break;
                } else {
                    moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeile));
                }
            }

            //Felder von (x+1) bis 8
            for(int spaltenNummer = (spalte+1); spaltenNummer <= 8; spaltenNummer++){
                boolean kollisionGefunden = false;
                Figur kollidierteFigur = null;

                for(Figur f : figuren){
                    if(f.getPosition().getZeile() == zeile && f.getPosition().getSpalte() == spaltenNummer){
                        kollisionGefunden = true;
                        kollidierteFigur = f;
                        break;
                    } 
                }

                if(kollisionGefunden){
                    System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                        moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeile));
                    }
                    break;
                } else {
                    moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeile));
                }
            }

        } else if(spalte == 8){
            //Felder 1 bis 7 sind möglich
            System.out.println("SPALTE 8");

            for(int spaltenNummer = 7; spaltenNummer >= 1; spaltenNummer--){
                boolean kollisionGefunden = false;
                Figur kollidierteFigur = null;

                for(Figur f : figuren){
                    if(f.getPosition().getZeile() == zeile && f.getPosition().getSpalte() == spaltenNummer){
                        kollisionGefunden = true;
                        kollidierteFigur = f;
                        break;
                    } 
                }

                if(kollisionGefunden){
                    System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                        moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeile));
                    }
                    break;
                } else {
                    moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeile));
                }
            }

        } else System.out.println("Irgendetwas ist schief gelaufen!");

        return moeglicheZuege;
    } 

    private ArrayList<Feld> getBewegungInSpalte(ArrayList<Figur> figuren, Schachbrett schachbrett) {
        
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

        //Erstelle alle möglichen Züge innerhalb einer Spalte
        if(zeile == 1){
            //Felder 2 bis 8 sind möglich
            System.out.println("SPALTE 1");

            for(int zeilenNummer = 2; zeilenNummer <= 8; zeilenNummer++){
                boolean kollisionGefunden = false;
                Figur kollidierteFigur = null;

                for(Figur f : figuren){
                    if(f.getPosition().getZeile() == zeilenNummer && f.getPosition().getSpalte() == spalte){
                        kollisionGefunden = true;
                        kollidierteFigur = f;
                        break;
                    } 
                }

                if(kollisionGefunden){
                    System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                        moeglicheZuege.add(schachbrett.getFeld(spalte, zeilenNummer));
                    }
                    break;
                } else {
                    moeglicheZuege.add(schachbrett.getFeld(spalte, zeilenNummer));
                }
            }


        } else if(zeile > 1 && zeile < 8){
            //Felder 1 bis (x-1) und (x+1) bis 8
            System.out.println("SPALTE 2-7");

            //Felder 1 bis (x-1) und (x+1) bis 8
            System.out.println("SPALTE 2-7");

            //Felder von 1 bis (x-1)
            for(int zeilenNummer = (zeile-1); zeilenNummer >= 1; zeilenNummer--){
                boolean kollisionGefunden = false;
                Figur kollidierteFigur = null;

                for(Figur f : figuren){
                    if(f.getPosition().getZeile() == zeilenNummer && f.getPosition().getSpalte() == spalte){
                        kollisionGefunden = true;
                        kollidierteFigur = f;
                        break;
                    } 
                }

                if(kollisionGefunden){
                    System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                        moeglicheZuege.add(schachbrett.getFeld(spalte, zeilenNummer));
                    }
                    break;
                } else {
                    moeglicheZuege.add(schachbrett.getFeld(spalte, zeilenNummer));
                }
            }

            //Felder von (x+1) bis 8
            for(int zeilenNummer = (zeile+1); zeilenNummer <= 8; zeilenNummer++){
                boolean kollisionGefunden = false;
                Figur kollidierteFigur = null;

                for(Figur f : figuren){
                    if(f.getPosition().getZeile() == zeilenNummer && f.getPosition().getSpalte() == spalte){
                        kollisionGefunden = true;
                        kollidierteFigur = f;
                        break;
                    } 
                }

                if(kollisionGefunden){
                    System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                        moeglicheZuege.add(schachbrett.getFeld(spalte, zeilenNummer));
                    }
                    break;
                } else {
                    moeglicheZuege.add(schachbrett.getFeld(spalte, zeilenNummer));
                }
            }     

        } else if(zeile == 8){
            //Felder 1 bis 7 sind möglich
            System.out.println("SPALTE 8");

            for(int zeilenNummer = 7; zeilenNummer >= 1; zeilenNummer--){
                boolean kollisionGefunden = false;
                Figur kollidierteFigur = null;

                for(Figur f : figuren){
                    if(f.getPosition().getZeile() == zeilenNummer && f.getPosition().getSpalte() == spalte){
                        kollisionGefunden = true;
                        kollidierteFigur = f;
                        break;
                    } 
                }

                if(kollisionGefunden){
                    System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                        moeglicheZuege.add(schachbrett.getFeld(spalte, zeilenNummer));
                    }
                    break;
                } else {
                    moeglicheZuege.add(schachbrett.getFeld(spalte, zeilenNummer));
                }
            }
        }

        return moeglicheZuege;
    }
}
