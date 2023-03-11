public class Turm extends Figur{
    public Turm(){

        this.name = "Turm";
        this.abkuerzung = "T";
        this.wert = 5;
        
    }

    public boolean sindZwischenfelderFrei(){
        //Hier muss die Überprüfungsroutine eingebaut werden, ob der Läufer ziehen kann
        return false;
    }
    
}
