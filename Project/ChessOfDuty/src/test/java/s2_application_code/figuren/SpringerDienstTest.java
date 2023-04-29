package s2_application_code.figuren;

import de.dhbw.chessofduty.s2_application_code.figuren.SpringerDienst;
import de.dhbw.chessofduty.s3_domain_code.Feld;
import de.dhbw.chessofduty.s3_domain_code.Figur;
import de.dhbw.chessofduty.s3_domain_code.Schachbrett;
import de.dhbw.chessofduty.s3_domain_code.Springer;
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
    private Schachbrett mockSchachbrett;

    @Mock
    private Springer mockSpringer;

    @Mock
    private Schachbrett schachbrett;

    @Mock
    private Figur figur;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        springerDienst = new SpringerDienst();
    }

    @Test
    void testErzeugeSpringer() {
        int farbe = 1;
        Feld startPosition = new Feld(1, 1);

        Springer result = springerDienst.erzeugeSpringer(farbe, startPosition);

        assertEquals(farbe, result.getFarbe());
        assertEquals(startPosition, result.getPosition());
    }

    @Test
    public void testGetMoeglicheZuege() {
        Springer springer = new Springer(1, new Feld(3,3));
        ArrayList<Figur> figuren = new ArrayList<>();
        figuren.add(figur);
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        Feld feld = new Feld(2,1);
        moeglicheZuege.add(feld);

        SpringerDienst springerDienst = new SpringerDienst();

        // Mock Schachbrett
        when(schachbrett.getFeld(anyInt(), anyInt())).thenReturn(feld);

        // Mock Figur
        when(figur.getPosition()).thenReturn(new Feld(4,4));
        when(figur.getFarbe()).thenReturn(2);

        // Test
        ArrayList<Feld> result = springerDienst.getMoeglicheZuege(figuren, schachbrett, springer);

        assertEquals(moeglicheZuege, result);
    }

}