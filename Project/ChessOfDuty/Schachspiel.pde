public class Schachspiel {
    ArrayList<Feld> felder;
    
    public Schachspiel(){
        
        felder = new ArrayList<Feld>();
        for(int i = 1; i <= 8; i++){
            for(int j = 1; j<= 8; j++){
                felder.add(new Feld(i,j));
            }
        }
    }

    public ArrayList<Feld> getFelder(){
        return felder;
    }
}
