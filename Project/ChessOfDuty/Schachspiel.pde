import java.util.Date;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Schachspiel {
    private UUID gameID;

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

    private Schachzug aktuellerSchachzug = null;
    
    public Schachspiel(){
        this.gameID = UUID.randomUUID();
        
        //Schachbrett bestehend aus 8x8 Feldern wird instanziiert
        this.schachbrett = new Schachbrett();
        this.spielerAmZug = 1;
        
        //Figuren des Schachspiels werden instanziiert
        this.initialisiereFiguren();

    }

    public Schachbrett getSchachbrett(){
        return this.schachbrett;
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

    public void renderSchachspiel(){
        schachbrett.renderSchachbrett();

        this.renderZuege();
        this.renderGeschlageneFiguren();

        for(Figur f : this.figuren){
            f.render();

            if(schachWeiss && f.getAbkuerzung() == "K" && f.getFarbe() == 1){
                Feld feld = f.getPosition();
                push();
                fill(200,100,100,200);
                rect(feld.getX(), feld.getY(), feld.getGroesse(), feld.getGroesse());
                pop();
            }else if(schachSchwarz && f.getAbkuerzung() == "K" && f.getFarbe() == 0) {
                Feld feld = f.getPosition();
                push();
                fill(200,100,100,200);
                rect(feld.getX(), feld.getY(), feld.getGroesse(), feld.getGroesse());
                pop();
            }
        }

        if(this.ausgewaehltFigur != null){
            Feld f = this.ausgewaehltFigur.getPosition();
            push();
            fill(100,200,100,200);
            rect(f.getX(), f.getY(), f.getGroesse(), f.getGroesse());
            pop();
        }

        for(Feld f: this.moeglicheZuegeDerFigur){
            push();
            stroke(200,100,100);
            strokeWeight(7);
            point(f.getX() + f.getGroesse()/2, f.getY() + f.getGroesse()/2);
            pop();
        }

    }

    public void renderGeschlageneFiguren(){
        push();
        fill(160,82,45);
        String textS = "";
        for(Figur f : geschlageneFigurenSchwarz){
            textS += f.getAbkuerzung();
        }
        textAlign(LEFT, CENTER);
        textSize(20);
        text(textS, 200, 1100);
        pop();

        push();
        fill(169, 172, 176);
        String textW = "";
        for(Figur f : geschlageneFigurenWeiss){
            textW += f.getAbkuerzung();
        }
        textAlign(RIGHT, CENTER);
        textSize(20);
        text(textW, 1000, 1100);
        pop();
    }

    public void renderZuege(){
        push();
        fill(0);
        textSize(40);
        textAlign(CENTER,CENTER);
        text("Züge:", width - 150, 200);
        pop();

        push();
        fill(200);
        textSize(15);
        textAlign(LEFT, TOP);
        String text = "";
        for(String zug : this.zuege){
            int index = this.zuege.indexOf(zug);
            if(index % 2 == 0){
                int spielzug = index/2 + 1;
                text += Integer.toString(spielzug) + ":";
            }
            text += zug + ";";
            if(index % 2 == 1){
                text+="\n";
            }
        }
        text(text, width-185, 300);
        pop();
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
                    print(this.moeglicheZuegeDerFigur);
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
                    print("Geschlagene Figur: " + fg.getAbkuerzung() + "\n");
                    kopieFiguren.remove(fg);
                    geschlagen = true;
                }
            }
            if(!checkSchach(kopieFiguren, (this.spielerAmZug-1)*(this.spielerAmZug-1))){        
                kopieFigur.setPosition(startPosition);
                print("\nmove valid");   
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

                    String zug = f.getAbkuerzung() + schachbrett.integerZuBuchstabe(alteSpalte) + Integer.toString(alteZeile);
                    //Überprüfung, ob etwas geschlagen wurde, oder nicht
                    if(pruefeFigurGeschlagen(ausgewaehltesFeld)){
                        zug += "x";
                    }else{
                        zug += "-";
                    }
                    zug += schachbrett.integerZuBuchstabe(neueSpalte) + Integer.toString(neueZeile);

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
                if(tmp.checkFeldGeklickt() != null){
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
        print("Schlage Figur");
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
                print("Königposition: \n");
                print(koenigPosition);
                if(mglZuege.contains(koenigPosition)){
                    print("Schach!");
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
                            print("Geschlagene Figur: " + fg.getAbkuerzung() + "\n");
                            kopieFiguren.remove(fg);
                            geschlagen = true;
                        }
                    }
                    if(!checkSchach(kopieFiguren, this.spielerAmZug)){
                        
                        print(kopieFigur.getAbkuerzung());
                        print(kopieFigur.getPosition().getSpalte());
                        print(kopieFigur.getPosition().getZeile());
                        kopieFigur.setPosition(startPosition);
                        print("\nno Mate");
                        return false;
                    }
                    if(geschlagen){
                        kopieFiguren.add(geschlageneFigur);
                    }
                }
            }
            f.setPosition(startPosition);       
        }
        print("Schachmatt");
        return true;
    }

    private void exportZuege(){
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
        print(fileNameString);
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
        String text = "";
        for(String zug : this.zuege){
            int index = this.zuege.indexOf(zug);
            if(index % 2 == 0){
                int spielzug = index/2 + 1;
                text += Integer.toString(spielzug) + ":";
            }
            text += zug + ";";
            if(index % 2 == 1){
                text+="\n";
            }
        }
        try {

            Path filePath = Files.writeString(p, text);
            String s = Files.readString(filePath);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
