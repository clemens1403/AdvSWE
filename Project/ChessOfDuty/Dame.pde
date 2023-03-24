public class Dame extends Figur{
    public Dame(int farbe, Feld startPosition){
        
        super(farbe, startPosition);
        
        this.setName("Dame");
        this.setAbkuerzung("D");
        this.setWert(9);
    }

    public boolean sindZwischenfelderFrei(){
        //Hier muss die Überprüfungsroutine eingebaut werden, ob der Läufer ziehen kann
        return false;
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


        for (Feld eintrag : moeglicheZuege) {
            //System.out.println("(" + eintrag.getSpalte() + ", " + eintrag.getZeile() + ")");
        }

        return moeglicheZuege;

    }

    private ArrayList<Feld> moeglicheZeugeNachVorneRechts(ArrayList<Figur> figuren, Schachbrett schachbrett){

        print("Ping 1");

        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

        int spaltenNummer = spalte+1;
        int zeilenNummer = zeile+1;

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 8 || zeile == 8){
            return moeglicheZuege;
        } 

        for(int i = (spalte+1); i <= 8; i++){
            
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

        print("Ping 2");

        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

        int spaltenNummer = spalte-1;
        int zeilenNummer = zeile+1;

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 1 || zeile == 8){
            return moeglicheZuege;
        } 

        for(int i = (spalte-1); i >= 1; i--){
            
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
        //ALARM HIER IST NOCH EIN FEHLER
        print("Ping 3");

        int platzhalter = 0;

        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

        int spaltenNummer = spalte-1;
        int zeilenNummer = zeile-1;

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 1 || zeile == 1){
            return moeglicheZuege;
        } 

        //Das hier könnte acutally ziemlich smart sein
        //Nein, ist doch stupid... bitte überarbeiten
        if(spalte <= 4 && zeile >= 4){
            platzhalter = spalte -1 ;
        } else {
            platzhalter = zeile - 1;
        }

        for(int i = 1; i < platzhalter; i++){
            
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

        print("Ping 4");

        int platzhalter = 0;

        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

        int spaltenNummer = spalte+1;
        int zeilenNummer = zeile-1;

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 8 || zeile == 1){
            return moeglicheZuege;
        } 

        platzhalter = getAnzahlMoeglicherBewegungen(spalte, zeile);
        System.out.println("Platzhalter: " + platzhalter);


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
        
        print("Ping 5");

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        
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
        
        print("Ping 6");

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

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

    public int getAnzahlMoeglicherBewegungen(int spalte, int zeile){

        /*int wert = 0;

        // Alle Fälle abdecken, in denen wert nicht 0 ist
        if (zeile == 2 && spalte >= 1 && spalte <= 7) {
            wert = 1;
        } else if (zeile == 3 && spalte >= 1 && spalte <= 7) {
            wert = (spalte == 7) ? 1 : 2;
        } else if (zeile == 4 && spalte >= 1 && spalte <= 8) {
            wert = (spalte == 8) ? 0 : ((spalte == 6) ? 2 : ((spalte == 7) ? 1 : 3));
        } else if (zeile == 5 && spalte >= 1 && spalte <= 8) {
            wert = (spalte == 8) ? 0 : ((spalte == 5) ? 3 : ((spalte == 6) ? 2 : ((spalte == 7) ? 1 : 4)));
        } else if (zeile == 6 && spalte >= 1 && spalte <= 8) {
            wert = (spalte == 8) ? 0 : ((spalte == 4) ? 4 : ((spalte == 5) ? 3 : ((spalte == 6) ? 2 : ((spalte == 7) ? 1 : 5))));
        } else if (zeile == 7 && spalte >= 1 && spalte <= 8) {
            wert = (spalte == 8) ? 0 : ((spalte == 3) ? 5 : ((spalte == 4) ? 4 : ((spalte == 5) ? 3 : ((spalte == 6) ? 2 : ((spalte == 7) ? 1 : 6)))));
        } else if (zeile == 8 && spalte >= 1 && spalte <= 8) {
            wert = 8 - spalte;
        }

        return wert;*/

        int[][] wertematrix = {
                        {0, 1, 2, 3, 4, 5, 6, 7},
                        {0, 1, 2, 3, 4, 5, 6, 6},
                        {0, 1, 2, 3, 4, 5, 5, 5},
                        {0, 1, 2, 3, 4, 4, 4, 4},
                        {0, 1, 2, 3, 3, 3, 3, 3},
                        {0, 1, 2, 2, 2, 2, 2, 2},
                        {0, 1, 1, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0}};

        return wertematrix[spalte-1][zeile-1];
    }

}
