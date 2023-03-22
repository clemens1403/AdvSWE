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
        //moeglicheZuege.addAll(moeglicheZuegeNachVorneLinks(figuren, schachbrett));
        //moeglicheZuege.addAll(moeglicheZeugeNachHintenLinks(figuren, schachbrett));
        //moeglicheZuege.addAll(moeglicheZuegeNachHintenRechts(figuren, schachbrett));

        for (Feld eintrag : moeglicheZuege) {
            System.out.println("(" + eintrag.getSpalte() + ", " + eintrag.getZeile() + ")");
        }

        return moeglicheZuege;

    }

    private ArrayList<Feld> moeglicheZeugeNachVorneRechts(ArrayList<Figur> figuren, Schachbrett schachbrett){

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
}
