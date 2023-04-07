public final class Schachbrett {
    private final Feld[][] felder;

    public Schachbrett(){
        
        felder = new Feld[8][8];

        for(int spalte = 1; spalte <= 8; spalte++){

            for(int zeile = 1; zeile<= 8; zeile++){
                felder[spalte-1][zeile-1] = new Feld(spalte,zeile);
            }
        }
    }

    public void renderSchachbrett(){
        for(int i = 1; i <= 8; i++){
            for(int j = 1; j <= 8; j++){
                Feld f = felder[i-1][j-1];
                f.render();
            }
        }

        for (int i = 1; i <= 8; i++){
            push();
            fill(200);
            textAlign(CENTER, CENTER);
            textSize(20);
            text(integerZuBuchstabe(i-1), 250 + (i-1)*100, 1020);
            text(i, 180, 950 - (i-1)*100);
            pop();
        }
    }

    public String integerZuBuchstabe(int i){

        if(i < 0){
            return null;
        }
        String s = Integer.toString(i, 26);

        char[] buchstaben = s.toCharArray();

        String ergebnis = "";
        for(char b : buchstaben){
            int x = Integer.parseInt(Character.valueOf(b).toString(), 26);
            ergebnis += String.valueOf((char)(x + 'A'));          
        }

        return ergebnis;
    }

    @Override
    public boolean equals(Object objekt){
        if(objekt == this){
            return true;
        }

        if(!(objekt instanceof Schachbrett)){
            return false;
        }

        Schachbrett schachbrett = (Schachbrett) objekt;

        return Arrays.deepEquals(this.felder, schachbrett.felder);
    }

    @Override
    public int hashCode(){
        return Arrays.deepHashCode(felder);
    }

    public Feld[][] getFelder(){
        return felder;
    }

    public Feld getFeld(int spalte, int zeile){
        return felder[spalte-1][zeile-1];
    }
}
