package de.dhbw.chessofduty.s3_domain_code;

import java.util.ArrayList;
import java.util.UUID;

public class Schachspiel {
    private UUID spielID;

    private final Schachbrett schachbrett;

    private ArrayList<Figur> figuren = new ArrayList<Figur>();
    private ArrayList<Figur> geschlageneFigurenWeiss = new ArrayList<Figur>();

    private ArrayList<Figur> geschlageneFigurenSchwarz = new ArrayList<Figur>();
    private ArrayList<String> zuege = new ArrayList<String>();

    private int spielerAmZug;

    private boolean schachWeiss = false;

    private boolean schachSchwarz = false;
    private boolean mattWeiss = false;

    private boolean mattSchwarz = false;

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

    public UUID getSpielID() {
        return spielID;
    }

    public void setSpielID(UUID spielID) {
        this.spielID = spielID;
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

    public ArrayList<String> getZuege() {
        return zuege;
    }

    public void setZuege(ArrayList<String> zuege) {
        this.zuege = zuege;
    }

    public int getSpielerAmZug() {
        return spielerAmZug;
    }

    public void setSpielerAmZug(int spielerAmZug) {
        this.spielerAmZug = spielerAmZug;
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

    public boolean isMattWeiss() {
        return mattWeiss;
    }

    public void setMattWeiss(boolean mattWeiss) {
        this.mattWeiss = mattWeiss;
    }

    public boolean isMattSchwarz() {
        return mattSchwarz;
    }

    public void setMattSchwarz(boolean mattSchwarz) {
        this.mattSchwarz = mattSchwarz;
    }
}
