public abstract class Figur{

    private String name;
    private String abkuerzung;
    private int wert;
    private int farbe;
    private Boolean imSpiel;

    private Feld position;
    private int[][] moeglicheZuege;


    public Figur(int farbe, Feld startPosition){
        
        this.farbe = farbe;
        this.position = startPosition;
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

    public void pruefeMoeglicheZuege(){
        
    }

    public Feld getPosition(){
        return this.position;
    }

    public void setPosition(Feld feld){
        //Hier müssen definitiv noch einige Fehler abgefangen werden
        this.position = feld;
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

    public int getFarbe(){
        return this.farbe;
    }

    public void render(){
        push();
        if(this.farbe==1){
            fill(200);
        }else{
            fill(70);
        }
        
        textAlign(CENTER, CENTER);
        textSize(30);
        int x = 250 + (this.position.getSpalte()-1)*100;
        int y = 250 + (this.position.getZeile()-1)*100;
        text(this.abkuerzung, x, y);
        pop();
    }

}