package de.dhbw.chessofduty.s3_domain_code.spielzug;

public class Spielzug {

    private int zugNummer;
    private Schachzug zugWeiss;
    private Schachzug zugSchwarz;

    public Spielzug(int zugNummer, Schachzug zugWeiss, Schachzug zugSchwarz){
        this.zugNummer = zugNummer;
        this.zugWeiss = zugWeiss;
        this.zugSchwarz = zugSchwarz;
    }
}

