public class Feld {
    
    private int zeile;
    private int spalte;
    private int farbe;
    private String name;
    private int groesse;
    private int x;
    private int y;
    
    public Feld(int spalte, int zeile){
        this.zeile = zeile; 
        this.spalte = spalte;
        this.groesse = 100;
        if((zeile + spalte) % 2 == 0){
            this.farbe = 1;
        }else{
            this.farbe = 0;
        }

        this.x = 700 - ((zeile-1)*groesse) + 200;
        this.y = 700 - ((spalte-1)*groesse) + 200;
        
    }

    public void render(){
        push();
        fill(255 * farbe);
        rect(x, y, groesse, groesse);
        pop();
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
