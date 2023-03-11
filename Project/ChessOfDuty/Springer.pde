public class Springer extends Figur{
    public Springer(int farbe, Feld startPosition, Bewegungsmuster muster){
        super(farbe, startPosition, muster);
        this.setName("Springer");
        this.setAbkuerzung("S");
        this.setWert(3);
        
    }

}
