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
    
}
