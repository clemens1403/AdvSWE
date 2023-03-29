public class Laeufer extends Figur{
    public Laeufer(int farbe, Feld startPosition){
        
        super(farbe, startPosition);
        
        this.setName("Läufer");
        this.setAbkuerzung("L");
        this.setWert(3);
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
        moeglicheZuege.addAll(moeglicheZuegeNachVorneRechts(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachVorneLinks(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachHintenLinks(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachHintenRechts(figuren, schachbrett));

        for (Feld eintrag : moeglicheZuege) {
            System.out.println("(" + eintrag.getSpalte() + ", " + eintrag.getZeile() + ")");
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicheZuegeNachVorneRechts(ArrayList<Figur> figuren, Schachbrett schachbrett){
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
}
