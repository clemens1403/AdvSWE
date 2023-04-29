package de.dhbw.chessofduty.s0_plugins.benutzeroberflaeche;

import de.dhbw.chessofduty.s2_application_code.schachbrett.SchachbrettService;
import de.dhbw.chessofduty.s3_domain_code.Feld;
import de.dhbw.chessofduty.s3_domain_code.Schachbrett;
import processing.core.PGraphics;

import static processing.core.PConstants.CENTER;

public class SchachbrettZeichner {

    SchachbrettService schachbrettService;

    public SchachbrettZeichner(SchachbrettService schachbrettService) {
        this.schachbrettService = schachbrettService;
    }

    public void zeichneSchachbrett(PGraphics g, int mausX, int mausY){
        Schachbrett schachbrett = schachbrettService.createSchachbrett();
        for(int i = 1; i <= 8; i++){
            for(int j = 1; j <= 8; j++){
                Feld f = schachbrett.getFelder()[i-1][j-1];
                f.zeichne(g, mausX, mausY);
            }
        }

        for (int i = 1; i <= 8; i++){
            g.pushStyle();
            g.fill(200);
            g.textAlign(CENTER, CENTER);
            g.textSize(20);
            g.text(schachbrettService.zahlZuBuchstabe(i-1), 250 + (i-1)*100, 1020);
            g.text(i, 180, 950 - (i-1)*100);
            g.popStyle();
        }
    }
}
