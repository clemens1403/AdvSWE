package de.dhbw.chessofduty.s0_plugins;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Knopf extends PApplet {
    PGraphics g;
    private int mausX, mausY;
    private String id;
    private int x;
    private int y;
    private int breite;
    private int hoehe;
    private String text;
    private int hintergrund;
    private int hoverFarbe;
    private int textFarbe;
    private int textGroesse;

    public Knopf(String id, int x, int y, int breite, int hoehe, String text, int hintergrund, int hoverFarbe, int textFarbe, int textGroesse){
        this.id = id;
        this.x = x;
        this.y = y;
        this.breite = breite;
        this.hoehe = hoehe;
        this.text = text;
        this.hintergrund = hintergrund;
        this.hoverFarbe = hoverFarbe;
        this.textFarbe = textFarbe;
        this.textGroesse = textGroesse;
    }

    public void render(PGraphics g, int mausX, int mausY){
        this.g = g;
        this.mausX = mausX;
        this.mausY = mausY;

        g.pushStyle();
        if(checkMausPosition()){
            g.fill(hoverFarbe);
        }else{
            g.fill(hintergrund);
        }
        g.rectMode(CENTER);
        g.rect(x,y,breite,hoehe);
        g.fill(textFarbe);
        g.textAlign(CENTER, CENTER);
        g.textSize(textGroesse);
        g.text(text, x, y);
        g.pushStyle();
    }

    public boolean checkMausPosition(){
        if(mausX > x - (breite/2) && mausX < x + (breite/2)){
            if(mausY > y - (hoehe/2) && mausY < y + (hoehe/2)){
                return true;
            }
        }
        return false;
    }

    public String getId(){
        return id;
    }
}
