public class EntityFactory {

    public Schachzug erstelleSchachzug(Figur figur, Feld neuePosition){
        return new Schachzug(figur.getFarbe(), figur.getAbkuerzung(), figur.getPosition(), neuePosition);
    }

    public Spielzug erstelleSpielzug(int zugNummer, Schachzug zugWeiss, Schachzug zugSchwarz){
        return new Spielzug(zugNummer, zugWeiss, zugSchwarz);
    }
}