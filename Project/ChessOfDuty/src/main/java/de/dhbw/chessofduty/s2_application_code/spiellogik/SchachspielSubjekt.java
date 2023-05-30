package de.dhbw.chessofduty.s2_application_code.spiellogik;

public interface SchachspielSubjekt {

    void registriereBeobachter(SchachspielBeobachter schachspielBeobachter);

    void unregistriereBeobachter(SchachspielBeobachter schachspielBeobachter);

    void benachrichtigeBeobachter();

}