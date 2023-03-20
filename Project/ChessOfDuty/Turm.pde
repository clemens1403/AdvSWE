public class Turm extends Figur{
    public Turm(int farbe, Feld startPosition){
        
        super(farbe, startPosition);
        
        this.setName("Turm");
        this.setAbkuerzung("T");
        this.setWert(5);
    }

    public boolean sindZwischenfelderFrei(){
        //Hier muss die Überprüfungsroutine eingebaut werden, ob der Läufer ziehen kann
        return false;
    }

    @Override
    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren){

        ArrayList<SimpleEntry<Integer, Integer>> moeglicheZuege = new ArrayList<>();
        /*
            Die Figur Turm kann folgende Bewegungen ausführen:
                - alle Felder in der aktuellen Spalte
                - alle Felder in der aktuellen Zeile
        */

        moeglicheZuege.addAll(getBewegungInZeile());
        moeglicheZuege.addAll(getBewegungInSpalte());

        for (SimpleEntry<Integer, Integer> eintrag : moeglicheZuege) {
            System.out.println("(" + eintrag.getKey() + ", " + eintrag.getValue() + ")");
        }

        for(Figur f : figuren){
            for(SimpleEntry<Integer, Integer> moeglicherZug : moeglicheZuege) {
                if(f.getPosition().getSpalte() == )
            }
            

            /*if(f.getPosition().getSpalte() == spalte){
                if(f.getPosition().getZeile() != zeile){
                    System.out.println(f);
                }
            }*/
        }



        return new ArrayList<Feld>();
    }

    private ArrayList<SimpleEntry<Integer, Integer>> getBewegungInZeile(){
        
        ArrayList<SimpleEntry<Integer, Integer>> moeglicheZuege = new ArrayList<>();
        
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();
        
        //Erstelle alle möglichen Züge für Spalte
        if(spalte == 1){
            //Felder 2 bis 8 sind möglich
            System.out.println("SPALTE 1");
            moeglicheZuege.add(new SimpleEntry<>(2, zeile));
            moeglicheZuege.add(new SimpleEntry<>(3, zeile));
            moeglicheZuege.add(new SimpleEntry<>(4, zeile));
            moeglicheZuege.add(new SimpleEntry<>(5, zeile));
            moeglicheZuege.add(new SimpleEntry<>(6, zeile));
            moeglicheZuege.add(new SimpleEntry<>(7, zeile));
            moeglicheZuege.add(new SimpleEntry<>(8, zeile));

        } else if(spalte > 1 && spalte < 8){
            //Felder 1 bis (x-1) und (x+1) bis 8
            System.out.println("SPALTE 2-7");
            for(int i = 1; i < spalte; i++){
                moeglicheZuege.add(new SimpleEntry<>(i, zeile));
            }

            for(int i = spalte + 1; i <= 8; i++){
                moeglicheZuege.add(new SimpleEntry<>(i, zeile));
            }

        } else if(spalte == 8){
            //Felder 1 bis 7 sind möglich
            System.out.println("SPALTE 8");
            moeglicheZuege.add(new SimpleEntry<>(1, zeile));
            moeglicheZuege.add(new SimpleEntry<>(2, zeile));
            moeglicheZuege.add(new SimpleEntry<>(3, zeile));
            moeglicheZuege.add(new SimpleEntry<>(4, zeile));
            moeglicheZuege.add(new SimpleEntry<>(5, zeile));
            moeglicheZuege.add(new SimpleEntry<>(6, zeile));
            moeglicheZuege.add(new SimpleEntry<>(7, zeile));
        }

        return moeglicheZuege;
    } 

    private ArrayList<SimpleEntry<Integer, Integer>> getBewegungInSpalte() {
        
        ArrayList<SimpleEntry<Integer, Integer>> moeglicheZuege = new ArrayList<>();
        
        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

        //Erstelle alle möglichen Züge für Spalte
        if(zeile == 1){
            //Felder 2 bis 8 sind möglich
            System.out.println("SPALTE 1");
            moeglicheZuege.add(new SimpleEntry<>(spalte, 2));
            moeglicheZuege.add(new SimpleEntry<>(spalte, 3));
            moeglicheZuege.add(new SimpleEntry<>(spalte, 4));
            moeglicheZuege.add(new SimpleEntry<>(spalte, 5));
            moeglicheZuege.add(new SimpleEntry<>(spalte, 6));
            moeglicheZuege.add(new SimpleEntry<>(spalte, 7));
            moeglicheZuege.add(new SimpleEntry<>(spalte, 8));

        } else if(zeile > 1 && zeile < 8){
            //Felder 1 bis (x-1) und (x+1) bis 8
            System.out.println("SPALTE 2-7");
            for(int i = 1; i < zeile; i++){
                moeglicheZuege.add(new SimpleEntry<>(spalte, i));
            }

            for(int i = zeile + 1; i <= 8; i++){
                moeglicheZuege.add(new SimpleEntry<>(spalte, i));
            }        

        } else if(zeile == 8){
            //Felder 1 bis 7 sind möglich
            System.out.println("SPALTE 8");#
            moeglicheZuege.add(new SimpleEntry<>(spalte, 1));
            moeglicheZuege.add(new SimpleEntry<>(spalte, 2));
            moeglicheZuege.add(new SimpleEntry<>(spalte, 3));
            moeglicheZuege.add(new SimpleEntry<>(spalte, 4));
            moeglicheZuege.add(new SimpleEntry<>(spalte, 5));
            moeglicheZuege.add(new SimpleEntry<>(spalte, 6));
            moeglicheZuege.add(new SimpleEntry<>(spalte, 7));
        }

        return moeglicheZuege;
    }
    
}
