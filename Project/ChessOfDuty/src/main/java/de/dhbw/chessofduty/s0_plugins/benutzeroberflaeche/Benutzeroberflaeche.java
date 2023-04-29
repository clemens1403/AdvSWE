package de.dhbw.chessofduty.s0_plugins.benutzeroberflaeche;

import de.dhbw.chessofduty.s2_application_code.spiellogik.SchachspielKontrollierer;
import processing.core.PGraphics;

import java.util.ArrayList;
import processing.core.PApplet;

public class Benutzeroberflaeche extends PApplet{

    private PGraphics g;
    private int mausX, mausY;
    private String status;
    private ArrayList<Knopf> startKnoepfe = new ArrayList<Knopf>();
    private ArrayList<Knopf> spielKnoepfe = new ArrayList<Knopf>();
    //private SchachspielKontrollierer schachspielKontrollierer;
    private SchachspielZeichner schachspielZeichner;

    public Benutzeroberflaeche(SchachspielZeichner schachspielZeichner, SchachspielKontrollierer schachspielKontrollierer, PGraphics g){
        this.g = g;
        //this.schachspielKontrollierer = schachspielKontrollierer;
        this.schachspielZeichner = schachspielZeichner;

        status = "Start";

        Knopf spielKnopf = new Knopf("Spielen", g.width/2, g.height/2, 300, 150, "Spielen", 200,230, 0, 30);
        startKnoepfe.add(spielKnopf);

        Knopf startKnopf = new Knopf("Start", g.width - 250, 50, 80, 30, "Start", 200,230,0, 18);
        Knopf neustartKnopf = new Knopf("neustart", g.width - 160, 50, 110, 30, "neues Spiel", 200, 230, 0,18);
        Knopf exportKnopf = new Knopf("export", g.width - 140, g.height-50, 110, 30, "Exportieren", 200,230,0,18);

        spielKnoepfe.add(startKnopf);
        spielKnoepfe.add(neustartKnopf);
        spielKnoepfe.add(exportKnopf);
    }

    public void render(PGraphics g, int mausX, int mausY){
        this.g = g;
        this.mausX = mausX;
        this.mausY = mausY;

        switch(status){
            case "Start":
                zeicheStartAnsicht();
                break;
            case "Spiel":
                zeichneSpiel();
                break;
            case "Zug":
                zeichneSpiel();
                break;
            default:
                zeicheStartAnsicht();
                break;
        }

    }

    public void zeicheStartAnsicht(){
        g.background(51);

        //Titel
        g.pushStyle();
        g.fill(255);
        g.textAlign(LEFT, BOTTOM);
        g.textSize(43);
        g.text("Chess Of Duty", 100, 100);
        g.popStyle();

        for(Knopf k : startKnoepfe){
            k.render(this.g, this.mausX, this.mausY);
        }
    }

    public void zeichneSpiel(){
        g.background(51);

        for(Knopf k : spielKnoepfe){
            k.render(this.g, this.mausX, this.mausY);
        }

        //Titel
        g.pushStyle();
        g.fill(255);
        g.textAlign(LEFT, BOTTOM);
        g.textSize(43);
        g.text("Chess Of Duty", 100, 100);
        g.popStyle();
        this.schachspielZeichner.zeichneSchachspiel(this.g, this.mausX, this.mausY);
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public ArrayList<Knopf> getStartKnoepfe(){
        return startKnoepfe;
    }

    public ArrayList<Knopf> getSpielKnoepfe(){
        return spielKnoepfe;
    }

    public void setSchachspiel(SchachspielKontrollierer schachspielKontrollierer){
        //this.schachspielKontrollierer = schachspielKontrollierer;
    }

}
