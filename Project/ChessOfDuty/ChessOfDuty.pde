ArrayList<Feld> felder;
GUI gui;

void setup(){
  size(800,800);
  background(0);
  felder = new ArrayList<Feld>();
  for(int i = 1; i <= 8; i++){
    for(int j = 1; j<= 8; j++){
      felder.add(new Feld(i,j));
    }
  }


  gui = new GUI(felder);
}

void draw(){
  gui.render();
  
}

// Mausclick-Verarbeitung

void mousePressed(){
  switch(gui.getStatus()){
    case "Start":
      ArrayList<Knopf> knoepfe = gui.getStartKnoepfe();
      for(Knopf k : knoepfe){
        if(k.checkMausPosition()){
          switch(k.getId()){
            case "Spielen":
              gui.setStatus("Spiel");
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