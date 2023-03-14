public class Schachspiel {
    private Schachbrett schachbrett;
    private Figur[] spielfiguren;
    
    public Schachspiel(){
        
        this.schachbrett = new Schachbrett();
        //hier müssen die Figuren des Schachspiel instanziiert werden
    }

    public Schachbrett getSchachbrett(){
        return this.schachbrett;
    }

    public Figur[] getSpielfiguren(){
        return this.spielfiguren;
    }


}
