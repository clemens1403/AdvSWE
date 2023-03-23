public class Koenig extends Figur{

    private boolean imSchach = false;
    private boolean schachMatt = false;

    public Koenig(int farbe, Feld startPosition){
        
        super(farbe, startPosition);
        
        this.setName("König");
        this.setAbkuerzung("K");
    }

    public boolean istImSchach() {
        return true;
        // Implementierung der Überprüfung, ob der König im Schach ist
    }

    @Override
    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett){

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

        /*
        Die Figur König kann folgende Bewegungen ausführen
        - einen Schritt nach vorne
        - einen Schritt nach vorne links
        - einen Schritt nach vorne rechts
        - einen Schritt nach hinten
        - einen Schritt nach hinten links
        - einen Schritt nach hinten rechts
        - einen Schritt nach links
        - einen Schritt nach rechts
        */
        moeglicheZuege.addAll(moeglicherZugNachVorne(figuren, schachbrett, spalte, zeile));
        moeglicheZuege.addAll(moeglicheZuegeNachVorneLinks(figuren, schachbrett, spalte, zeile));
        moeglicheZuege.addAll(moeglicherZugNachVorneRechts(figuren, schachbrett, spalte, zeile));
        moeglicheZuege.addAll(moeglicherZugNachHinten(figuren, schachbrett, spalte, zeile));
        moeglicheZuege.addAll(moeglicherZugNachHintenLinks(figuren, schachbrett, spalte, zeile));
        moeglicheZuege.addAll(moeglicherZugNachHintenRechts(figuren,schachbrett, spalte, zeile));
        moeglicheZuege.addAll(moeglicherZugNachRechts(figuren, schachbrett, spalte, zeile));
        moeglicheZuege.addAll(moeglicherZugNachLinks(figuren,schachbrett, spalte, zeile));


        for (Feld eintrag : moeglicheZuege) {
            System.out.println("(" + eintrag.getSpalte() + ", " + eintrag.getZeile() + ")");
        }

        return moeglicheZuege;

    }

    private ArrayList<Feld> moeglicherZugNachVorne(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile) {

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 8) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == (zeile+1) && f.getPosition().getSpalte() == spalte){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte, zeile+1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte, zeile+1));
        }
           
        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicheZuegeNachVorneLinks(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile){

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 8 || spalte == 1) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == zeile+1 && f.getPosition().getSpalte() == spalte-1){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile+1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile+1));
        }
           
        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicherZugNachVorneRechts(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile){

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 8 || spalte == 8) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == zeile+1 && f.getPosition().getSpalte() == spalte+1){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile+1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile+1));
        }
           
        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicherZugNachHinten(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile){

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 1) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == zeile-1 && f.getPosition().getSpalte() == spalte){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte, zeile-1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte, zeile-1));
        }
           
        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicherZugNachHintenLinks(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile){

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 1 || spalte == 1) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == zeile-1 && f.getPosition().getSpalte() == spalte-1){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile-1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile-1));
        }
           
        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicherZugNachHintenRechts(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile) {
        
        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(zeile == 1 || spalte == 8) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == zeile-1 && f.getPosition().getSpalte() == spalte+1){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile-1));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile-1));
        }
           
        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicherZugNachRechts(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile){

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 8) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == zeile && f.getPosition().getSpalte() == spalte+1){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte+1, zeile));
        }
           
        return moeglicheZuege;
    }

    private ArrayList<Feld> moeglicherZugNachLinks(ArrayList<Figur> figuren, Schachbrett schachbrett, int spalte, int zeile){

        boolean kollisionGefunden = false;
        Figur kollidierteFigur = null;
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        if(spalte == 1) return moeglicheZuege;

        for(Figur f : figuren){
            if(f.getPosition().getZeile() == zeile && f.getPosition().getSpalte() == spalte-1){
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
                moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile));
            }
        } else {
            moeglicheZuege.add(schachbrett.getFeld(spalte-1, zeile));
        }
           
        return moeglicheZuege;
    }
}
