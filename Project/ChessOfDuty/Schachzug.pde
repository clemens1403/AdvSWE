public class Schachzug {

    private int farbe;
    private String abkuerzung;
    private Feld altePosition;
    private Feld neuePosition;

    public Schachzug(int farbe, String abkuerzung, Feld altePosition, Feld neuePosition){
        this.farbe = farbe;
        this.abkuerzung = abkuerzung;
        this.altePosition = altePosition;
        this.neuePosition = neuePosition;
    }
}
