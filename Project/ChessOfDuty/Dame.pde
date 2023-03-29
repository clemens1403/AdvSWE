//Ähm... also die Dame muss generell mal ordentlich überarbeitet werden, aber erstmal funktioniert es...

public class Dame extends Figur{
    public Dame(int farbe, Feld startPosition){
        
        super(farbe, startPosition);
        
        this.setName("Dame");
        this.setAbkuerzung("D");
        this.setWert(9);
    }

    @Override
    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        /*
            Die Figur Dame kann folgende Bewegungen ausführen
                - diagonale Bewegungen nach vorne links
                - diagonale Bewegungen nach vorne rechts
                - diagonale Bewegungen nach hinten links
                - diagonale Bewegungen nach hinten rechts
                - gerade Bewegungen in der Zeile
                - gerade Bewegungen in der Spalte
        */

        moeglicheZuege.addAll(moeglicheZeugeNachVorneRechts(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachVorneLinks(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachHintenLinks(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachHintenRechts(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeInDerZeile(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeInDerSpalte(figuren,schachbrett));

        return moeglicheZuege;

    }

    private ArrayList<Feld> moeglicheZeugeNachVorneRechts(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 8 || zeile == 8){
            return moeglicheZuege;
        } 

        int spaltenNummer = spalte+1;
        int zeilenNummer = zeile+1;
        int platzhalter = bekommeMatrixWerte(spalte, zeile, "vorneRechts");

        for(int i = 1; i <= platzhalter; i++){
            
            boolean kollisionGefunden = false;
            Figur kollidierteFigur = null;

            for(Figur f : figuren){
                if(f.getPosition().getZeile() == zeilenNummer && f.getPosition().getSpalte() == spaltenNummer){
                    kollisionGefunden = true;
                    kollidierteFigur = f;
                    break;
                } 
            }

            if(kollisionGefunden){
                //System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                if(this.getFarbe() == kollidierteFigur.getFarbe()){
                    //System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                } else{
                    //System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                    moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeilenNummer));
                }
                break;
            } else {
                moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeilenNummer));
            }

            spaltenNummer++;
            zeilenNummer++;
        }

        return moeglicheZuege;

    }

    private ArrayList<Feld> moeglicheZuegeNachVorneLinks(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 1 || zeile == 8){
            return moeglicheZuege;
        } 

        int spaltenNummer = spalte-1;
        int zeilenNummer = zeile+1;
        int platzhalter = bekommeMatrixWerte(spalte, zeile, "vorneLinks");

