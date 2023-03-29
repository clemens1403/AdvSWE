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
        int platzhalter = Bewegungsmatrizen.OBEN_RECHTS.erhalteMatrizenWert(spalte, zeile);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 8 || zeile == 8){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.OBEN_RECHTS, platzhalter, spalte+1, zeile+1, figuren, schachbrett);
    }

    private ArrayList<Feld> moeglicheZuegeNachVorneLinks(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = Bewegungsmatrizen.OBEN_LINKS.erhalteMatrizenWert(spalte, zeile);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 1 || zeile == 8){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.OBEN_LINKS, platzhalter, spalte-1, zeile+1, figuren, schachbrett);
    }

    private ArrayList<Feld> moeglicheZuegeNachHintenLinks(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = Bewegungsmatrizen.UNTEN_LINKS.erhalteMatrizenWert(spalte, zeile);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();   

        if(spalte == 1 || zeile == 1){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.UNTEN_LINKS, platzhalter, spalte-1, zeile-1, figuren, schachbrett);
    }

    private ArrayList<Feld> moeglicheZuegeNachHintenRechts(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = Bewegungsmatrizen.UNTEN_RECHTS.erhalteMatrizenWert(spalte, zeile);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 8 || zeile == 1){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.UNTEN_RECHTS, platzhalter, spalte+1, zeile-1, figuren, schachbrett);
    }

    private ArrayList<Feld> moeglicheZuegeNachVorne(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = Bewegungsmatrizen.OBEN.erhalteMatrizenWert(spalte, zeile);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 8){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.OBEN, platzhalter, spalte, zeile+1, figuren, schachbrett);
    }

    private ArrayList<Feld> moeglicheZuegeNachHinten(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = Bewegungsmatrizen.UNTEN.erhalteMatrizenWert(spalte, zeile);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 1){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.UNTEN, platzhalter, spalte, zeile-1, figuren, schachbrett);
    }

    private ArrayList<Feld> moeglicheZuegeNachLinks(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = Bewegungsmatrizen.LINKS.erhalteMatrizenWert(spalte, zeile);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 1){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.LINKS, platzhalter, spalte-1, zeile, figuren, schachbrett);
    }

        private ArrayList<Feld> moeglicheZuegeNachRechts(ArrayList<Figur> figuren, Schachbrett schachbrett){
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        int platzhalter = Bewegungsmatrizen.RECHTS.erhalteMatrizenWert(spalte, zeile);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 8){
            return moeglicheZuege;
        } 

        return ermittleMoeglicheZuege(Bewegungsrichtung.RECHTS, platzhalter, spalte+1, zeile, figuren, schachbrett);
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
