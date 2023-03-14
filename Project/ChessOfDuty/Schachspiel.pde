public class Schachspiel {
    private Schachbrett schachbrett;
    //private Figur[] spielfiguren = new ArrayList<Figur>;
    
    public Schachspiel(){
        
        //Schachbrett bestehend aus 8x8 Feldern wird instanziiert
        this.schachbrett = new Schachbrett();
        
        //Figuren des Schachspiels werden instanziiert
        //Bauer weißerBauer1 = new Bauer
    }

    public Schachbrett getSchachbrett(){
        return this.schachbrett;
    }

    /*public Figur[] getSpielfiguren(){
        return this.spielfiguren;
    }*/


}
