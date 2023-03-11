public class Dame extends Figur{
    public Dame(){

        this.name = "Dame";
        this.abkuerzung = "D";
        this.wert = 9;
        
    }

    public boolean sindZwischenfelderFrei(){
        //Hier muss die Überprüfungsroutine eingebaut werden, ob der Läufer ziehen kann
        return false;
    }
}
