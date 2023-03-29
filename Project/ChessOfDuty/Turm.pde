public class Turm extends Figur{
    public Turm(int farbe, Feld startPosition){
        
        super(farbe, startPosition);
        
        this.setName("Turm");
        this.setAbkuerzung("T");
        this.setWert(5);
    }

    @Override
    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        /*
            Die Figur Turm kann folgende Bewegungen ausführen:
                - alle Felder in der aktuellen Spalte
                - alle Felder in der aktuellen Zeile
        */

        moeglicheZuege.addAll(moeglicheZuegeNachVorne(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachHinten(figuren,schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachLinks(figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeNachRechts(figuren,schachbrett));

        return moeglicheZuege;
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
}
