public class Laeufer extends Figur{
    public Laeufer(int farbe, Feld startPosition){
        
        super(farbe, startPosition);
        
        this.setName("Läufer");
        this.setAbkuerzung("L");
        this.setWert(3);
    }

    public boolean sindZwischenfelderFrei(){
        //Hier muss die Überprüfungsroutine eingebaut werden, ob der Läufer ziehen kann
        return false;
    }

    @Override
    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        /*
            Die Figur Läufer kann folgende Bewegungen ausführen
                - diagonale Bewegungen nach vorne links
                - diagonale Bewegungen nach vorne rechts
                - diagonale Bewegungen nach hinten links
                - diagonale Bewegungen nach hinten rechts
        */

        //Erstelle alle möglichen Züge, wenn der Läufer am linken Rand steht
        moeglicheZuege.addAll(moeglicheZeugeNachVorneRechts(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachVorneLinks(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachHintenLinks(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachHintenRechts(figuren, schachbrett));

        for (Feld eintrag : moeglicheZuege) {
            System.out.println("(" + eintrag.getSpalte() + ", " + eintrag.getZeile() + ")");
        }

        return moeglicheZuege;

    }

    private ArrayList<Feld> moeglicheZeugeNachVorneRechts(ArrayList<Figur> figuren, Schachbrett schachbrett){

        int platzhalter = 0;

        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

        int spaltenNummer = spalte+1;
        int zeilenNummer = zeile+1;

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 8 || zeile == 8){
            return moeglicheZuege;
        } 

        platzhalter = bekommeMatrixWerte(spalte, zeile, "vorneRechts");

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
                System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                if(this.getFarbe() == kollidierteFigur.getFarbe()){
                    System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                } else{
                    System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
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

        int platzhalter = 0;

        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

        int spaltenNummer = spalte-1;
        int zeilenNummer = zeile+1;

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 1 || zeile == 8){
            return moeglicheZuege;
        } 

        platzhalter = bekommeMatrixWerte(spalte, zeile, "vorneLinks");

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
                System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                if(this.getFarbe() == kollidierteFigur.getFarbe()){
                    System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                } else{
                    System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
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

        int platzhalter = 0;

        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

        int spaltenNummer = spalte-1;
        int zeilenNummer = zeile-1;

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 1 || zeile == 1){
            return moeglicheZuege;
        } 

        platzhalter = bekommeMatrixWerte(spalte, zeile, "hintenLinks");

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
                System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                if(this.getFarbe() == kollidierteFigur.getFarbe()){
                    System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                } else{
                    System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
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

        int platzhalter = 0;

        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

        int spaltenNummer = spalte+1;
        int zeilenNummer = zeile-1;

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 8 || zeile == 1){
            return moeglicheZuege;
        } 

        platzhalter = bekommeMatrixWerte(spalte, zeile, "hintenRechts");

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
                System.out.println("Auf diesem Feld wurde eine Kollision festgestellt");

                if(this.getFarbe() == kollidierteFigur.getFarbe()){
                    System.out.println("Die erkannte Kollision ist mit einer Figur der gleichen Farbe");
                } else{
                    System.out.println("Die erkannte Kollision ist mit einer Figur der anderen Farbe");
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
