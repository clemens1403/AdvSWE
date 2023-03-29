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
        moeglicheZuege.addAll(moeglicheZuegeNachVorne(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachHinten(figuren,schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachLinks(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachRechts(figuren,schachbrett));

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicheZeugeNachVorneRechts(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = bekommeMatrixWerte(spalte, zeile, Bewegungsrichtung.OBEN_RECHTS);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 8 || zeile == 8){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.OBEN_RECHTS, platzhalter, spalte+1, zeile+1, figuren, schachbrett);
    }

    private ArrayList<Feld> moeglicheZuegeNachVorneLinks(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = bekommeMatrixWerte(spalte, zeile, Bewegungsrichtung.OBEN_LINKS);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 1 || zeile == 8){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.OBEN_LINKS, platzhalter, spalte-1, zeile+1, figuren, schachbrett);
    }

    private ArrayList<Feld> moeglicheZuegeNachHintenLinks(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = bekommeMatrixWerte(spalte, zeile, Bewegungsrichtung.UNTEN_LINKS);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();   

        if(spalte == 1 || zeile == 1){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.UNTEN_LINKS, platzhalter, spalte-1, zeile-1, figuren, schachbrett);
    }

    private ArrayList<Feld> moeglicheZuegeNachHintenRechts(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = bekommeMatrixWerte(spalte, zeile, Bewegungsrichtung.UNTEN_RECHTS);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 8 || zeile == 1){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.UNTEN_RECHTS, platzhalter, spalte+1, zeile-1, figuren, schachbrett);
    }

    private ArrayList<Feld> moeglicheZuegeNachVorne(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = bekommeMatrixWerte(spalte, zeile, Bewegungsrichtung.OBEN);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 8){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.OBEN, platzhalter, spalte, zeile+1, figuren, schachbrett);
    }

    private ArrayList<Feld> moeglicheZuegeNachHinten(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = bekommeMatrixWerte(spalte, zeile, Bewegungsrichtung.UNTEN);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 1){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.UNTEN, platzhalter, spalte, zeile-1, figuren, schachbrett);
    }

    private ArrayList<Feld> moeglicheZuegeNachLinks(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = bekommeMatrixWerte(spalte, zeile, Bewegungsrichtung.LINKS);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 1){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.LINKS, platzhalter, spalte-1, zeile, figuren, schachbrett);
    }

        private ArrayList<Feld> moeglicheZuegeNachRechts(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = bekommeMatrixWerte(spalte, zeile, Bewegungsrichtung.RECHTS);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 8){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.RECHTS, platzhalter, spalte+1, zeile, figuren, schachbrett);
    }

    private int bekommeMatrixWerte(int spalte, int zeile, Bewegungsrichtung bewegungsRichtung){

        int[][] wertematrix = null;

        switch(bewegungsRichtung){
            case UNTEN_RECHTS:
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
            case UNTEN_LINKS:
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
            case OBEN_RECHTS:
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
            case OBEN_LINKS:
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
            case LINKS:
                wertematrix = new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1},
                    {2, 2, 2, 2, 2, 2, 2, 2},
                    {3, 3, 3, 3, 3, 3, 3, 3},
                    {4, 4, 4, 4, 4, 4, 4, 4},
                    {5, 5, 5, 5, 5, 5, 5, 5},
                    {6, 6, 6, 6, 6, 6, 6, 6},
                    {7, 7, 7, 7, 7, 7, 7, 7}};
                break;
            case RECHTS:
                wertematrix = new int[][]{
                    {7, 7, 7, 7, 7, 7, 7, 7},
                    {6, 6, 6, 6, 6, 6, 6, 6},
                    {5, 5, 5, 5, 5, 5, 5, 5},
                    {4, 4, 4, 4, 4, 4, 4, 4},
                    {3, 3, 3, 3, 3, 3, 3, 3},
                    {2, 2, 2, 2, 2, 2, 2, 2},
                    {1, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0}};
                break;
            case UNTEN:
                wertematrix = new int[][]{
                    {0, 1, 2, 3, 4, 5, 6, 7},
                    {0, 1, 2, 3, 4, 5, 6, 7},
                    {0, 1, 2, 3, 4, 5, 6, 7},
                    {0, 1, 2, 3, 4, 5, 6, 7},
                    {0, 1, 2, 3, 4, 5, 6, 7},
                    {0, 1, 2, 3, 4, 5, 6, 7},
                    {0, 1, 2, 3, 4, 5, 6, 7},
                    {0, 1, 2, 3, 4, 5, 6, 7}};
                break;
            case OBEN:
                wertematrix = new int[][]{
                    {7, 6, 5, 4, 3, 2, 1, 0},
                    {7, 6, 5, 4, 3, 2, 1, 0},
                    {7, 6, 5, 4, 3, 2, 1, 0},
                    {7, 6, 5, 4, 3, 2, 1, 0},
                    {7, 6, 5, 4, 3, 2, 1, 0},
                    {7, 6, 5, 4, 3, 2, 1, 0},
                    {7, 6, 5, 4, 3, 2, 1, 0},
                    {7, 6, 5, 4, 3, 2, 1, 0}};
                break;     
        }

        
        return wertematrix[spalte-1][zeile-1];
    }

    private ArrayList<Feld> ermittleMoeglicheZuege(Bewegungsrichtung bewegungsRichtung, int beweglicheFelder, int spalte, int zeile, ArrayList<Figur> figuren, Schachbrett schachbrett){
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        for(int i = 1; i <= beweglicheFelder; i++){
            Figur kollidierteFigur = findeKollision(figuren, spalte, zeile);

            if(kollidierteFigur != null){
                if(this.getFarbe() != kollidierteFigur.getFarbe()){
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

    private Figur findeKollision(ArrayList<Figur> figuren, int spalte, int zeile){
        Figur kollidierteFigur = null;
        
        for(Figur f : figuren){
            if(f.getPosition().getSpalte() == spalte && f.getPosition().getZeile() == zeile){
                kollidierteFigur = f;
                break;
            } 
        }

        return kollidierteFigur;
    }
}
