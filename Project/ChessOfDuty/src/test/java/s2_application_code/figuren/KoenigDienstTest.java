package s2_application_code.figuren;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import de.dhbw.chessofduty.s2_application_code.figuren.KoenigDienst;
import de.dhbw.chessofduty.s3_domain_code.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KoenigDienstTest {

    private KoenigDienst koenigDienst;
    private Schachbrett schachbrettMock;
    private Koenig koenigMock;

    @BeforeEach
    public void setUp() {
        koenigDienst = new KoenigDienst();
        schachbrettMock = new Schachbrett();
        koenigMock = mock(Koenig.class);
        when(koenigMock.getPosition()).thenReturn(new Feld(1, 1)); // Beispielposition
    }

    @Test
    public void erzeugeKoenigTest() {
        Koenig koenig = koenigDienst.erzeugeKoenig(1, new Feld(1, 1)); // weißer König an Startposition
        assertEquals(1, koenig.getFarbe());
        assertEquals(new Feld(1, 1), koenig.getPosition());
    }

    @Test
    public void getMoeglicheZuegeTest() {
        Dame figur1 = new Dame(1, new Feld(2, 2));
        Dame figur2 = new Dame(0, new Feld(1, 2));
        ArrayList<Figur> figuren = new ArrayList<>(Arrays.asList(figur1, figur2));

        when(koenigMock.getFarbe()).thenReturn(1);

        ArrayList<Feld> moeglicheZuege = new ArrayList<>(Arrays.asList(new Feld(1, 2), new Feld(2, 1)));

        assertEquals(moeglicheZuege, koenigDienst.getMoeglicheZuege(figuren, schachbrettMock, koenigMock));
    }

}

