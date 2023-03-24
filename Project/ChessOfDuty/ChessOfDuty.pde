Schachspiel schachspiel;
GUI gui;

void setup(){
  size(1200,1200);
  background(0);

  schachspiel = new Schachspiel();
  gui = new GUI(schachspiel);
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
              schachspiel = new Schachspiel();
              gui.setStatus("Spiel");
            default:
              break;
          }
        }
      }
      break;
    case "Spiel":
      schachspiel.waehleEineFigurAus();
      gui.setStatus("Zug");
      break;
    case "Zug":
      schachspiel.fuehreZugAus();
      schachspiel.renderSchachspiel();
      //gui.setStatus("Spiel");
      break;
    default:
      break;
  }
}