public class Koenig extends Figur{

    private boolean imSchach = false;
    private boolean schachMatt = false;

    public Koenig(int farbe, Feld startPosition){
        
        super(farbe, startPosition);
        
        this.setName("König");
        this.setAbkuerzung("K");
    }

    public boolean istImSchach() {
        return true;
        // Implementierung der Überprüfung, ob der König im Schach ist
    }
}
