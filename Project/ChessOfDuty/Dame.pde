public class Dame extends Figur{
    public Dame(int farbe, Feld startPosition, Bewegungsmuster muster){
        super(farbe, startPosition, muster);
        this.setName("Dame");
        this.setAbkuerzung("D");
        this.setWert(9);
        
    }

    public boolean sindZwischenfelderFrei(){
        //Hier muss die Überprüfungsroutine eingebaut werden, ob der Läufer ziehen kann
        return false;
    }
}
