ArrayList<Feld> felder;

void setup(){
  size(800,800);
  background(0);
  felder = new ArrayList<Feld>();
  for(int i = 1; i <= 8; i++){
    for(int j = 1; j<= 8; j++){
      felder.add(new Feld(i,j));
    }
  }
}

void draw(){
  //fill(123, 231, 0);
  //ellipse(mouseX,mouseY,5,5);  
  for(int i = 0; i< felder.size(); i++){
    felder.get(i).show();
  }
}