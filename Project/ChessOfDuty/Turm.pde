public class Turm extends Figur{
    public Turm(int farbe, Feld startPosition){
        
        super(farbe, startPosition);
        
        this.setName("Turm");
        this.setAbkuerzung("T");
        this.setWert(5);
    }

    public boolean sindZwischenfelderFrei(){
        //Hier muss die Überprüfungsroutine eingebaut werden, ob der Läufer ziehen kann
        return false;
    }

    @Override
    public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren){

        int spalte = this.getPosition().getSpalte();
        int zeile = this.getPosition().getZeile();

        

        for(Figur f : figuren){
            if(f.getPosition().getSpalte() == spalte){
                System.out.println("ALAAAARM");

            }
        }


        return new ArrayList<Feld>();
    }
    
}
