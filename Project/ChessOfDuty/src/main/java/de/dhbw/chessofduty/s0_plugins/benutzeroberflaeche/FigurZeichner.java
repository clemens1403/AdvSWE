package de.dhbw.chessofduty.s0_plugins.benutzeroberflaeche;

import de.dhbw.chessofduty.s3_domain_code.Figur;
import processing.core.PGraphics;

import static processing.core.PConstants.CENTER;

public class FigurZeichner {

    public void zeichneFigur(Figur figur, PGraphics g){

        g.pushStyle();
        if(figur.getFarbe()==1){
            g.fill(169, 172, 176);
        }else{
            g.fill(160,82,45);
        }

        g.textAlign(CENTER, CENTER);
        g.textSize(50);
        int x = 50 + (figur.getPosition().getX());
        int y = 50 + (figur.getPosition().getY());
        g.text(figur.getAbkuerzung(), x, y);
        g.popStyle();
    }

}
