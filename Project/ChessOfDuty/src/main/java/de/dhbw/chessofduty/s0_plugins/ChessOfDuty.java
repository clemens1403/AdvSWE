package de.dhbw.chessofduty.s0_plugins;

import de.dhbw.chessofduty.s0_plugins.benutzeroberflaeche.Benutzeroberflaeche;
import de.dhbw.chessofduty.s0_plugins.benutzeroberflaeche.Knopf;
import de.dhbw.chessofduty.s0_plugins.logik.Schachspiel;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;

public class ChessOfDuty extends PApplet {

    Schachspiel schachspiel;
    Benutzeroberflaeche benutzeroberflaeche;

    PGraphics g;

    public void settings(){
        size(1200, 1200);
    }
    public void setup() {
        this.g = createGraphics(1200, 1050);

        schachspiel = new Schachspiel(this.g);
        benutzeroberflaeche = new Benutzeroberflaeche(schachspiel, this.g);
    }



    public void draw() {
        background(255);
        this.g.beginDraw();
        benutzeroberflaeche.render(this.g, mouseX, mouseY);
        this.g.endDraw();
        this.image(g, 0, 0);
    }

    // Mausclick-Verarbeitung

    public void mousePressed(){
      switch(benutzeroberflaeche.getStatus()){
        case "Start":
          ArrayList<Knopf> knoepfeStart = benutzeroberflaeche.getStartKnoepfe();
          for(Knopf k : knoepfeStart){
            if(k.pruefeMausPosition()){
              switch(k.getId()){
                case "Spielen":
                  schachspiel = new Schachspiel(this.g);
                  benutzeroberflaeche.setStatus("Spiel");
                  break;
                default:
                  break;
              }
            }
          }
          break;
        case "Spiel":
          schachspiel.klickAuswerten();
          benutzeroberflaeche.setSchachspiel(schachspiel);
          ArrayList<Knopf> knoepfeSpiel = benutzeroberflaeche.getSpielKnoepfe();
          for(Knopf k : knoepfeSpiel){
            if(k.pruefeMausPosition()){
              switch(k.getId()){
                case "neustart":
                  schachspiel = new Schachspiel(this.g);
                  benutzeroberflaeche.setSchachspiel(schachspiel);
                  break;
                case "Start":
                  benutzeroberflaeche.setStatus("Start");
                  break;
                case "export":
                  schachspiel.exportZuege();
                  break;
                default:
                  break;
              }
            }
          }
          break;
        default:
          break;
      }
    }

    public static void main(String[] args) {
        PApplet.main("de.dhbw.chessofduty.s0_plugins.ChessOfDuty");
    }
}

