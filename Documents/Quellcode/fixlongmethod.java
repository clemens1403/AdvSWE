public ArrayList<Feld> getMoeglicheZuege(ArrayList<Figur> figuren, Schachbrett schachbrett, Bauer bauer) {

    ArrayList<Feld> moeglicheZuege = new ArrayList<>();

    Feld aktuellePosition = bauer.getPosition();
    System.out.println("Aktuelles Feld: " + aktuellePosition);
    if (aktuellePosition != bauer.getStartposition()) {
        bauer.setDoppelschrittMoeglich(false);
    }
    int aktuelleZeile = aktuellePosition.getZeile();
    int aktuelleSpalte = aktuellePosition.getSpalte();
    int richtung = bauer.getFarbe()==0?-1:1;

    boolean einzelschritt;
    boolean doppelschritt = bauer.getDoppelschrittMoeglich();

    if (berechneSchrittMoeglich(bauer, figuren, 1)) {
        einzelschritt = true;
        if (berechneSchrittMoeglich(bauer, figuren, 2) && doppelschritt) {
            doppelschritt = true;
        } else {
            doppelschritt = false;
        }
    } else {
        einzelschritt = false;
    }

    for (Figur f : figuren) {
        if (istFigurSchlagbar(bauer, f, richtung)) moeglicheZuege.add(f.getPosition());
    }
    
    if (einzelschritt) {
        moeglicheZuege.add(schachbrett.getFeld(aktuelleSpalte, aktuelleZeile + 1 * richtung));
    }
    if (doppelschritt) {
        moeglicheZuege.add(schachbrett.getFeld(aktuelleSpalte, aktuelleZeile + 2 * richtung));
    }

    for (Feld eintrag : moeglicheZuege) {
        System.out.println("(" + eintrag.getSpalte() + ", " + eintrag.getZeile() + ")");
    }

    return moeglicheZuege;
}

private Boolean istFigurSchlagbar(Bauer bauer, Figur figur, int richtung) {
    int bauerSpalte = bauer.getPosition().getSpalte();
    int bauerZeile = bauer.getPosition().getZeile();

    int figurSpalte = figur.getPosition().getSpalte();
    int figurZeile = figur.getPosition().getZeile();

    if (bauerSpalte - 1 == figurSpalte || bauerSpalte + 1 == figurSpalte) {
        if (bauerZeile == figurZeile - 1 * richtung) {
            if (bauer.getFarbe() != figur.getFarbe()) {
                return true;
            }
        }
    }
    return false;
}

public Boolean berechneSchrittMoeglich(Bauer bauer, ArrayList<Figur> figuren, int schrittWeite) {
    int bauerSpalte = bauer.getPosition().getSpalte();
    int bauerZeile = bauer.getPosition().getZeile();
    int schritt = bauer.getFarbe()==1? schrittWeite *-1 : schrittWeite;
    for (Figur f : figuren) {
        Feld positionFigur = f.getPosition();
        int zeileFigur = positionFigur.getZeile();
        int spalteFigur = positionFigur.getSpalte();
        if (bauerSpalte == spalteFigur) {
            if (bauerZeile == zeileFigur + schritt) {
                return false;
            }
        }
    }
    return true;
}