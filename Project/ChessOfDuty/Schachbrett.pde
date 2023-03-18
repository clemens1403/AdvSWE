public class Schachbrett {
    ArrayList<ArrayList<Feld>> spielfeld;

    public Schachbrett(){
        
        spielfeld = new ArrayList<ArrayList<Feld>>();
        for(int i = 1; i <= 8; i++){

            ArrayList<Feld> tempListe = new ArrayList<Feld>();

            for(int j = 1; j<= 8; j++){
                tempListe.add(new Feld(i,j));
                //spielfeld.get(i-1).add(new Feld(i,j));
            }

            spielfeld.add(tempListe);
        }
    }

    public ArrayList<ArrayList<Feld>> getspielfeld(){
        return spielfeld;
    }

    public Feld getFeld(int spalte, int zeile){

        return spielfeld.get(zeile).get(spalte);
    }
}
