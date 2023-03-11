public class Laeufer extends Figur{
    public Laeufer(int farbe, Feld startPosition, Bewegungsmuster muster){
        super(farbe, startPosition, muster);
        this.setName("Läufer");
        this.setAbkuerzung("L");
        this.setWert(3);

    }

    public boolean sindZwischenfelderFrei(){
        //Hier muss die Überprüfungsroutine eingebaut werden, ob der Läufer ziehen kann
        return false;
    }
}
