public class Dame extends Figur{
    int spalte = -1;
    int zeile = -1;

    public Dame(int farbe, Feld startPosition){
        
        super(farbe, startPosition);
        
        this.setName("Dame");
        this.setAbkuerzung("D");
        this.setWert(9);
    }

    @Override
    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett){
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        
        this.spalte = this.getPosition().getSpalte();
        this.zeile = this.getPosition().getZeile();

        /*
            Die Figur Dame kann folgende Bewegungen ausführen
                - diagonale Bewegungen nach vorne links
                - diagonale Bewegungen nach vorne rechts
                - diagonale Bewegungen nach hinten links
                - diagonale Bewegungen nach hinten rechts
                - gerade Bewegungen in der Zeile
                - gerade Bewegungen in der Spalte
        */

        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.OBEN_RECHTS, Bewegungsmatrizen.OBEN_RECHTS, figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.OBEN_LINKS, Bewegungsmatrizen.OBEN_LINKS, figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.UNTEN_RECHTS, Bewegungsmatrizen.UNTEN_RECHTS, figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.UNTEN_LINKS, Bewegungsmatrizen.UNTEN_LINKS, figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.OBEN, Bewegungsmatrizen.OBEN, figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.UNTEN, Bewegungsmatrizen.UNTEN, figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.LINKS, Bewegungsmatrizen.LINKS, figuren, schachbrett));
        moeglicheZuege.addAll(moeglicheZuegeRichtung(Bewegungsrichtung.RECHTS, Bewegungsmatrizen.RECHTS, figuren, schachbrett));

        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicheZuegeRichtung(Bewegungsrichtung richtung, Bewegungsmatrizen matrix, ArrayList<Figur> figuren, Schachbrett schachbrett){
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        int platzhalter = matrix.erhalteMatrizenWert(spalte, zeile);

        if ((spalte == richtung.spaltenVerbot() && richtung.zeilenVerbot() != 0) || (richtung.zeilenVerbot() != 0 && zeile == richtung.zeilenVerbot())) {
            return moeglicheZuege;
        }

        int naechsteSpalte = spalte + richtung.spaltenVerschiebung();
        int naechsteZeile = zeile + richtung.zeilenVerschiebung();

        return ermittleMoeglicheZuege(richtung, platzhalter, naechsteSpalte, naechsteZeile, figuren, schachbrett);
    }
}
