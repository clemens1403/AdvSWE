package de.dhbw.chessofduty.s0_plugins.benutzeroberflaeche;

import de.dhbw.chessofduty.s2_application_code.schachbrett.FeldDienst;
import de.dhbw.chessofduty.s3_domain_code.Feld;
import processing.core.PGraphics;

import static processing.core.PConstants.CORNER;

public class FeldZeichner {

    public void zeichne(PGraphics g, Feld feld){

        g.pushMatrix();
        g.fill(255 * feld.getFarbe());
        g.rectMode(CORNER);
        g.rect(feld.getX(), feld.getY(), feld.getGroesse(), feld.getGroesse());
        g.popMatrix();
    }
}
