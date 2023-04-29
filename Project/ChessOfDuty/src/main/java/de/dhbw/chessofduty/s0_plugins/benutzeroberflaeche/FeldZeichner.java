package de.dhbw.chessofduty.s0_plugins.benutzeroberflaeche;

import de.dhbw.chessofduty.s2_application_code.schachbrett.FeldDienst;
import processing.core.PGraphics;

import static processing.core.PConstants.CORNER;

public class FeldZeichner {

    FeldDienst feldDienst;

    public FeldZeichner(FeldDienst feldDienst) {
        this.feldDienst = feldDienst;
    }

    public void zeichne(PGraphics g, int mausX, int mausY){

        g.pushMatrix();
        g.fill(255 * feldDienst.getFeld().getFarbe());
        g.rectMode(CORNER);
        g.rect(feldDienst.getFeld().getX(), feldDienst.getFeld().getY(), feldDienst.getFeld().getGroesse(), feldDienst.getFeld().getGroesse());
        g.popMatrix();
    }
}
