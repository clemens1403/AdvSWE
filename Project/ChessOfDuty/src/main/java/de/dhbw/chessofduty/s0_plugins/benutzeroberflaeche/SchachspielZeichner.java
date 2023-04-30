package de.dhbw.chessofduty.s0_plugins.benutzeroberflaeche;

import de.dhbw.chessofduty.s2_application_code.spiellogik.SchachspielKontrollierer;
import de.dhbw.chessofduty.s3_domain_code.Feld;
import de.dhbw.chessofduty.s3_domain_code.Figur;
import de.dhbw.chessofduty.s3_domain_code.Schachspiel;
import de.dhbw.chessofduty.s3_domain_code.spielzug.Spielzug;
import processing.core.PApplet;
import processing.core.PGraphics;


public class SchachspielZeichner extends PApplet {

    private SchachspielKontrollierer schachspielKontrollierer;
    //private SchachbrettDienst schachbrettDienst;
    private SchachbrettZeichner schachbrettZeichner;
    private FigurZeichner figurZeichner;

    private Schachspiel schachspiel;


    PGraphics g;

    public SchachspielZeichner(PGraphics g, SchachspielKontrollierer schachspielKontrollierer, SchachbrettZeichner schachbrettZeichner, FigurZeichner figurZeichner) {
        this.schachspielKontrollierer = schachspielKontrollierer;
        this.schachbrettZeichner = schachbrettZeichner;
        this.figurZeichner = figurZeichner;
        this.schachspiel = schachspielKontrollierer.getSchachspiel();
        this.g = g;
    }

    public void zeichneSchachspiel(PGraphics g, int mausX, int mausY) {

        schachbrettZeichner.zeichneSchachbrett(g, mausX, mausY);

        this.zeichneZuege();
        this.zeichneGeschlageneFiguren();

        for (Figur figur : schachspiel.getFiguren()) {
            figurZeichner.zeichneFigur(figur, g);

            if (schachspiel.isSchachWeiss() && figur.getAbkuerzung() == "K" && figur.getFarbe() == 1) {
                Feld feld = figur.getPosition();
                g.pushMatrix();
                g.fill(200, 100, 100, 200);
                g.rect(feld.getX(), feld.getY(), feld.getGroesse(), feld.getGroesse());
                g.popMatrix();
            } else if (schachspiel.isSchachSchwarz() && figur.getAbkuerzung() == "K" && figur.getFarbe() == 0) {
                Feld feld = figur.getPosition();
                g.pushMatrix();
                g.fill(200, 100, 100, 200);
                g.rect(feld.getX(), feld.getY(), feld.getGroesse(), feld.getGroesse());
                g.popMatrix();
            }
        }

        Figur figur = schachspielKontrollierer.getAusgewaelteFigur();

        if (figur != null) {
            Feld f = figur.getPosition();
            g.pushMatrix();
            g.fill(100, 200, 100, 200);
            g.rect(f.getX(), f.getY(), f.getGroesse(), f.getGroesse());
            g.popMatrix();
        }

        for (Feld f : schachspielKontrollierer.getMoeglicheZuegeDerFigur()) {
            g.pushStyle();
            g.stroke(200, 100, 100);
            g.strokeWeight(7);
            g.point(f.getX() + f.getGroesse() / 2, f.getY() + f.getGroesse() / 2);
            g.popStyle();
        }

    }

    public void zeichneGeschlageneFiguren() {
        g.pushStyle();
        g.fill(160, 82, 45);
        StringBuilder textS = new StringBuilder();
        for (Figur f : schachspiel.getGeschlageneFigurenSchwarz()) {
            textS.append(f.getAbkuerzung());
        }
        g.textAlign(LEFT, CENTER);
        g.textSize(20);
        g.text(textS.toString(), 200, 1100);
        g.popStyle();

        g.pushStyle();
        g.fill(169, 172, 176);
        StringBuilder textW = new StringBuilder();
        for (Figur f : schachspiel.getGeschlageneFigurenWeiss()) {
            textW.append(f.getAbkuerzung());
        }
        g.textAlign(RIGHT, CENTER);
        g.textSize(20);
        g.text(textW.toString(), 1000, 1100);
        g.popStyle();
    }

    public void zeichneZuege() {
        g.pushStyle();
        g.fill(0);
        g.textSize(40);
        g.textAlign(CENTER, CENTER);
        g.text("Züge:", g.width - 140, 200);
        g.popStyle();

        g.pushStyle();
        g.fill(200);
        g.textSize(15);
        g.textAlign(LEFT, TOP);
        StringBuilder text = new StringBuilder();

        for(Spielzug spielzug : schachspiel.getSpielzuege()){
            text.append(spielzug.getSpielzugText()).append("\n");
        }

        g.text(text.toString(), g.width - 185, 300);
        g.popStyle();
    }

    public void setSchachspielKontrollierer(SchachspielKontrollierer schachspielKontrollierer){
        this.schachspielKontrollierer = schachspielKontrollierer;
        this.schachspiel = schachspielKontrollierer.getSchachspiel();
    }
}
