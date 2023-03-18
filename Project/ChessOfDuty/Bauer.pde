import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class Bauer extends Figur{

    private boolean doppelschrittMoeglich = true;

    public Bauer(int farbe, Feld startPosition){
        super(farbe, startPosition);
        this.setName("Bauer");
        this.setAbkuerzung("B");
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

    @Override
    public ArrayList<Feld> getMoeglicheZuege(){
        /*
        Prüfe mögliche Züge für die Figur 'Bauer'

        Ein Bauer kann sich in folgende Richtungen bewegen:
            - zwei Felder nach vorne (sofern noch nicht bewegt)
            - ein Feld nach vorne (sofern bereits bewegt wurden und keine Figur blockiert)
            - ein Feld schräg nach links oder rechts vorne (andere Figur wird geschlagen)
            - ein Feld schräg nach links oder rechts vorne (En Passent)
        */

        // Farbe: 0 = Schwarz, 1 = Weiß

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        Feld aktuellePosition = getPosition();

        // Bauern können sich nur vorwärt auf dem Spielfeld bewegen
        if(getFarbe() == 1){
            //Die weißen Figuren bewegen sich bei den Zeilen in aufsteigende Richtung

            //Einfacher Schritt nach vorne
            int einfacherSchritt = aktuellePosition.getZeile() + 1;
            //moeglicheZuege.add((aktuellePosition.getSpalte(), einfacherSchritt));

        } else {
            //Die schwarzen Figuren bewegen sich bei den Zeilen in absteigende Richtung

            //Einfacher Schritt nach vorne
            int einfacherSchritt = aktuellePosition.getZeile() - 1;
            //moeglicheZuege.add(new SimpleEntry<>(aktuellePosition.getSpalte(), einfacherSchritt));

        }
        
        //int zeile = aktuellePosition.getZeile();

        return moeglicheZuege;

    }
}
