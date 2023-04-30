package de.dhbw.chessofduty.s3_domain_code;

import de.dhbw.chessofduty.s3_domain_code.spielzug.Spielzug;

import java.util.ArrayList;
import java.util.UUID;

public class Schachspiel {
    private static UUID spielID;

    private final Schachbrett schachbrett;

    private ArrayList<Figur> figuren = new ArrayList<Figur>();

    private ArrayList<Figur> geschlageneFigurenWeiss = new ArrayList<Figur>();
    private ArrayList<Figur> geschlageneFigurenSchwarz = new ArrayList<Figur>();

    private ArrayList<Spielzug> spielzuege = new ArrayList<>();

    private boolean schachWeiss = false;

    private boolean schachSchwarz = false;

    public Schachspiel(Schachbrett schachbrett) {
        this.spielID = UUID.randomUUID();
        this.schachbrett = schachbrett;
    }

    public ArrayList<Figur> getFiguren() {
        return figuren;
    }

    public void setFiguren(ArrayList<Figur> figuren) {
        this.figuren = figuren;
    }

    public Schachbrett getSchachbrett() {
        return schachbrett;
    }

    public ArrayList<Figur> getGeschlageneFigurenWeiss() {
        return geschlageneFigurenWeiss;
    }

    public void setGeschlageneFigurenWeiss(ArrayList<Figur> geschlageneFigurenWeiss) {
        this.geschlageneFigurenWeiss = geschlageneFigurenWeiss;
    }

    public ArrayList<Figur> getGeschlageneFigurenSchwarz() {
        return geschlageneFigurenSchwarz;
    }

    public void setGeschlageneFigurenSchwarz(ArrayList<Figur> geschlageneFigurenSchwarz) {
        this.geschlageneFigurenSchwarz = geschlageneFigurenSchwarz;
    }

    public boolean isSchachWeiss() {
        return schachWeiss;
    }

    public void setSchachWeiss(boolean schachWeiss) {
        this.schachWeiss = schachWeiss;
    }

    public boolean isSchachSchwarz() {
        return schachSchwarz;
    }

    public void setSchachSchwarz(boolean schachSchwarz) {
        this.schachSchwarz = schachSchwarz;
    }

    public ArrayList<Spielzug> getSpielzuege() {
        return spielzuege;
    }

    public void addSpielzug(Spielzug spielzug) {
        this.spielzuege.add(spielzug);
    }

    public void ueberarbeiteSpielzug(Spielzug spielzug){
        this.spielzuege.set(spielzug.getZugNummer()-1, spielzug);
    }

    public static UUID getSpielID() {
        return spielID;
    }
}
