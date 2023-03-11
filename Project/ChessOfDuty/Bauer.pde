public class Bauer extends Figur{

    private boolean doppelschrittMoeglich = true;

    public Bauer(){
        
        this.name = "Bauer";
        this.wert = 1;

    }

    public void wirdUmgewandelt(){
        
    }

    public void istEnPassantMoeglich(){
        // Implementierung der En-Passant-Regel für den Bauern
    }

    public boolean istDoppelschrittMoeglich(){
        return this.doppelschrittMoeglich;
    }

    public void doppelschrittNichtMehrMoeglich(){
        this.doppelschrittMoeglich = false;
    }
}
