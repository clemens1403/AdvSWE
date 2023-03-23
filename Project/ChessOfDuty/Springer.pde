public class Springer extends Figur{
    public Springer(int farbe, Feld startPosition){
        
        super(farbe, startPosition);
        
        this.setName("Springer");
        this.setAbkuerzung("S");
        this.setWert(3);
    }

    @Override
    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        
        /*
            Die Figur Springer kann folgenden Bewegungen ausführen
                - zwei Schritte nach vorne und einen nach links
                - zwei Schritte nach vorne und einen nach rechts
                - zwei Schritte nach rechts und einen nach oben
                - zwei Schritte nach rechts und einen nach unten
                - zwei Schritte nach unten und einen nach rechts
                - zwei Schritte nach unten und einen nach links
                - zwei Schritte nach links und einen nach unten
                - zwei Schritte nach links und einen nach oben
        */

        moeglicheZuege.addAll(zweiNachVorneEinsNachLinks(figuren, schachbrett, spalte, zeile));
        moeglicheZuege.addAll(zweiNachVorneEinsNachRechts(figuren, schachbrett, spalte, zeile));
        moeglicheZuege.addAll(zweiNachRechtsEinesNachOben(figuren, schachbrett, spalte, zeile));
        moeglicheZuege.addAll(zweiNachRechtsEinesNachUnten(figuren, schachbrett, spalte, zeile));
        moeglicheZuege.addAll(zweiNachUntenEinesNachRechts(figuren, schachbrett, spalte, zeile));
        moeglicheZuege.addAll(zweiNachUntenEinesNachLinks(figuren,schachbrett, spalte, zeile));
        moeglicheZuege.addAll(zweiNachLinksEinesNachUnten(figuren, schachbrett, spalte, zeile));
        moeglicheZuege.addAll(zweiNachLinksEinensNachOben(figuren,schachbrett, spalte, zeile));

        for (Feld eintrag : moeglicheZuege) {
            System.out.println("(" + eintrag.getSpalte() + ", " + eintrag.getZeile() + ")");
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> zweiNachVorneEinsNachLinks(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;

        if(zeile >= 7 || spalte == 1) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == (zeile+2) && f.getPosition().getSpalte() == spalte-1){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile+2));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile+2));
        }

        return moeglicheZuege;
    }

    private ArrayList<Feld> zweiNachVorneEinsNachRechts(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile){return new ArrayList<Feld>();}

    private ArrayList<Feld> zweiNachRechtsEinesNachOben(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile){return new ArrayList<Feld>();}

    private ArrayList<Feld> zweiNachRechtsEinesNachUnten(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile){return new ArrayList<Feld>();}

    private ArrayList<Feld> zweiNachUntenEinesNachRechts(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile){return new ArrayList<Feld>();}

    private ArrayList<Feld> zweiNachUntenEinesNachLinks(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile){return new ArrayList<Feld>();}

    private ArrayList<Feld> zweiNachLinksEinesNachUnten(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile){return new ArrayList<Feld>();}

    private ArrayList<Feld> zweiNachLinksEinensNachOben(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile){return new ArrayList<Feld>();}

}
