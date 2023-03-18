public class Schachbrett {
    ArrayList<ArrayList<Feld>> felder;

    public Schachbrett(){
        
        felder = new ArrayList<ArrayList<Feld>>();
        for(int i = 1; i <= 8; i++){

            ArrayList<Feld> tempListe = new ArrayList<Feld>();

            for(int j = 1; j<= 8; j++){
                tempListe.add(new Feld(i,j));
                //felder.get(i-1).add(new Feld(i,j));
            }

            felder.add(tempListe);
        }
    }

    public ArrayList<ArrayList<Feld>> getFelder(){
        return felder;
    }

    public Feld getFeld(int spalte, int zeile){

        return felder.get(zeile-1).get(spalte-1);
    }

    public void renderSchachbrett(){
        felder = felder;

        for(int i = 1; i <= 8; i++){
            for(int j = 1; j <= 8; j++){
                Feld f = felder.get(i-1).get(j-1);
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
