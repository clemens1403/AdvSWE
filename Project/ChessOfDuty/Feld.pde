public final class Feld {
    
    private final int zeile;
    private final int spalte;
    private final int farbe;
    private final int groesse;
    private final int x;
    private final int y;
    
    public Feld(int spalte, int zeile){
        this.zeile = zeile; 
        this.spalte = spalte;
        this.groesse = 100;
        if((zeile + spalte) % 2 == 0){
            this.farbe = 0;
        }else{
            this.farbe = 1;
        }

        this.x = ((spalte-1)*groesse) + 200;
        this.y = 700 - ((zeile-1)*groesse) + 200; 
    }

    public void render(){
        push();
        fill(255 * farbe);
        rect(x, y, groesse, groesse);
        pop();
    }

    public Feld checkFeldGeklickt(){

        if((mouseX > this.getX()) && (mouseX < this.getX()+this.getGroesse())){
            if((mouseY > this.getY()) && (mouseY < this.getY()+this.getGroesse())){
                return this;
            }
        }

        //Refactoring hier? Wir müssen was anderes als null wiedergeben
        return null;
    }

    @Override
    public boolean equals(Object objekt){
        if(this == objekt){
            return true;
        }

        if(objekt == null || this.getClass() != objekt.getClass()){
            return false;
        }

        Feld feld = (Feld) objekt;

        return zeile == feld.zeile &&
               spalte == feld.spalte &&
               farbe == feld.farbe &&
               groesse == feld.groesse &&
               x == feld.x &&
               y == feld.y;
    }

    @Override
    public int hashCode(){
        int ergebnis = 187;
        ergebnis = 31 * ergebnis + zeile;
        ergebnis = 31 * ergebnis + spalte;
        ergebnis = 31 * ergebnis + farbe;
        ergebnis = 31 * ergebnis + groesse;
        ergebnis = 31 * ergebnis + x;
        ergebnis = 31 * ergebnis + y;

        return ergebnis;
    }

    @Override
    public String toString(){
        return "Spalte: " + this.getSpalte() + ";  Zeile: " + this.getZeile() + "\n"; 
    }

    public int getZeile(){
        return this.zeile;
    }

    public int getSpalte(){
        return this.spalte;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getGroesse(){
        return this.groesse;
    }
}
