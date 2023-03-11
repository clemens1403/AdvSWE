public class GUI {
    private String status;
    private Knopf startKnopf = new Knopf(width/2, height/2, 300, 150, "Spielen", color(200), color(230), color(0), 30);
    private ArrayList<Feld> felder;

    public GUI(ArrayList<Feld> felder){
        status = "Start";

        this.felder = felder;
    }

    public void render(){
        switch(status){
            case "Start":
                renderStartAnsicht();
                break;
            case "Spiel":
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

        startKnopf.render();
        
    }

    public void renderSpiel(){
        for(int i = 0; i< felder.size(); i++){
            felder.get(i).show();
        }
    }

}

public class Knopf{
    private int x;
    private int y;
    private int breite;
    private int hoehe;
    private String text;
    private color hintergrund;
    private color hoverFarbe;
    private color textFarbe;
    private int textGroesse;
    
    public Knopf(int x, int y, int breite, int hoehe, String text, color hintergrund, color hoverFarbe, color textFarbe, int textGroesse){
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
}