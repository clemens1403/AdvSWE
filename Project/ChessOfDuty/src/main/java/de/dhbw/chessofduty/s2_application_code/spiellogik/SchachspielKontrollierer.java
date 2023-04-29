package de.dhbw.chessofduty.s2_application_code.spiellogik;

import de.dhbw.chessofduty.s2_application_code.schachbrett.SchachbrettDienst;
import de.dhbw.chessofduty.s3_domain_code.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SchachspielKontrollierer {

    private Schachspiel schachspiel;

    private int spielerAmZug;

    private Figur ausgewaehltFigur = null;

    private ArrayList<Feld> moeglicheZuegeDerFigur = new ArrayList<>();

    public SchachspielKontrollierer(SchachspielDienst schachspielDienst) {
        this.schachspiel = schachspielDienst.erstelleSchachspiel();
        this.spielerAmZug = 1;

        this.initialisiereFiguren();
    }

    public void initialisiereFiguren() {
        ArrayList<Figur> figuren = new ArrayList<Figur>();
        for (int i = 1; i <= 8; i++) {
            figuren.add(new Bauer(1, schachspiel.getSchachbrett().getFeld(i, 2)));
            figuren.add(new Bauer(0, schachspiel.getSchachbrett().getFeld(i, 7)));
        }

        figuren.add(new Koenig(1, schachspiel.getSchachbrett().getFeld(5, 1)));
        figuren.add(new Koenig(0, schachspiel.getSchachbrett().getFeld(5, 8)));

        figuren.add(new Dame(1, schachspiel.getSchachbrett().getFeld(4, 1)));
        figuren.add(new Dame(0, schachspiel.getSchachbrett().getFeld(4, 8)));

        figuren.add(new Laeufer(1, schachspiel.getSchachbrett().getFeld(3, 1)));
        figuren.add(new Laeufer(0, schachspiel.getSchachbrett().getFeld(3, 8)));
        figuren.add(new Laeufer(1, schachspiel.getSchachbrett().getFeld(6, 1)));
        figuren.add(new Laeufer(0, schachspiel.getSchachbrett().getFeld(6, 8)));
        figuren.add(new Springer(1, schachspiel.getSchachbrett().getFeld(2, 1)));
        figuren.add(new Springer(0, schachspiel.getSchachbrett().getFeld(2, 8)));
        figuren.add(new Springer(1, schachspiel.getSchachbrett().getFeld(7, 1)));
        figuren.add(new Springer(0, schachspiel.getSchachbrett().getFeld(7, 8)));

        figuren.add(new Turm(1, schachspiel.getSchachbrett().getFeld(1, 1)));
        figuren.add(new Turm(0, schachspiel.getSchachbrett().getFeld(1, 8)));
        figuren.add(new Turm(1, schachspiel.getSchachbrett().getFeld(8, 1)));
        figuren.add(new Turm(0, schachspiel.getSchachbrett().getFeld(8, 8)));

        schachspiel.setFiguren(figuren);
    }

    public Schachspiel getSchachspiel() {
        return schachspiel;
    }

    public void klickAuswerten() {
        if (this.ausgewaehltFigur != null) {
            this.fuehreZugAus(new SchachbrettDienst());
        } else {
            this.waehleEineFigurAus();
        }
    }

    public void waehleEineFigurAus() {

        for (Figur f : schachspiel.getFiguren()) {
            if (f.checkFigurGeklickt()) {
                if (f.getFarbe() == this.spielerAmZug) {
                    this.ausgewaehltFigur = f;
                    this.moeglicheZuegeDerFigur = f.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett());
                    this.moeglicheZuegeDerFigur = this.getValideZuege(this.moeglicheZuegeDerFigur);
                    System.out.println(this.moeglicheZuegeDerFigur);

                }

                break;
            }
        }
    }

    public Figur getAusgewaelteFigur() {
        return this.ausgewaehltFigur;
    }

    public ArrayList<Feld> getMoeglicheZuegeDerFigur() {
        return this.moeglicheZuegeDerFigur;
    }

    public ArrayList<Feld> getValideZuege(ArrayList<Feld> zuege) {
        ArrayList<Feld> valideZuege = new ArrayList<Feld>();
        ArrayList<Figur> kopieFiguren = (ArrayList<Figur>) schachspiel.getFiguren().clone();
        Feld startPosition = this.ausgewaehltFigur.getPosition();
        for (Feld zug : zuege) {
            boolean geschlagen = false;
            Figur geschlageneFigur = null;
            int index = kopieFiguren.indexOf(this.ausgewaehltFigur);
            Figur kopieFigur = kopieFiguren.get(index);
            kopieFiguren.get(index).setPosition(zug);
            for (Figur fg : schachspiel.getFiguren()) {
                if (fg.getPosition() == zug && fg.getFarbe() != this.spielerAmZug) {
                    geschlageneFigur = fg;
                    System.out.println("Geschlagene Figur: " + fg.getAbkuerzung() + "\n");
                    kopieFiguren.remove(fg);
                    geschlagen = true;
                }
            }
            if (!checkSchach(kopieFiguren, (this.spielerAmZug - 1) * (this.spielerAmZug - 1))) {
                kopieFigur.setPosition(startPosition);
                System.out.println("\nmove valid");
                valideZuege.add(zug);
            }
            if (geschlagen) {
                kopieFiguren.add(geschlageneFigur);
            }
            this.ausgewaehltFigur.setPosition(startPosition);
        }
        return valideZuege;
    }

    public void fuehreZugAus(SchachbrettDienst schachbrettDienst) {

        Feld ausgewaehltesFeld = selektiereAusgewaehltesFeld();
        boolean umsetzenMoeglich = this.moeglicheZuegeDerFigur.contains(ausgewaehltesFeld);

        if (umsetzenMoeglich) {
            ArrayList<String> zuege = schachspiel.getZuege();
            for (Figur f : schachspiel.getFiguren()) {
                if (f == ausgewaehltFigur) {
                    int neueZeile = ausgewaehltesFeld.getZeile();
                    int neueSpalte = ausgewaehltesFeld.getSpalte();

                    int alteZeile = f.getPosition().getZeile();
                    int alteSpalte = f.getPosition().getSpalte();

                    String zug = f.getAbkuerzung() + schachbrettDienst.zahlZuBuchstabe(alteSpalte) + Integer.toString(alteZeile);
                    //Überprüfung, ob etwas geschlagen wurde, oder nicht
                    if (pruefeFigurGeschlagen(ausgewaehltesFeld)) {
                        zug += "x";
                    } else {
                        zug += "-";
                    }
                    zug += schachbrettDienst.zahlZuBuchstabe(neueSpalte) + Integer.toString(neueZeile);

                    //Hier wird die Figur gesetzt
                    f.setPosition(ausgewaehltesFeld);

                    if (checkSchach(schachspiel.getFiguren(), this.spielerAmZug)) {
                        zug += "+";
                        if (checkMatt()) {
                            zug += "+";
                        }
                        if (this.spielerAmZug == 1) {
                            schachspiel.setSchachSchwarz(true);
                        } else {
                            schachspiel.setSchachWeiss(true);
                        }
                    } else {
                        if (this.spielerAmZug == 1) {
                            schachspiel.setSchachSchwarz(false);
                        } else {
                            schachspiel.setSchachWeiss(false);
                        }
                    }

                    zuege.add(zug);
                    schachspiel.setZuege(zuege);

                    //Wenn der Gegner im vorherigen Zug schach gegeben haben sollte, wird falls es sich nicht um Matt handelt, mit dem nächsten Zug das Schach aufgehoben
                    if (this.spielerAmZug == 1) {
                        schachspiel.setSchachWeiss(false);
                    } else {
                        schachspiel.setSchachSchwarz(false);
                    }

                    //Spieler am Zug wechselt
                    this.spielerAmZug = (this.spielerAmZug - 1) * (this.spielerAmZug - 1);
                    break;
                }
            }
        }

        this.moeglicheZuegeDerFigur = new ArrayList<Feld>();
        this.ausgewaehltFigur = null;
    }

    private Feld selektiereAusgewaehltesFeld() {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                Feld tmp = schachspiel.getSchachbrett().getFeld(i, j);
                if (tmp.pruefeFeldGeklickt() != null) {
                    return tmp;
                }
            }
        }

        return null;
    }

    private boolean pruefeFigurGeschlagen(Feld zielFeld) {

        for (Figur f : schachspiel.getFiguren()) {
            if (f.getPosition() == zielFeld && f.getFarbe() != ausgewaehltFigur.getFarbe()) {
                schlageFigur(f);
                return true;
            }
        }
        return false;
    }

    private void schlageFigur(Figur f) {
        //Figur zu Array der geschlagenen Figuren hinzufügen
        //Array nach Farbe aufgeteilt
        //Wenn Figur weiß dann zu geschlageneFigurenWeiss hinzufügen sonst zu geschlageneFigurenSchwarz
        System.out.println("Schlage Figur");
        if (f.getFarbe() == 1) {
            ArrayList<Figur> geschlageneFigurenWeiss = schachspiel.getGeschlageneFigurenWeiss();
            geschlageneFigurenWeiss.add(f);
            schachspiel.setGeschlageneFigurenWeiss(geschlageneFigurenWeiss);
        } else {
            ArrayList<Figur> geschlageneFigurenSchwarz = schachspiel.getGeschlageneFigurenSchwarz();
            geschlageneFigurenSchwarz.add(f);
            schachspiel.setGeschlageneFigurenSchwarz(geschlageneFigurenSchwarz);
        }
        ArrayList<Figur> figuren = schachspiel.getFiguren();
        figuren.remove(f);
        schachspiel.setFiguren(figuren);
    }

    private boolean checkSchach(ArrayList<Figur> figuren, int spieler) {

        Figur koenig;
        Feld koenigPosition = null;
        for (Figur fg : figuren) {
            if (fg.getFarbe() != spieler && fg.getAbkuerzung() == "K") {
                koenig = fg;
                koenigPosition = koenig.getPosition();
                break;
            }
        }

        for (Figur f : figuren) {
            if (f.getFarbe() == spieler) {
                ArrayList<Feld> mglZuege = f.getMoeglicheZuege(figuren, schachspiel.getSchachbrett());
                System.out.println("Königposition: \n");
                System.out.println(koenigPosition);
                if (mglZuege.contains(koenigPosition)) {
                    System.out.println("Schach!");
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkMatt() {
        for (Figur f : schachspiel.getFiguren()) {
            ArrayList<Figur> kopieFiguren = (ArrayList<Figur>) schachspiel.getFiguren().clone();
            Feld startPosition = f.getPosition();
            if (f.getFarbe() != this.spielerAmZug) {
                ArrayList<Feld> mglZuege = f.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett());
                for (Feld zug : mglZuege) {
                    boolean geschlagen = false;
                    Figur geschlageneFigur = null;
                    //f.setPosition(zug);
                    int index = kopieFiguren.indexOf(f);
                    Figur kopieFigur = kopieFiguren.get(index);
                    kopieFiguren.get(index).setPosition(zug);
                    for (Figur fg : schachspiel.getFiguren()) {
                        if (fg.getPosition() == zug && fg.getFarbe() == this.spielerAmZug) {
                            geschlageneFigur = fg;
                            System.out.println("Geschlagene Figur: " + fg.getAbkuerzung() + "\n");
                            kopieFiguren.remove(fg);
                            geschlagen = true;
                        }
                    }
                    if (!checkSchach(kopieFiguren, this.spielerAmZug)) {

                        System.out.println(kopieFigur.getAbkuerzung());
                        System.out.println(kopieFigur.getPosition().getSpalte());
                        System.out.println(kopieFigur.getPosition().getZeile());
                        kopieFigur.setPosition(startPosition);
                        System.out.println("\nno Mate");
                        return false;
                    }
                    if (geschlagen) {
                        kopieFiguren.add(geschlageneFigur);
                    }
                }
            }
            f.setPosition(startPosition);
        }
        System.out.println("Schachmatt");
        return true;
    }

    public void exportZuege() {
        String pathString = null;

        try {
            Process p = Runtime.getRuntime().exec("reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v personal");
            p.waitFor();

            InputStream in = p.getInputStream();
            byte[] b = new byte[in.available()];
            in.read(b);
            in.close();

            pathString = new String(b);
            pathString = pathString.split("\\s\\s+")[4];

        } catch (Throwable t) {
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
        String fileNameString = pathString + schachspiel.getSpielID() + ".txt";
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
        for (String zug : schachspiel.getZuege()) {
            int index = schachspiel.getZuege().indexOf(zug);
            if (index % 2 == 0) {
                int spielzug = index / 2 + 1;
                text.append(spielzug).append(":");
            }
            text.append(zug).append(";");
            if (index % 2 == 1) {
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

    public Schachbrett getSchachbrett() {
        return schachspiel.getSchachbrett();
    }

}

