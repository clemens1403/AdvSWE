package de.dhbw.chessofduty.s0_plugins.logik;

import de.dhbw.chessofduty.s3_domain_code.*;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.io.InputStream;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Schachspiel extends PApplet {
    private UUID gameID;
    PGraphics g;

    private Schachbrett schachbrett;
    private ArrayList<Figur> figuren = new ArrayList<Figur>();

    private ArrayList<Figur> geschlageneFigurenWeiss = new ArrayList<Figur>();
    private ArrayList<Figur> geschlageneFigurenSchwarz = new ArrayList<Figur>();

    private ArrayList<String> zuege = new ArrayList<String>();

    private int spielerAmZug;

    private boolean schachWeiss = false;
    private boolean schachSchwarz = false;

    private boolean mattWeiss = false;
    private boolean mattSchwarz = false;

    private Figur ausgewaehltFigur = null;
    private ArrayList<Feld> moeglicheZuegeDerFigur = new ArrayList<>();

    public Schachspiel(PGraphics g){
        this.gameID = UUID.randomUUID();
        this.g = g;

        this.schachbrett = new Schachbrett();
        this.spielerAmZug = 1;

        this.initialisiereFiguren();
    }

    public void initialisiereFiguren(){

        for(int i = 1; i<=8; i++){
            figuren.add(new Bauer(1, schachbrett.getFeld(i, 2)));
            figuren.add(new Bauer(0, schachbrett.getFeld(i, 7)));
        }

        figuren.add(new Koenig(1, schachbrett.getFeld(5,1)));
        figuren.add(new Koenig(0, schachbrett.getFeld(5,8)));

        figuren.add(new Dame(1, schachbrett.getFeld(4,1)));
        figuren.add(new Dame(0, schachbrett.getFeld(4,8)));

        figuren.add(new Laeufer(1, schachbrett.getFeld(3,1)));
        figuren.add(new Laeufer(0, schachbrett.getFeld(3,8)));
        figuren.add(new Laeufer(1, schachbrett.getFeld(6,1)));
        figuren.add(new Laeufer(0, schachbrett.getFeld(6,8)));
        figuren.add(new Springer(1, schachbrett.getFeld(2,1)));
        figuren.add(new Springer(0, schachbrett.getFeld(2,8)));
        figuren.add(new Springer(1, schachbrett.getFeld(7,1)));
        figuren.add(new Springer(0, schachbrett.getFeld(7,8)));

        figuren.add(new Turm(1, schachbrett.getFeld(1,1)));
        figuren.add(new Turm(0, schachbrett.getFeld(1,8)));
        figuren.add(new Turm(1, schachbrett.getFeld(8,1)));
        figuren.add(new Turm(0, schachbrett.getFeld(8,8)));
    }

    public void zeichneSchachspiel(PGraphics g, int mausX, int mausY){
        this.g = g;
        schachbrett.zeichneSchachbrett(this.g, mausX, mausY);

        this.renderZuege();
        this.renderGeschlageneFiguren();

        for(Figur f : this.figuren){
            f.zeichne(g, mausX, mausY);

            if(schachWeiss && f.getAbkuerzung() == "K" && f.getFarbe() == 1){
                Feld feld = f.getPosition();
                g.pushMatrix();
                g.fill(200,100,100,200);
                g.rect(feld.getX(), feld.getY(), feld.getGroesse(), feld.getGroesse());
                g.popMatrix();
            }else if(schachSchwarz && f.getAbkuerzung() == "K" && f.getFarbe() == 0) {
                Feld feld = f.getPosition();
                g.pushMatrix();
                g.fill(200,100,100,200);
                g.rect(feld.getX(), feld.getY(), feld.getGroesse(), feld.getGroesse());
                g.popMatrix();
            }
        }

        if(this.ausgewaehltFigur != null){
            Feld f = this.ausgewaehltFigur.getPosition();
            g.pushMatrix();
            g.fill(100,200,100,200);
            g.rect(f.getX(), f.getY(), f.getGroesse(), f.getGroesse());
            g.popMatrix();
        }

        for(Feld f: this.moeglicheZuegeDerFigur){
            g.pushStyle();
            g.stroke(200,100,100);
            g.strokeWeight(7);
            g.point(f.getX() + f.getGroesse()/2, f.getY() + f.getGroesse()/2);
            g.popStyle();
        }

    }

    public void renderGeschlageneFiguren(){
        g.pushStyle();
        g.fill(160,82,45);
        StringBuilder textS = new StringBuilder();
        for(Figur f : geschlageneFigurenSchwarz){
            textS.append(f.getAbkuerzung());
        }
        g.textAlign(LEFT, CENTER);
        g.textSize(20);
        g.text(textS.toString(), 200, 1100);
        g.popStyle();

        g.pushStyle();
        g.fill(169, 172, 176);
        StringBuilder textW = new StringBuilder();
        for(Figur f : geschlageneFigurenWeiss){
            textW.append(f.getAbkuerzung());
        }
        g.textAlign(RIGHT, CENTER);
        g.textSize(20);
        g.text(textW.toString(), 1000, 1100);
        g.popStyle();
    }

    public void renderZuege(){
        g.pushStyle();
        g.fill(0);
        g.textSize(40);
        g.textAlign(CENTER,CENTER);
        g.text("Züge:", g.width - 140, 200);
        g.popStyle();

        g.pushStyle();
        g.fill(200);
        g.textSize(15);
        g.textAlign(LEFT, TOP);
        StringBuilder text = new StringBuilder();
        for(String zug : this.zuege){
            int index = this.zuege.indexOf(zug);
            if(index % 2 == 0){
                int spielzug = index/2 + 1;
                text.append(spielzug).append(":");
            }
            text.append(zug).append(";");
            if(index % 2 == 1){
                text.append("\n");
            }
        }
        g.text(text.toString(), g.width-185, 300);
        g.popStyle();
    }

    public void klickAuswerten(){
        if (this.ausgewaehltFigur != null){
            this.fuehreZugAus();
        }else{
            this.waehleEineFigurAus();
        }
    }

    public void waehleEineFigurAus(){

        for(Figur f : figuren){
            if(f.checkFigurGeklickt()) {
                if(f.getFarbe() == this.spielerAmZug){
                    this.ausgewaehltFigur = f;
                    this.moeglicheZuegeDerFigur = f.getMoeglicheZuege(figuren, schachbrett);
                    this.moeglicheZuegeDerFigur = this.getValideZuege(this.moeglicheZuegeDerFigur);
                    System.out.println(this.moeglicheZuegeDerFigur);
                }

                break;
            }
        }
    }

    public ArrayList<Feld> getValideZuege(ArrayList<Feld> zuege){
        ArrayList<Feld> valideZuege = new ArrayList<Feld>();
        ArrayList<Figur> kopieFiguren = (ArrayList<Figur>)this.figuren.clone();
        Feld startPosition = this.ausgewaehltFigur.getPosition();
        for(Feld zug : zuege){
            boolean geschlagen = false;
            Figur geschlageneFigur = null;
            int index = kopieFiguren.indexOf(this.ausgewaehltFigur);
            Figur kopieFigur = kopieFiguren.get(index);
            kopieFiguren.get(index).setPosition(zug);
            for(Figur fg : this.figuren){
                if(fg.getPosition() == zug && fg.getFarbe() != this.spielerAmZug){
                    geschlageneFigur = fg;
                    System.out.println("Geschlagene Figur: " + fg.getAbkuerzung() + "\n");
                    kopieFiguren.remove(fg);
                    geschlagen = true;
                }
            }
            if(!checkSchach(kopieFiguren, (this.spielerAmZug-1)*(this.spielerAmZug-1))){
                kopieFigur.setPosition(startPosition);
                System.out.println("\nmove valid");
                valideZuege.add(zug);
            }
            if(geschlagen){
                kopieFiguren.add(geschlageneFigur);
            }
            this.ausgewaehltFigur.setPosition(startPosition);
        }
        return valideZuege;
    }

    public void fuehreZugAus(){

        Feld ausgewaehltesFeld = selektiereAusgewaehltesFeld();
        boolean umsetzenMoeglich = this.moeglicheZuegeDerFigur.contains(ausgewaehltesFeld);

        if(umsetzenMoeglich){
            for(Figur f : this.figuren){
                if(f == ausgewaehltFigur) {
                    int neueZeile = ausgewaehltesFeld.getZeile();
                    int neueSpalte = ausgewaehltesFeld.getSpalte();

                    int alteZeile = f.getPosition().getZeile();
                    int alteSpalte = f.getPosition().getSpalte();

                    String zug = f.getAbkuerzung() + schachbrett.zahlZuBuchstabe(alteSpalte) + Integer.toString(alteZeile);
                    //Überprüfung, ob etwas geschlagen wurde, oder nicht
                    if(pruefeFigurGeschlagen(ausgewaehltesFeld)){
                        zug += "x";
                    }else{
                        zug += "-";
                    }
                    zug += schachbrett.zahlZuBuchstabe(neueSpalte) + Integer.toString(neueZeile);

                    //Hier wird die Figur gesetzt
                    f.setPosition(ausgewaehltesFeld);

                    if(checkSchach(this.figuren, this.spielerAmZug)){
                        zug+="+";
                        if(checkMatt()){
                            zug+="+";
                        }
                        if(this.spielerAmZug == 1){
                            this.schachSchwarz = true;
                        }else{
                            this.schachWeiss = true;
                        }
                    }else{
                        if(this.spielerAmZug == 1){
                            this.schachSchwarz = false;
                        }else{
                            this.schachWeiss = false;
                        }
                    }

                    this.zuege.add(zug);

                    //Wenn der Gegner im vorherigen Zug schach gegeben haben sollte, wird falls es sich nicht um Matt handelt, mit dem nächsten Zug das Schach aufgehoben
                    if(this.spielerAmZug == 1){
                        this.schachWeiss = false;
                    }else{
                        this.schachSchwarz = false;
                    }

                    //Spieler am Zug wechselt
                    this.spielerAmZug =  (this.spielerAmZug - 1) * (this.spielerAmZug - 1);
                    break;
                }
            }
        }

        this.moeglicheZuegeDerFigur = new ArrayList<Feld>();
        this.ausgewaehltFigur = null;
    }

    private Feld selektiereAusgewaehltesFeld(){
        for(int i = 1; i <= 8; i++){
            for(int j = 1; j <= 8; j++){
                Feld tmp = schachbrett.getFeld(i, j);
                if(tmp.pruefeFeldGeklickt() != null){
                    return tmp;
                }
            }
        }

        return null;
    }

    private boolean pruefeFigurGeschlagen(Feld zielFeld){

        for(Figur f : this.figuren){
            if(f.getPosition() == zielFeld && f.getFarbe() != ausgewaehltFigur.getFarbe()){
                schlageFigur(f);
                return true;
            }
        }
        return false;
    }

    private void schlageFigur(Figur f){
        //Figur zu Array der geschlagenen Figuren hinzufügen
        //Array nach Farbe aufgeteilt
        //Wenn Figur weiß dann zu geschlageneFigurenWeiss hinzufügen sonst zu geschlageneFigurenSchwarz
        System.out.println("Schlage Figur");
        if(f.getFarbe()== 1){
            this.geschlageneFigurenWeiss.add(f);
        }else{
            this.geschlageneFigurenSchwarz.add(f);
        }
        this.figuren.remove(f);
    }

    private boolean checkSchach(ArrayList<Figur> figuren, int spieler){

        Figur koenig;
        Feld koenigPosition = null;
        for(Figur fg : figuren){
            if(fg.getFarbe() != spieler && fg.getAbkuerzung() =="K"){
                koenig = fg;
                koenigPosition = koenig.getPosition();
                break;
            }
        }

        for(Figur f: figuren){
            if(f.getFarbe()==spieler){
                ArrayList<Feld> mglZuege = f.getMoeglicheZuege(figuren, this.schachbrett);
                System.out.println("Königposition: \n");
                System.out.println(koenigPosition);
                if(mglZuege.contains(koenigPosition)){
                    System.out.println("Schach!");
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkMatt(){
        for(Figur f : this.figuren){
            ArrayList<Figur> kopieFiguren = (ArrayList<Figur>)this.figuren.clone();
            Feld startPosition = f.getPosition();
            if(f.getFarbe() != this.spielerAmZug){
                ArrayList<Feld> mglZuege = f.getMoeglicheZuege(this.figuren, this.schachbrett);
                for(Feld zug: mglZuege){
                    boolean geschlagen = false;
                    Figur geschlageneFigur = null;
                    //f.setPosition(zug);
                    int index = kopieFiguren.indexOf(f);
                    Figur kopieFigur = kopieFiguren.get(index);
                    kopieFiguren.get(index).setPosition(zug);
                    for(Figur fg : this.figuren){
                        if(fg.getPosition() == zug && fg.getFarbe() == this.spielerAmZug){
                            geschlageneFigur = fg;
                            System.out.println("Geschlagene Figur: " + fg.getAbkuerzung() + "\n");
                            kopieFiguren.remove(fg);
                            geschlagen = true;
                        }
                    }
                    if(!checkSchach(kopieFiguren, this.spielerAmZug)){

                        System.out.println(kopieFigur.getAbkuerzung());
                        System.out.println(kopieFigur.getPosition().getSpalte());
                        System.out.println(kopieFigur.getPosition().getZeile());
                        kopieFigur.setPosition(startPosition);
                        System.out.println("\nno Mate");
                        return false;
                    }
                    if(geschlagen){
                        kopieFiguren.add(geschlageneFigur);
                    }
                }
            }
            f.setPosition(startPosition);
        }
        System.out.println("Schachmatt");
        return true;
    }

    public void exportZuege(){
        String pathString = null;

        try {
            Process p =  Runtime.getRuntime().exec("reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v personal");
            p.waitFor();

            InputStream in = p.getInputStream();
            byte[] b = new byte[in.available()];
            in.read(b);
            in.close();

            pathString = new String(b);
            pathString = pathString.split("\\s\\s+")[4];

        } catch(Throwable t) {
            t.printStackTrace();
        }

        pathString += "\\SchachLogs\\";

        try {

            Path path = Paths.get(pathString);

            //java.nio.file.Files;
            Files.createDirectories(path);

            System.out.println("Directory is created!");

        } catch (IOException e) {

            System.err.println("Failed to create directory!" + e.getMessage());

        }
        String fileNameString = pathString + this.gameID + ".txt";
        System.out.println(fileNameString);
        try {
            File logFile = new File(fileNameString);
            if (logFile.createNewFile()) {
                System.out.println("File created: " + logFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Path p = Path.of(fileNameString);
        StringBuilder text = new StringBuilder();
        for(String zug : this.zuege){
            int index = this.zuege.indexOf(zug);
            if(index % 2 == 0){
                int spielzug = index/2 + 1;
                text.append(spielzug).append(":");
            }
            text.append(zug).append(";");
            if(index % 2 == 1){
                text.append("\n");
            }
        }
        try {

            Path filePath = Files.writeString(p, text.toString());
            String s = Files.readString(filePath);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

