public class Dame extends Figur{
    public Dame(int farbe, Feld startPosition){
        
        super(farbe, startPosition);
        
        this.setName("Dame");
        this.setAbkuerzung("D");
        this.setWert(9);
        
    }

    public boolean sindZwischenfelderFrei(){
        //Hier muss die Überprüfungsroutine eingebaut werden, ob der Läufer ziehen kann
        return false;
    }
}
