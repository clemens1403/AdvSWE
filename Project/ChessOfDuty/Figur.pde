public abstract class Figur{

    private String name;
    private String abkuerzung;
    private int wert;
    private int farbe;
    private Feld aktuellePosition;
    private Bewegungsmuster bewegungsmuster;
    private Boolean imSpiel;

    public Figur(int farbe, Feld startPosition, Bewegungsmuster muster){
        
        this.farbe = farbe;
        this.aktuellePosition = startPosition;
        this.bewegungsmuster = muster;
    }

    public boolean istZugAusserhalbVomFeld(){
        //Implementiere die Überprüfung, ob ein Zug den Springer aus dem Feld herausbringen würde
        return true;
    }

    public boolean istZugGueltig(String newPosition) {
        return true;
        // Implementierung der Überprüfung, ob der Zug der Dame gültig ist
        // Zum Beispiel wenn eine Figur bewegt werden soll und dadurch der eigene König ins Schach gerät
    }

    public void zugAusfuehren(){

    }

    public void wirdGeschlagen(){

    }

    public void schlaegtFigur(){

    }

    public void setztSchach(){

    }

    /*public Position getAktuellePosition(){
        return this.aktuellePosition;
    }*/

    public void setNeuePosition(Feld feld){
        //Hier müssen definitiv noch einige Fehler abgefangen werden
        this.aktuellePosition = feld;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setWert(int wert){
        this.wert = wert;
    }

    public int getWert(){
        return wert;
    }

    public void setAbkuerzung(String abkuerzung){
        this.abkuerzung = abkuerzung;
    }

    public String getAbkuerzung(){
        return abkuerzung;
    }

}