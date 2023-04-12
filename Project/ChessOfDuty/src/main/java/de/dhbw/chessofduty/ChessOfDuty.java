package de.dhbw.chessofduty;

import de.dhbw.chessofduty.s0_plugins.GUI;
import de.dhbw.chessofduty.s0_plugins.Knopf;
import de.dhbw.chessofduty.s3_domain_code.Schachspiel;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;

public class ChessOfDuty extends PApplet {

    Schachspiel schachspiel;
    GUI gui;

    PGraphics g;

    public void settings(){
        size(1200, 1200);
    }
    public void setup() {
        this.g = createGraphics(1200, 1050);

        schachspiel = new Schachspiel(this.g);
        gui = new GUI(schachspiel, this.g);
    }



    public void draw() {
        background(255);
        this.g.beginDraw();
        gui.render(this.g, mouseX, mouseY);
        this.g.endDraw();
        this.image(g, 0, 0);
    }

    // Mausclick-Verarbeitung

    public void mousePressed(){
      switch(gui.getStatus()){
        case "Start":
          ArrayList<Knopf> knoepfeStart = gui.getStartKnoepfe();
          for(Knopf k : knoepfeStart){
            if(k.checkMausPosition()){
              switch(k.getId()){
                case "Spielen":
                  schachspiel = new Schachspiel(this.g);
                  gui.setStatus("Spiel");
                  break;
                default:
                  break;
              }
            }
          }
          break;
        case "Spiel":
          schachspiel.klickAuswerten();
          gui.setSchachspiel(schachspiel);
          ArrayList<Knopf> knoepfeSpiel = gui.getSpielKnoepfe();
          for(Knopf k : knoepfeSpiel){
            if(k.checkMausPosition()){
              switch(k.getId()){
                case "neustart":
                  schachspiel = new Schachspiel(this.g);
                  gui.setSchachspiel(schachspiel);
                  break;
                case "Start":
                  gui.setStatus("Start");
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
        PApplet.main("de.dhbw.chessofduty.ChessOfDuty");
    }
}

