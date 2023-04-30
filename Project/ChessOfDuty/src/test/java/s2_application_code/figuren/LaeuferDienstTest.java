package s2_application_code.figuren;

import de.dhbw.chessofduty.s3_domain_code.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import de.dhbw.chessofduty.s2_application_code.figuren.LaeuferDienst;

public class LaeuferDienstTest {


    private Schachbrett schachbrettMock;

    @Mock
    private Turm turm;

    @Mock
    private Bauer bauer;
    @Mock
    private Laeufer laeufer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        schachbrettMock = new Schachbrett();
    }

    @Test
    public void testErzeugeLaeufer() {
        LaeuferDienst laeuferDienst = new LaeuferDienst();
        Feld startPosition = new Feld(1,1);

        Laeufer laeufer = laeuferDienst.erzeugeLaeufer(0, startPosition);

        assertNotNull(laeufer);
        assertEquals(0, laeufer.getFarbe());
        assertEquals(startPosition, laeufer.getPosition());
    }

    @Test
    public void testGetMoeglicheZuege() {
        LaeuferDienst laeuferDienst = new LaeuferDienst();
        ArrayList<Figur> figuren = new ArrayList<>();
        figuren.add(bauer);
        figuren.add(turm);
        figuren.add(laeufer);

        ArrayList<Feld> moeglicheZuege = new ArrayList<>();

        moeglicheZuege.add(new Feld(3,3));
        moeglicheZuege.add(new Feld(4,4));
        moeglicheZuege.add(new Feld(1,3));
        moeglicheZuege.add(new Feld(3,1));

        when(bauer.getPosition()).thenReturn(new Feld(1,1));
        when(bauer.getFarbe()).thenReturn(1);

        when(laeufer.getPosition()).thenReturn(new Feld(2,2));
        when(laeufer.getFarbe()).thenReturn(1);

        when(turm.getPosition()).thenReturn(new Feld(4,4));
        when(turm.getFarbe()).thenReturn(0);

        ArrayList<Feld> ergebnis = laeuferDienst.getMoeglicheZuege(figuren, schachbrettMock, laeufer);

        assertNotNull(moeglicheZuege);
        assertEquals( moeglicheZuege, ergebnis);
    }

}

