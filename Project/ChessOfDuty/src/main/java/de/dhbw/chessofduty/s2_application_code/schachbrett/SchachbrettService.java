package de.dhbw.chessofduty.s2_application_code.schachbrett;

import de.dhbw.chessofduty.s3_domain_code.Feld;
import de.dhbw.chessofduty.s3_domain_code.Schachbrett;
import processing.core.PGraphics;

import static processing.core.PConstants.CENTER;

public class SchachbrettService {

    public Schachbrett createSchachbrett() {
        return new Schachbrett();
    }


    public String zahlZuBuchstabe(int i){

        if(i < 0){
            return null;
        }
        String s = Integer.toString(i, 26);

        char[] buchstaben = s.toCharArray();

        StringBuilder ergebnis = new StringBuilder();
        for(char b : buchstaben){
            int x = Integer.parseInt(Character.valueOf(b).toString(), 26);
            ergebnis.append((char) (x + 'A'));
        }

        return ergebnis.toString();
    }
}
