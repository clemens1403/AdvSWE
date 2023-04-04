public class GUI {
    private String status;
    private ArrayList<Knopf> startKnoepfe = new ArrayList<Knopf>();
    private ArrayList<ArrayList<Feld>> felder;
    private Schachspiel schachspiel;

    public GUI(Schachspiel schachspiel){
        this.schachspiel = schachspiel;
        status = "Start";

        Knopf spielKnopf = new Knopf("Spielen", width/2, height/2, 300, 150, "Spielen", color(200), color(230), color(0), 30);
        startKnoepfe.add(spielKnopf);
    }

    public void render(){
        switch(status){
            case "Start":
                renderStartAnsicht();
                break;
            case "Spiel":
                renderSpiel();
                break;
            case "Zug":
                renderSpiel();
                break;
            default:
                renderStartAnsicht();
                break;
        }

    }

    public void renderStartAnsicht(){
        background(51);

        //Titel
        push();
        fill(255);
        textSize(50);
        text("Chess Of Duty", 100, 100);
        pop();

        for(Knopf k : startKnoepfe){
            k.render();
        } 
    }

    public void renderSpiel(){      
        background(51);  

        //Titel
        push();
        fill(255);
        textSize(50);
        text("Chess Of Duty", 100, 100);
        pop();
        this.schachspiel.renderSchachspiel();
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public ArrayList<Knopf> getStartKnoepfe(){
        return startKnoepfe;
    }

    public void setSchachspiel(Schachspiel schachspiel){
        this.schachspiel = schachspiel;
    }

}

public class Knopf{
    private String id;
    private int x;
    private int y;
    private int breite;
    private int hoehe;
    private String text;
    private color hintergrund;
    private color hoverFarbe;
    private color textFarbe;
    private int textGroesse;
    
    public Knopf(String id, int x, int y, int breite, int hoehe, String text, color hintergrund, color hoverFarbe, color textFarbe, int textGroesse){
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
    
    public void render(){
        push();
        if(checkMausPosition()){
            fill(hoverFarbe);
        }else{
            fill(hintergrund);
        }
        rectMode(CENTER);
        rect(x,y,breite,hoehe);
        fill(textFarbe);
        textAlign(CENTER, CENTER);
        textSize(textGroesse);
        text(text, x, y);
        pop();
    }

    public boolean checkMausPosition(){
        if(mouseX > x - (breite/2) && mouseX < x + (breite/2)){
            if(mouseY > y - (hoehe/2) && mouseY < y + (hoehe/2)){
                return true;
            }
        }
        return false;
    }

    public String getId(){
        return id;
    }
    
}