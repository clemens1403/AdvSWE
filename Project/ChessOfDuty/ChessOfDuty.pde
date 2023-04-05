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
      ArrayList<Knopf> knoepfeStart = gui.getStartKnoepfe();
      for(Knopf k : knoepfeStart){
        if(k.checkMausPosition()){
          switch(k.getId()){
            case "Spielen":
              schachspiel = new Schachspiel();
              gui.setStatus("Spiel");
              break;
            default:
              break;
          }
        }
      }
      break;
    case "Spiel":
      schachspiel.klickAuswerten();
      gui.setSchachspiel(schachspiel);
      ArrayList<Knopf> knoepfeSpiel = gui.getSpielKnoepfe();
      for(Knopf k : knoepfeSpiel){
        if(k.checkMausPosition()){
          switch(k.getId()){
            case "neustart":
              schachspiel = new Schachspiel();
              gui.setSchachspiel(schachspiel);
              break;
            case "Start":
              gui.setStatus("Start");
              break;
            case "export":
              schachspiel.exportZuege();
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