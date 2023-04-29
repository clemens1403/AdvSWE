package s2_application_code.figuren;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import de.dhbw.chessofduty.s2_application_code.figuren.LaeuferDienst;
import de.dhbw.chessofduty.s3_domain_code.Feld;
import de.dhbw.chessofduty.s3_domain_code.Figur;
import de.dhbw.chessofduty.s3_domain_code.Laeufer;
import de.dhbw.chessofduty.s3_domain_code.Schachbrett;

public class LaeuferDienstTest {

    @Mock
    private Schachbrett schachbrettMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testErzeugeLaeufer() {
        LaeuferDienst laeuferDienst = new LaeuferDienst();
        Feld startPosition = mock(Feld.class);

        Laeufer laeufer = laeuferDienst.erzeugeLaeufer(0, startPosition);

        assertNotNull(laeufer);
        assertEquals(0, laeufer.getFarbe());
        assertEquals(startPosition, laeufer.getPosition());
    }

    @Test
    public void testGetMoeglicheZuege() {
        LaeuferDienst laeuferDienst = new LaeuferDienst();
        Feld startPosition = mock(Feld.class);
        Laeufer laeufer = new Laeufer(0, startPosition);
        Figur figur1 = mock(Figur.class);
        Figur figur2 = mock(Figur.class);
        ArrayList<Figur> figuren = new ArrayList<>();
        figuren.add(figur1);
        figuren.add(figur2);
        when(figur1.getFarbe()).thenReturn(1);
        when(figur2.getFarbe()).thenReturn(0);
        when(startPosition.getSpalte()).thenReturn(2);
        when(startPosition.getZeile()).thenReturn(3);

        ArrayList<Feld> moeglicheZuege = laeuferDienst.getMoeglicheZuege(figuren, schachbrettMock, laeufer);

        assertNotNull(moeglicheZuege);
        assertEquals(12, moeglicheZuege.size());
    }

}

