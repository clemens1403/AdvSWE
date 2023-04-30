package de.dhbw.chessofduty.s3_domain_code.spielzug;

public class Spielzug {

    private int zugNummer;
    private Schachzug zugWeiss;
    private Schachzug zugSchwarz;

    public Spielzug(int zugNummer){
        this.zugNummer = zugNummer;
    }
    @Override
    public String toString(){
        return String.valueOf(zugNummer);
    }

    public void setSchachzugWeiss(Schachzug weiss){
        this.zugWeiss = weiss;
    }

    public void setSchachzugSchwarz(Schachzug schwarz){
        this.zugSchwarz = schwarz;
    }

    public String getSpielzugText(){
        if (zugSchwarz == null) {
            return zugNummer + ". " + zugWeiss;

        }
        return zugNummer + ". " + zugWeiss + ":  " + zugSchwarz + ";";
    }

    public int getZugNummer(){
        return this.zugNummer;
    }


}

