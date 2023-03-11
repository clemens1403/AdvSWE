public class Laeufer extends Figur{
    public Laeufer(){

        this.name = "Läufer";
        this.abkuerzung = "L";
        this.wert = 3;

    }

    public boolean sindZwischenfelderFrei(){
        //Hier muss die Überprüfungsroutine eingebaut werden, ob der Läufer ziehen kann
        return false;
    }
}
