package s2_application_code.figuren;

import de.dhbw.chessofduty.s2_application_code.figuren.TurmDienst;
import de.dhbw.chessofduty.s3_domain_code.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

class TurmDienstTest {

    private TurmDienst turmDienst;
    private Schachbrett schachbrett;
    @Mock
    private Turm turm;

    @Mock
    private Bauer bauer;

    @Mock
    private Laeufer laeufer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        turmDienst = new TurmDienst();
        schachbrett = new Schachbrett();
    }

    @Test
    void erzeugeTurm() {
        int farbe = 1;
        Feld startPosition = new Feld(4,1);
        Turm ergebnis = turmDienst.erzeugeTurm(farbe, startPosition);

        Assertions.assertNotNull(ergebnis);
        Assertions.assertEquals(ergebnis.getFarbe(), farbe);
        Assertions.assertEquals(ergebnis.getPosition(), startPosition);
    }

    @Test
    void getMoeglicheZuege() {
        ArrayList<Figur> figuren = new ArrayList<>();
        figuren.add(bauer);
        figuren.add(laeufer);
        figuren.add(turm);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        // Mock Figuren
        when(bauer.getPosition()).thenReturn(new Feld(1,1));
        when(bauer.getFarbe()).thenReturn(0);

        when(laeufer.getPosition()).thenReturn(new Feld(7,1));
        when(laeufer.getFarbe()).thenReturn(1);

        when(turm.getPosition()).thenReturn(new Feld(4,1));
        when(turm.getFarbe()).thenReturn(0);

        //erwartete Felder hinzufügen
        moeglicheZuege.add(new Feld(4,2));
        moeglicheZuege.add(new Feld(4,3));
        moeglicheZuege.add(new Feld(4,4));
        moeglicheZuege.add(new Feld(4,5));
        moeglicheZuege.add(new Feld(4,6));
        moeglicheZuege.add(new Feld(4,7));
        moeglicheZuege.add(new Feld(4,8));
        moeglicheZuege.add(new Feld(3,1));
        moeglicheZuege.add(new Feld(2,1));
        moeglicheZuege.add(new Feld(5,1));
        moeglicheZuege.add(new Feld(6,1));
        moeglicheZuege.add(new Feld(7,1));


        ArrayList<Feld> ergebnis = turmDienst.getMoeglicheZuege(figuren, schachbrett, turm);

        Assertions.assertEquals(ergebnis, moeglicheZuege);
    }
}