        for(int i = 1; i <= platzhalter; i++){
            
            boolean kollisionGefunden = false;
            Figur kollidierteFigur = null;

            for(Figur f : figuren){
                if(f.getPosition().getZeile() == zeilenNummer && f.getPosition().getSpalte() == spaltenNummer){
                    kollisionGefunden = true;
                    kollidierteFigur = f;
                    break;
                } 
            }

            if(kollisionGefunden){
                //System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                if(this.getFarbe() == kollidierteFigur.getFarbe()){
                    //System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                } else{
                    //System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                    moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeilenNummer));
                }
                break;
            } else {
                moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeilenNummer));
            }

            spaltenNummer--;
            zeilenNummer++;
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicheZuegeNachHintenLinks(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();   

        if(spalte == 1 || zeile == 1){
            return moeglicheZuege;
        } 

        int spaltenNummer = spalte-1;
        int zeilenNummer = zeile-1;
        int platzhalter = bekommeMatrixWerte(spalte, zeile, "hintenLinks");

        for(int i = 1; i <= platzhalter; i++){
            
            boolean kollisionGefunden = false;
            Figur kollidierteFigur = null;

            for(Figur f : figuren){
                if(f.getPosition().getZeile() == zeilenNummer && f.getPosition().getSpalte() == spaltenNummer){
                    kollisionGefunden = true;
                    kollidierteFigur = f;
                    break;
                } 
            }

            if(kollisionGefunden){
                //System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                if(this.getFarbe() == kollidierteFigur.getFarbe()){
                    //System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                } else{
                    //System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                    moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeilenNummer));
                }
                break;
            } else {
                moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeilenNummer));
            }

            spaltenNummer--;
            zeilenNummer--;
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicheZuegeNachHintenRechts(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 8 || zeile == 1){
            return moeglicheZuege;
        } 

        int spaltenNummer = spalte+1;
        int zeilenNummer = zeile-1;
        int platzhalter = bekommeMatrixWerte(spalte, zeile, "hintenRechts");

        for(int i = 1; i <= platzhalter; i++){
            
            boolean kollisionGefunden = false;
            Figur kollidierteFigur = null;

            for(Figur f : figuren){
                if(f.getPosition().getZeile() == zeilenNummer && f.getPosition().getSpalte() == spaltenNummer){
                    kollisionGefunden = true;
                    kollidierteFigur = f;
                    break;
                } 
            }

            if(kollisionGefunden){
                ////System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                if(this.getFarbe() == kollidierteFigur.getFarbe()){
                    ////System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                } else{
                    ////System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                    moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeilenNummer));
                }
                break;
            } else {
                moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeilenNummer));
            }

            spaltenNummer++;
            zeilenNummer--;
        }

        return moeglicheZuege;

    }

    private ArrayList<Feld> moeglicheZuegeInDerZeile(ArrayList<Figur> figuren, Schachbrett schachbrett){        
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        
        //Erstelle alle möglichen Züge innerhalb einer Zeile
        if(spalte == 1){
            //Felder 2 bis 8 sind möglich
            //System.out.println("SPALTE 1");

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
                    //System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                        moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeile));
                    }
                    break;
                } else {
                    moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeile));
                }
            }

        } else if(spalte > 1 && spalte < 8){
            //Felder 1 bis (x-1) und (x+1) bis 8
            //System.out.println("SPALTE 2-7");

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
                    //System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
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
                    //System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                        moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeile));
                    }
                    break;
                } else {
                    moeglicheZuege.add(schachbrett.getFeld(spaltenNummer, zeile));
                }
            }

        } else if(spalte == 8){
            //Felder 1 bis 7 sind möglich
            //System.out.println("SPALTE 8");

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
                    //System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
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

    private ArrayList<Feld> moeglicheZuegeInDerSpalte(ArrayList<Figur> figuren, Schachbrett schachbrett) {        
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        //Erstelle alle möglichen Züge innerhalb einer Spalte
        if(zeile == 1){
            //Felder 2 bis 8 sind möglich
            //System.out.println("SPALTE 1");

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
                    //System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                        moeglicheZuege.add(schachbrett.getFeld(spalte, zeilenNummer));
                    }
                    break;
                } else {
                    moeglicheZuege.add(schachbrett.getFeld(spalte, zeilenNummer));
                }
            }


        } else if(zeile > 1 && zeile < 8){
            //Felder 1 bis (x-1) und (x+1) bis 8
            //System.out.println("SPALTE 2-7");

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
                    //System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
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
                    //System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
                        moeglicheZuege.add(schachbrett.getFeld(spalte, zeilenNummer));
                    }
                    break;
                } else {
                    moeglicheZuege.add(schachbrett.getFeld(spalte, zeilenNummer));
                }
            }     

        } else if(zeile == 8){
            //Felder 1 bis 7 sind möglich
            //System.out.println("SPALTE 8");

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
                    //System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                    if(this.getFarbe() == kollidierteFigur.getFarbe()){
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                    } else{
                        //System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
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

    public int bekommeMatrixWerte(int spalte, int zeile, String bewegungsRichtung){

        int[][] wertematrix = null;

        switch(bewegungsRichtung){
            case "hintenRechts":
                wertematrix = new int[][]{
                    {0, 1, 2, 3, 4, 5, 6, 7},
                    {0, 1, 2, 3, 4, 5, 6, 6},
                    {0, 1, 2, 3, 4, 5, 5, 5},
                    {0, 1, 2, 3, 4, 4, 4, 4},
                    {0, 1, 2, 3, 3, 3, 3, 3},
                    {0, 1, 2, 2, 2, 2, 2, 2},
                    {0, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0}};
                break;
            case "hintenLinks":
                wertematrix = new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 1, 1},
                    {0, 1, 2, 2, 2, 2, 2, 2},
                    {0, 1, 2, 3, 3, 3, 3, 3},
                    {0, 1, 2, 3, 4, 4, 4, 4},
                    {0, 1, 2, 3, 4, 5, 5, 5},
                    {0, 1, 2, 3, 4, 5, 6, 6},
                    {0, 1, 2, 3, 4, 5, 6, 7}};
                break;
            case "vorneRechts":
                wertematrix = new int[][]{
                    {7, 6, 5, 4, 3, 2, 1, 0},
                    {6, 6, 5, 4, 3, 2, 1, 0},
                    {5, 5, 5, 4, 3, 2, 1, 0},
                    {4, 4, 4, 4, 3, 2, 1, 0},
                    {3, 3, 3, 3, 3, 2, 1, 0},
                    {2, 2, 2, 2, 2, 2, 1, 0},
                    {1, 1, 1, 1, 1, 1, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0}};
                break;
            case "vorneLinks":
                wertematrix = new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 0},
                    {2, 2, 2, 2, 2, 2, 1, 0},
                    {3, 3, 3, 3, 3, 2, 1, 0},
                    {4, 4, 4, 4, 3, 2, 1, 0},
                    {5, 5, 5, 4, 3, 2, 1, 0},
                    {6, 6, 5, 4, 3, 2, 1, 0},
                    {7, 6, 5, 4, 3, 2, 1, 0}};
                break;
            
        }

        
        return wertematrix[spalte-1][zeile-1];
    }
}
