public abstract class Figur{

    private String name;
    private String abkuerzung;
    private int wert;
    private int farbe;
    private Boolean imSpiel;

    private Feld position;

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

    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett){
        return new ArrayList<Feld>();
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
            fill(169, 172, 176);
        }else{
            fill(160,82,45);
        }
        
        textAlign(CENTER, CENTER);
        textSize(50);
        int x = 50 + (this.position.getX());
        int y = 50 + (this.position.getY());
        text(this.abkuerzung, x, y);
        pop();
    }

    public boolean checkFigurGeklickt(){

        int feldX = this.position.getX();
        int feldY = this.position.getY();
        int feldGroesse = this.position.getGroesse();

        if((mouseX > feldX) && (mouseX < feldX+feldGroesse)){
            if((mouseY > feldY) && (mouseY < feldY+feldGroesse)){
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString(){
        return abkuerzung 
            + " " 
            +  String.valueOf(farbe) 
            + ": Spalte:" 
            + String.valueOf(getPosition().getSpalte()) 
            + " Zeile:" 
            + String.valueOf(getPosition().getZeile());
    }

}