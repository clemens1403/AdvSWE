public void mousePressed() {
    switch (benutzeroberflaeche.getStatus()) {
        case "Start":
            ArrayList<Knopf> knoepfeStart = benutzeroberflaeche.getStartKnoepfe();
            for (Knopf k : knoepfeStart) {
                if (k.pruefeMausPosition()) {
                    switch (k.getId()) {
                        case "Spielen":
                            this.schachspiel = schachspielDienst.erstelleSchachspiel();
                            schachspielKontrollierer.erstelleNeuesSpiel(schachspiel);
                            benutzeroberflaeche.setStatus("Spiel");
                            break;
                        default:
                            break;
                    }
                }
            }
            break;
        case "Spiel":
            schachspielKontrollierer.klickAuswerten(mouseX, mouseY);
            ArrayList<Knopf> knoepfeSpiel = benutzeroberflaeche.getSpielKnoepfe();
            for (Knopf k : knoepfeSpiel) {
                if (k.pruefeMausPosition()) {
                    switch (k.getId()) {
                        case "neustart":
                            this.schachspiel = schachspielDienst.erstelleSchachspiel();
                            schachspielKontrollierer.erstelleNeuesSpiel(schachspiel);
                            break;
                        case "Start":
                            benutzeroberflaeche.setStatus("Start");
                            break;
                        case "export":
                            spielzugRepositoryBruecke.dokumentiere(schachspiel);
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