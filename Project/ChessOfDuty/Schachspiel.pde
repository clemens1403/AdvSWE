public class Schachspiel {
    private Schachbrett schachbrett;
    private ArrayList<Figur> figuren = new ArrayList<Figur>();
    private int spielerAmZug;
    
    public Schachspiel(){
        
        //Schachbrett bestehend aus 8x8 Feldern wird instanziiert
        this.schachbrett = new Schachbrett();
        this.spielerAmZug = 1;

        
        //Figuren des Schachspiels werden instanziiert
        this.initialisiereFiguren();


    }

    public Schachbrett getSchachbrett(){
        return this.schachbrett;
    }

    public void initialisiereFiguren(){

        for(int i = 1; i<=8; i++){
            figuren.add(new Bauer(1, schachbrett.getFeld(i, 2)));
            figuren.add(new Bauer(0, schachbrett.getFeld(i, 7)));
        }

        figuren.add(new Koenig(1, schachbrett.getFeld(5,1)));
        figuren.add(new Koenig(0, schachbrett.getFeld(5,8)));

        figuren.add(new Dame(1, schachbrett.getFeld(4,1)));
        figuren.add(new Dame(0, schachbrett.getFeld(4,8)));

        figuren.add(new Laeufer(1, schachbrett.getFeld(3,1)));
        figuren.add(new Laeufer(0, schachbrett.getFeld(3,8)));
        figuren.add(new Laeufer(1, schachbrett.getFeld(6,1)));
        figuren.add(new Laeufer(0, schachbrett.getFeld(6,8)));

        figuren.add(new Springer(1, schachbrett.getFeld(2,1)));
        figuren.add(new Springer(0, schachbrett.getFeld(2,8)));
        figuren.add(new Springer(1, schachbrett.getFeld(7,1)));
        figuren.add(new Springer(0, schachbrett.getFeld(7,8)));

        figuren.add(new Turm(1, schachbrett.getFeld(1,1)));
        figuren.add(new Turm(0, schachbrett.getFeld(1,8)));
        figuren.add(new Turm(1, schachbrett.getFeld(8,1)));
        figuren.add(new Turm(0, schachbrett.getFeld(8,8)));
    }

    public void renderSchachspiel(){
        schachbrett.renderSchachbrett();
        for(Figur f : figuren){
            f.render();
        }
    }

    public void checkKlicks(){
        for(Figur f : figuren){
            if(f.checkFigurGeklickt()) {
                print(f.getAbkuerzung());
                print(f.getFarbe()); 
                print("Zeile: " + f.getPosition().getSpalte());
                print("Spalte: " + f.getPosition().getZeile());
                if(f.getFarbe() == this.spielerAmZug){
                    //print("Show possible moves");
                    f.getMoeglicheZuege(figuren);
                }
            }   
        }
    }


}
