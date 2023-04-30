package de.dhbw.chessofduty.s2_application_code.spiellogik;

import de.dhbw.chessofduty.s2_application_code.figuren.*;
import de.dhbw.chessofduty.s2_application_code.schachbrett.FeldDienst;
import de.dhbw.chessofduty.s2_application_code.schachbrett.SchachbrettDienst;
import de.dhbw.chessofduty.s2_application_code.spielzug.SchachzugDienst;
import de.dhbw.chessofduty.s2_application_code.spielzug.SpielzugDienst;
import de.dhbw.chessofduty.s3_domain_code.*;
import de.dhbw.chessofduty.s3_domain_code.spielzug.Schachzug;
import de.dhbw.chessofduty.s3_domain_code.spielzug.Spielzug;

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

    private Figur ausgewaehlteFigur = null;

    private ArrayList<Feld> moeglicheZuegeDerFigur = new ArrayList<>();
    private FigurDienst figurDienst;
    private BauerDienst bauerDienst;
    private DameDienst dameDienst;
    private KoenigDienst koenigDienst;
    private LaeuferDienst laeuferDienst;
    private SpringerDienst springerDienst;
    private TurmDienst turmDienst;
    private FeldDienst feldDienst;
    private SchachbrettDienst schachbrettDienst;
    private SchachzugDienst schachzugDienst;
    private SpielzugDienst spielzugDienst;

    private Spielzug spielzug;

    public SchachspielKontrollierer(Schachspiel schachspiel, FigurDienst figurDienst, BauerDienst bauerDienst, DameDienst dameDienst, KoenigDienst koenigDienst, LaeuferDienst laeuferDienst, SpringerDienst springerDienst, TurmDienst turmDienst, FeldDienst feldDienst, SchachbrettDienst schachbrettDienst, SchachzugDienst schachzugDienst, SpielzugDienst spielzugDienst) {
        this.schachspiel = schachspiel;
        this.figurDienst = figurDienst;
        this.bauerDienst = bauerDienst;
        this.dameDienst = dameDienst;
        this.koenigDienst = koenigDienst;
        this.laeuferDienst = laeuferDienst;
        this.springerDienst = springerDienst;
        this.turmDienst = turmDienst;

        this.feldDienst = feldDienst;
        this.schachbrettDienst = schachbrettDienst;
        this.schachzugDienst = schachzugDienst;
        this.spielzugDienst = spielzugDienst;

        this.spielerAmZug = 1;
        this.initialisiereFiguren(bauerDienst, dameDienst, koenigDienst, laeuferDienst, springerDienst, turmDienst);
        this.spielzug = spielzugDienst.erstelleSpielzug(schachspiel.getSpielzuege());
    }

    public void initialisiereFiguren(BauerDienst bauerDienst, DameDienst dameDienst, KoenigDienst koenigDienst, LaeuferDienst laeuferDienst, SpringerDienst springerDienst, TurmDienst turmDienst) {
        ArrayList<Figur> figuren = new ArrayList<Figur>();
        for (int i = 1; i <= 8; i++) {
            figuren.add(bauerDienst.erzeugeBauer(1, schachspiel.getSchachbrett().getFeld(i, 2)));
            figuren.add(bauerDienst.erzeugeBauer(0, schachspiel.getSchachbrett().getFeld(i, 7)));
        }

        figuren.add(koenigDienst.erzeugeKoenig(1, schachspiel.getSchachbrett().getFeld(5,1)));
        figuren.add(koenigDienst.erzeugeKoenig(0, schachspiel.getSchachbrett().getFeld(5,8)));

        figuren.add(dameDienst.erzeugeDame(1, schachspiel.getSchachbrett().getFeld(4,1)));
        figuren.add(dameDienst.erzeugeDame(0, schachspiel.getSchachbrett().getFeld(4,8)));

        figuren.add(laeuferDienst.erzeugeLaeufer(1, schachspiel.getSchachbrett().getFeld(3,1)));
        figuren.add(laeuferDienst.erzeugeLaeufer(0, schachspiel.getSchachbrett().getFeld(3,8)));
        figuren.add(laeuferDienst.erzeugeLaeufer(1, schachspiel.getSchachbrett().getFeld(6,1)));
        figuren.add(laeuferDienst.erzeugeLaeufer(0, schachspiel.getSchachbrett().getFeld(6,8)));

        figuren.add(springerDienst.erzeugeSpringer(1, schachspiel.getSchachbrett().getFeld(2,1)));
        figuren.add(springerDienst.erzeugeSpringer(0, schachspiel.getSchachbrett().getFeld(2,8)));
        figuren.add(springerDienst.erzeugeSpringer(1, schachspiel.getSchachbrett().getFeld(7,1)));
        figuren.add(springerDienst.erzeugeSpringer(0, schachspiel.getSchachbrett().getFeld(7,8)));

        figuren.add(turmDienst.erzeugeTurm(1, schachspiel.getSchachbrett().getFeld(1,1)));
        figuren.add(turmDienst.erzeugeTurm(0, schachspiel.getSchachbrett().getFeld(1,8)));
        figuren.add(turmDienst.erzeugeTurm(1, schachspiel.getSchachbrett().getFeld(8,1)));
        figuren.add(turmDienst.erzeugeTurm(0, schachspiel.getSchachbrett().getFeld(8,8)));

        schachspiel.setFiguren(figuren);
    }

    public Schachspiel getSchachspiel() {
        return schachspiel;
    }

    public void klickAuswerten(int mausX, int mausY) {
        if (this.ausgewaehlteFigur != null) {
            this.fuehreZugAus(mausX, mausY);
        } else {
            this.waehleEineFigurAus(mausX, mausY);
        }
    }

    public void waehleEineFigurAus(int mausX, int mausY) {
        for (Figur figur : schachspiel.getFiguren()) {
            if (figurDienst.checkFigurGeklickt(figur, mausX, mausY)) {
                if (figur.getFarbe() == this.spielerAmZug) {
                    this.ausgewaehlteFigur = figur;
                    switch (figur.getClass().getSimpleName()) {
                        case "Bauer":
                            this.moeglicheZuegeDerFigur = bauerDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Bauer) figur);
                            break;
                        case "Dame":
                            this.moeglicheZuegeDerFigur = dameDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Dame) figur);
                            break;
                        case "Koenig":
                            this.moeglicheZuegeDerFigur = koenigDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Koenig) figur);
                            break;
                        case "Laeufer":
                            this.moeglicheZuegeDerFigur = laeuferDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Laeufer) figur);
                            break;
                        case "Springer":
                            this.moeglicheZuegeDerFigur = springerDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Springer) figur);
                            break;
                        case "Turm":
                            this.moeglicheZuegeDerFigur = turmDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Turm) figur);
                            break;
                    }
                    this.moeglicheZuegeDerFigur = this.getValideZuege(this.moeglicheZuegeDerFigur);
                }

                break;
            }
        }
    }

    public Figur getAusgewaelteFigur() {
        return this.ausgewaehlteFigur;
    }

    public ArrayList<Feld> getMoeglicheZuegeDerFigur() {
        return this.moeglicheZuegeDerFigur;
    }

    public ArrayList<Feld> getValideZuege(ArrayList<Feld> zuege) {
        ArrayList<Feld> valideZuege = new ArrayList<Feld>();
        ArrayList<Figur> kopieFiguren = (ArrayList<Figur>) schachspiel.getFiguren().clone();
        Feld startPosition = this.ausgewaehlteFigur.getPosition();
        for (Feld zug : zuege) {
            boolean geschlagen = false;
            Figur geschlageneFigur = null;
            int index = kopieFiguren.indexOf(this.ausgewaehlteFigur);
            Figur kopieFigur = kopieFiguren.get(index);
            kopieFiguren.get(index).setPosition(zug);
            for (Figur fg : schachspiel.getFiguren()) {
                if (fg.getPosition() == zug && fg.getFarbe() != this.spielerAmZug) {
                    geschlageneFigur = fg;
                    kopieFiguren.remove(fg);
                    geschlagen = true;
                }
            }
            if (!checkSchach(kopieFiguren, (this.spielerAmZug - 1) * (this.spielerAmZug - 1))) {
                kopieFigur.setPosition(startPosition);
                valideZuege.add(zug);
            }
            if (geschlagen) {
                kopieFiguren.add(geschlageneFigur);
            }
            this.ausgewaehlteFigur.setPosition(startPosition);
        }
        return valideZuege;
    }

    public void fuehreZugAus(int mausX, int mausY) {
        Feld ausgewaehltesFeld = selektiereAusgewaehltesFeld(mausX, mausY);
        boolean umsetzenMoeglich = this.moeglicheZuegeDerFigur.contains(ausgewaehltesFeld);

        if (umsetzenMoeglich) {
            //ArrayList<String> zuege = schachspiel.getZuege();
            for (Figur figur : schachspiel.getFiguren()) {
                if (figur == this.ausgewaehlteFigur) {
                    //int neueZeile = ausgewaehltesFeld.getZeile();
                    //int neueSpalte = ausgewaehltesFeld.getSpalte();

                    //int alteZeile = figur.getPosition().getZeile();
                    //int alteSpalte = figur.getPosition().getSpalte();

                    dokumentiereSchachzug(figur, ausgewaehltesFeld);
                    //String zug = figur.getAbkuerzung() + schachbrettDienst.zahlZuBuchstabe(alteSpalte) + Integer.toString(alteZeile);

                    /*if (pruefeFigurGeschlagen(ausgewaehltesFeld)) {
                        zug += "x";
                    } else {
                        zug += "-";
                    }
                    zug += schachbrettDienst.zahlZuBuchstabe(neueSpalte) + Integer.toString(neueZeile);*/

                    //Hier wird die Figur gesetzt
                    figur.setPosition(ausgewaehltesFeld);

                    if (checkSchach(schachspiel.getFiguren(), this.spielerAmZug)) {
                        //zug += "+";
                        /*if (checkMatt()) {
                            //zug += "+";
                        }*/
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

                    //zuege.add(zug);
                    //schachspiel.setZuege(zuege);

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
        this.ausgewaehlteFigur = null;
    }

    private void dokumentiereSchachzug(Figur figur, Feld ausgewaehltesFeld) {

        String schachzugAbkuerzung = figur.getAbkuerzung() + schachbrettDienst.zahlZuBuchstabe(figur.getPosition().getSpalte()) + figur.getPosition().getZeile();
        if (pruefeFigurGeschlagen(ausgewaehltesFeld)) {
            schachzugAbkuerzung += "x";
        } else {
            schachzugAbkuerzung += "-";
        }
        schachzugAbkuerzung += schachbrettDienst.zahlZuBuchstabe(ausgewaehltesFeld.getSpalte()) + ausgewaehltesFeld.getZeile();
        if (checkSchach(schachspiel.getFiguren(), this.spielerAmZug)) {
            schachzugAbkuerzung += "+";
            if (checkMatt()) {
                schachzugAbkuerzung += "+";
            }
        }

        if(figur.getFarbe() == 1){
            Schachzug schachzugWeiss = schachzugDienst.erstelleSchachzug(figur, schachzugAbkuerzung, ausgewaehltesFeld);
            this.spielzug = spielzugDienst.setzteSchachzugWeiss(spielzug, schachzugWeiss);
            schachspiel.addSpielzug(spielzug);
            return;
        }

        Schachzug schachzugSchwarz = schachzugDienst.erstelleSchachzug(figur, schachzugAbkuerzung,ausgewaehltesFeld);
        this.spielzug = spielzugDienst.setzteSchachzugSchwarz(spielzug, schachzugSchwarz);

        schachspiel.ueberarbeiteSpielzug(spielzug);

        this.spielzug = spielzugDienst.erstelleSpielzug(schachspiel.getSpielzuege());
    }

    private Feld selektiereAusgewaehltesFeld(int mausX, int mausY) {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                Feld tmp = schachspiel.getSchachbrett().getFeld(i, j);
                if(feldDienst.pruefeFeldGeklickt(mausX, mausY, tmp) != null){
                    return tmp;
                }
            }
        }

        return null;
    }

    private boolean pruefeFigurGeschlagen(Feld zielFeld) {

        for (Figur f : schachspiel.getFiguren()) {
            if (f.getPosition() == zielFeld && f.getFarbe() != ausgewaehlteFigur.getFarbe()) {
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

        for (Figur figur : figuren) {
            if (figur.getFarbe() == spieler) {
                ArrayList<Feld> mglZuege = new ArrayList<>();
                switch (figur.getClass().getSimpleName()) {
                    case "Bauer":
                        mglZuege = this.moeglicheZuegeDerFigur = bauerDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Bauer) figur);
                        break;
                    case "Dame":
                        mglZuege = this.moeglicheZuegeDerFigur = dameDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Dame) figur);
                        break;
                    case "Koenig":
                        mglZuege = this.moeglicheZuegeDerFigur = koenigDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Koenig) figur);
                        break;
                    case "Lauefer":
                        mglZuege = this.moeglicheZuegeDerFigur = laeuferDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Laeufer) figur);
                        break;
                    case "Springer":
                        mglZuege = this.moeglicheZuegeDerFigur = springerDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Springer) figur);
                        break;
                    case "Turm":
                        mglZuege = this.moeglicheZuegeDerFigur = turmDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Turm) figur);
                        break;
                }

                if (mglZuege.contains(koenigPosition)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkMatt() {
        for (Figur figur : schachspiel.getFiguren()) {
            ArrayList<Figur> kopieFiguren = (ArrayList<Figur>) schachspiel.getFiguren().clone();
            Feld startPosition = figur.getPosition();
            if (figur.getFarbe() != this.spielerAmZug) {
                ArrayList<Feld> mglZuege = new ArrayList<>();
                switch (figur.getClass().getSimpleName()) {
                    case "Bauer":
                        mglZuege = this.moeglicheZuegeDerFigur = bauerDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Bauer) figur);
                        break;
                    case "Dame":
                        mglZuege = this.moeglicheZuegeDerFigur = dameDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Dame) figur);
                        break;
                    case "Koenig":
                        mglZuege = this.moeglicheZuegeDerFigur = koenigDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Koenig) figur);
                        break;
                    case "Lauefer":
                        mglZuege = this.moeglicheZuegeDerFigur = laeuferDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Laeufer) figur);
                        break;
                    case "Springer":
                        mglZuege = this.moeglicheZuegeDerFigur = springerDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Springer) figur);
                        break;
                    case "Turm":
                        mglZuege = this.moeglicheZuegeDerFigur = turmDienst.getMoeglicheZuege(schachspiel.getFiguren(), schachspiel.getSchachbrett(), (Turm) figur);
                        break;
                }

                for (Feld zug : mglZuege) {
                    boolean geschlagen = false;
                    Figur geschlageneFigur = null;
                    int index = kopieFiguren.indexOf(figur);
                    Figur kopieFigur = kopieFiguren.get(index);
                    kopieFiguren.get(index).setPosition(zug);
                    for (Figur fg : schachspiel.getFiguren()) {
                        if (fg.getPosition() == zug && fg.getFarbe() == this.spielerAmZug) {
                            geschlageneFigur = fg;
                            kopieFiguren.remove(fg);
                            geschlagen = true;
                        }
                    }
                    if (!checkSchach(kopieFiguren, this.spielerAmZug)) {
                        kopieFigur.setPosition(startPosition);
                        return false;
                    }
                    if (geschlagen) {
                        kopieFiguren.add(geschlageneFigur);
                    }
                }
            }
            figur.setPosition(startPosition);
        }
        return true;
    }

    /*public void exportZuege() {
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
    }*/

    public Schachbrett getSchachbrett() {
        return schachspiel.getSchachbrett();
    }
}

