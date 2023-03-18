public class Schachbrett {
    Feld[][] felder;

    public Schachbrett(){
        
        felder = new Feld[8][8];

        for(int spalte = 1; spalte <= 8; spalte++){

            for(int zeile = 1; zeile<= 8; zeile++){
                felder[spalte-1][zeile-1] = new Feld(spalte,zeile);
            }
        }
    }

    public Feld[][] getFelder(){
        return felder;
    }

    public Feld getFeld(int spalte, int zeile){
        return felder[spalte-1][zeile-1];
    }

    public void renderSchachbrett(){
        for(int i = 1; i <= 8; i++){
            for(int j = 1; j <= 8; j++){
                Feld f = felder[i-1][j-1];
                //Feld f = felder[j-1][i-1];
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
}
