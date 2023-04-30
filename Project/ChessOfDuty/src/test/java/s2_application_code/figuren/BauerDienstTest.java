package s2_application_code.figuren;

import de.dhbw.chessofduty.s2_application_code.figuren.BauerDienst;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import de.dhbw.chessofduty.s3_domain_code.*;

import java.util.ArrayList;

public class BauerDienstTest {

    @Mock
    private Bauer mockBauer;

    @Mock
    private Schachbrett mockSchachbrett;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockSchachbrett = new Schachbrett();
    }

    @Test
    public void testErzeugeBauer() {
        Feld mockFeld = mock(Feld.class);
        when(mockBauer.getPosition()).thenReturn(mockFeld);


        BauerDienst bd = new BauerDienst();
        Bauer result = bd.erzeugeBauer(1, mockFeld);

        assertSame(result, mockBauer);
        verify(mockBauer).setFarbe(1);
        verify(mockBauer).setPosition(mockFeld);
    }

    @Test
    public void testGetMoeglicheZuege() {
        // mock input data
        ArrayList<Figur> figuren = new ArrayList<>();
        Bauer bauer = mock(Bauer.class);
        figuren.add(bauer);

        Feld aktuellePosition = mock(Feld.class);
        when(bauer.getPosition()).thenReturn(aktuellePosition);
        when(aktuellePosition.getZeile()).thenReturn(1);
        when(aktuellePosition.getSpalte()).thenReturn(1);

        when(bauer.getFarbe()).thenReturn(1);
        when(bauer.getStartposition()).thenReturn(mock(Feld.class));
        when(bauer.getDoppelschrittMoeglich()).thenReturn(true);

        when(mockSchachbrett.getFeld(1, 2)).thenReturn(mock(Feld.class));
        when(mockSchachbrett.getFeld(1, 3)).thenReturn(mock(Feld.class));

        // mock expected result
        ArrayList<Feld> expected = new ArrayList<>();
        expected.add(mockSchachbrett.getFeld(1, 2));
        expected.add(mockSchachbrett.getFeld(1, 3));

        // run the method under test
        BauerDienst bd = new BauerDienst();
        ArrayList<Feld> result = bd.getMoeglicheZuege(figuren, mockSchachbrett, bauer);

        // assert the result
        assertEquals(expected, result);
        verify(bauer).setDoppelschrittMoeglich(false);
        verify(mockSchachbrett, times(2)).getFeld(1, anyInt());
    }

}