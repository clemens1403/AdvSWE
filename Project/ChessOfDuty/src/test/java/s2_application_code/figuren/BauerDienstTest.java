package s2_application_code.figuren;

import de.dhbw.chessofduty.s2_application_code.figuren.BauerDienst;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import de.dhbw.chessofduty.s3_domain_code.*;

import java.util.ArrayList;

public class BauerDienstTest {

    @Mock
    private Turm turm;

    @Mock
    private Bauer bauer;
    @Mock
    private Bauer bauer2;

    @Mock
    private Laeufer laeufer;

    private BauerDienst bauerDienst;

    private Schachbrett schachbrett;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        bauerDienst = new BauerDienst();
        schachbrett = new Schachbrett();
    }

    @Test
    public void erzeugeBauerTest() {
        int farbe = 1;
        Feld startPosition = new Feld(4,1);
        Bauer ergebnis = bauerDienst.erzeugeBauer(farbe, startPosition);

        Assertions.assertNotNull(ergebnis);
        Assertions.assertEquals(ergebnis.getFarbe(), farbe);
        Assertions.assertEquals(ergebnis.getPosition(), startPosition);
    }

    @Test
    public void getMoeglicheZuegeTest() {
        ArrayList<Figur> figuren = new ArrayList<>();
        figuren.add(bauer);
        figuren.add(bauer2);
        figuren.add(laeufer);
        figuren.add(turm);
        ArrayList<Feld> moeglicheZuegeBauer = new ArrayList<>();
        ArrayList<Feld> moeglicheZuegeBauer2 = new ArrayList<>();

        // Mock Figuren
        when(bauer.getPosition()).thenReturn(new Feld(1,8));
        when(bauer.getFarbe()).thenReturn(0);
        when(bauer.getDoppelschrittMoeglich()).thenReturn(true);

        when(bauer2.getPosition()).thenReturn(new Feld(1,1));
        when(bauer2.getFarbe()).thenReturn(1);
        when(bauer2.getDoppelschrittMoeglich()).thenReturn(true);

        when(laeufer.getPosition()).thenReturn(new Feld(1,3));
        when(laeufer.getFarbe()).thenReturn(1);

        when(turm.getPosition()).thenReturn(new Feld(2,2));
        when(turm.getFarbe()).thenReturn(0);

        //erwartete Felder hinzufügen
        moeglicheZuegeBauer.add(new Feld(1,7));
        moeglicheZuegeBauer.add(new Feld(1,6));

        moeglicheZuegeBauer2.add(new Feld(2,2));
        moeglicheZuegeBauer2.add(new Feld(1,2));

        
        ArrayList<Feld> ergebnisBauer = bauerDienst.getMoeglicheZuege(figuren, schachbrett, bauer);
        ArrayList<Feld> ergebnisBauer2 = bauerDienst.getMoeglicheZuege(figuren, schachbrett, bauer2);

        Assertions.assertEquals(ergebnisBauer, moeglicheZuegeBauer);
        Assertions.assertEquals(ergebnisBauer2, moeglicheZuegeBauer2);
    }

}