public enum Bewegungsrichtung {

    OBEN_RECHTS(1, 1, 8, 8),
    OBEN_LINKS(-1, 1, 1, 8),
    UNTEN_RECHTS(1, -1, 8, 1),
    UNTEN_LINKS(-1, -1, 1, 1),
    LINKS(-1, 0, 1, 0),
    RECHTS(1, 0, 8, 0),
    OBEN(0, 1, 0, 8),
    UNTEN(0, -1, 0, 1);

    private int spalte;
    private int zeile;
    private int spaltenVerbot;
    private int zeilenVerbot; 

    Bewegungsrichtung(int spalte, int zeile, int spaltenVerbot, int zeilenVerbot){
        this.spalte = spalte;
        this.zeile = zeile;
        this.spaltenVerbot = spaltenVerbot;
        this.zeilenVerbot = zeilenVerbot;
    }

    public int spaltenVerschiebung(){
        return spalte;
    }

    public int zeilenVerschiebung(){
        return zeile;
    }

    public int spaltenVerbot(){
        return spaltenVerbot;
    }

    public int zeilenVerbot(){
        return zeilenVerbot;
    }
}