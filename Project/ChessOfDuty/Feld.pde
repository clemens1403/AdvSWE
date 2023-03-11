public class Feld {
    
    private int zeile;
    private int spalte;
    private int farbe;
    private String name;
    private int groesse;
    
    public Feld(int zeile, int spalte){
        this.zeile = zeile; 
        this.spalte = spalte;
        this.groesse = 100;
        if((zeile + spalte) % 2 == 0){
            this.farbe = 1;
        }else{
            this.farbe = 0;
        }
        
    }

    public void show(){
        push();
        fill(255 * farbe);
        rect((zeile-1)*groesse, (spalte-1)*groesse, groesse, groesse);
        pop();
    }

    public int getZeile(){
        return this.zeile;
    }

    public int getSpalte(){
        return this.spalte;
    }
}
