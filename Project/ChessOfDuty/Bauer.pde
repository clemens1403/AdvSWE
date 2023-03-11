public class Bauer extends Figur{

    private boolean doppelschrittMoeglich = true;

    public Bauer(int farbe, Feld startPosition, Bewegungsmuster muster){
        super(farbe, startPosition, muster);
        this.setName("Bauer");
        this.setWert(1);

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
