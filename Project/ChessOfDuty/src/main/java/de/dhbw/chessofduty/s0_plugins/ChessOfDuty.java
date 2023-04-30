package de.dhbw.chessofduty.s0_plugins;

import de.dhbw.chessofduty.s0_plugins.benutzeroberflaeche.*;
import de.dhbw.chessofduty.s2_application_code.figuren.*;
import de.dhbw.chessofduty.s2_application_code.schachbrett.FeldDienst;
import de.dhbw.chessofduty.s2_application_code.schachbrett.SchachbrettDienst;
import de.dhbw.chessofduty.s2_application_code.spiellogik.SchachspielDienst;
import de.dhbw.chessofduty.s2_application_code.spiellogik.SchachspielKontrollierer;
import de.dhbw.chessofduty.s2_application_code.spielzug.SchachzugDienst;
import de.dhbw.chessofduty.s2_application_code.spielzug.SpielzugDienst;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;

public class ChessOfDuty extends PApplet {

    BauerDienst bauerDienst;
    DameDienst dameDienst;
    FigurDienst figurDienst;
    KoenigDienst koenigDienst;
    LaeuferDienst laeuferDienst;
    SpringerDienst springerDienst;
    TurmDienst turmDienst;

    FeldDienst feldDienst;
    SchachbrettDienst schachbrettDienst;
    SchachspielDienst schachspielDienst;
    SchachzugDienst schachzugDienst;
    SpielzugDienst spielzugDienst;

    FeldZeichner feldZeichner;
    SchachbrettZeichner schachbrettZeichner;
    SchachspielZeichner schachspielZeichner;

    SchachspielKontrollierer schachspielKontrollierer;
    Benutzeroberflaeche benutzeroberflaeche;

    PGraphics g;

    public void settings(){
        size(1200, 1050);
    }
    public void setup() {
        this.g = createGraphics(1200, 1050);

        FigurZeichner figurZeichner = new FigurZeichner();
        feldZeichner = new FeldZeichner();

        this.bauerDienst = new BauerDienst();
        this.dameDienst = new DameDienst();
        this.koenigDienst = new KoenigDienst();
        this.laeuferDienst = new LaeuferDienst();
        this.springerDienst = new SpringerDienst();
        this.turmDienst = new TurmDienst();

        this.figurDienst = new FigurDienst();
        this.feldDienst = new FeldDienst();
        this.schachzugDienst = new SchachzugDienst();
        this.spielzugDienst = new SpielzugDienst();


        schachbrettDienst = new SchachbrettDienst();
        schachspielDienst = new SchachspielDienst(schachbrettDienst);
        schachspielKontrollierer = new SchachspielKontrollierer(schachspielDienst, figurDienst, bauerDienst, dameDienst, koenigDienst, laeuferDienst, springerDienst, turmDienst, feldDienst, schachbrettDienst, schachzugDienst, spielzugDienst);

        schachbrettZeichner = new SchachbrettZeichner(schachbrettDienst, feldZeichner);
        schachspielZeichner = new SchachspielZeichner(g, schachspielKontrollierer, schachbrettZeichner, figurZeichner);

        benutzeroberflaeche = new Benutzeroberflaeche(schachspielZeichner,this.g);
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
                  schachspielKontrollierer = new SchachspielKontrollierer(schachspielDienst, figurDienst, bauerDienst, dameDienst, koenigDienst, laeuferDienst, springerDienst, turmDienst, feldDienst, schachbrettDienst, schachzugDienst, spielzugDienst);
                  benutzeroberflaeche.setStatus("Spiel");
                  break;
                default:
                  break;
              }
            }
          }
          break;
        case "Spiel":
          schachspielKontrollierer.klickAuswerten(mouseX, mouseY);
          benutzeroberflaeche.setSchachspiel(schachspielKontrollierer);
          ArrayList<Knopf> knoepfeSpiel = benutzeroberflaeche.getSpielKnoepfe();
          for(Knopf k : knoepfeSpiel){
            if(k.pruefeMausPosition()){
              switch(k.getId()){
                case "neustart":
                  schachspielKontrollierer = new SchachspielKontrollierer(schachspielDienst, figurDienst, bauerDienst, dameDienst, koenigDienst, laeuferDienst, springerDienst, turmDienst, feldDienst, schachbrettDienst, schachzugDienst, spielzugDienst);
                  benutzeroberflaeche.setSchachspiel(schachspielKontrollierer);
                  break;
                case "Start":
                  benutzeroberflaeche.setStatus("Start");
                  break;
                case "export":
                  //schachspielKontrollierer.exportZuege();
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

