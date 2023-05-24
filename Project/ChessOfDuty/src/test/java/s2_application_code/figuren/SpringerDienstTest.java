package s2_application_code.figuren;

import de.dhbw.chessofduty.s2_application_code.figuren.SpringerDienst;
import de.dhbw.chessofduty.s3_domain_code.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SpringerDienstTest {

    private SpringerDienst springerDienst;

    @Mock
    private Springer mockSpringer;

    private Schachbrett schachbrett;

    @Mock
    private Bauer bauer;

    @Mock
    private Laeufer laeufer;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        springerDienst = new SpringerDienst();
        schachbrett = new Schachbrett();
    }

    @Test
    void erzeugeSpringerTest() {
        int farbe = 1;
        Feld startPosition = new Feld(1, 1);

        Springer result = springerDienst.erzeugeSpringer(farbe, startPosition);

        assertEquals(farbe, result.getFarbe());
        assertEquals(startPosition, result.getPosition());
    }

    @Test
    public void testGetMoeglicheZuegeTest() {
        Springer springer = new Springer(1, new Feld(2,1));
        ArrayList<Figur> figuren = new ArrayList<>();
        figuren.add(bauer);
        figuren.add(laeufer);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        moeglicheZuege.add(new Feld(1,3));
        moeglicheZuege.add(new Feld(4,2));

        SpringerDienst springerDienst = new SpringerDienst();

        // Mock Figuren
        when(bauer.getPosition()).thenReturn(new Feld(4,2));
        when(bauer.getFarbe()).thenReturn(0);

        when(laeufer.getPosition()).thenReturn(new Feld(3,3));
        when(laeufer.getFarbe()).thenReturn(1);

        // Test
        ArrayList<Feld> result = springerDienst.getMoeglicheZuege(figuren, schachbrett, springer);

        assertEquals(moeglicheZuege, result);
    }

}