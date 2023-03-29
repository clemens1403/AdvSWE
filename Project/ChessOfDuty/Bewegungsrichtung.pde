public enum Bewegungsrichtung {

    OBEN_RECHTS(1, 1),
    OBEN_LINKS(-1, 1),
    UNTEN_RECHTS(1, -1),
    UNTEN_LINKS(-1, -1),
    LINKS(-1, 0),
    RECHTS(1, 0),
    OBEN(0, 1),
    UNTEN(0, -1);

    private int spalte;
    private int zeile; 

    Bewegungsrichtung(int spalte, int zeile){
        this.spalte = spalte;
        this.zeile = zeile;
    }

    public int spaltenVerschiebung(){
        return spalte;
    }

    public int zeilenVerschiebung(){
        return zeile;
    }
}