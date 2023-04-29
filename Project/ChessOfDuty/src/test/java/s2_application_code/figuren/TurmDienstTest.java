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

    @Mock
    private Schachbrett schachbrett;

    @Mock
    private Turm turm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        turmDienst = new TurmDienst();
    }

    @Test
    void erzeugeTurm() {
        int farbe = 0;
        Feld startPosition = mock(Feld.class);
        Turm result = turmDienst.erzeugeTurm(farbe, startPosition);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getFarbe(), farbe);
        Assertions.assertEquals(result.getPosition(), startPosition);
    }

    @Test
    void getMoeglicheZuege() {
        ArrayList<Figur> figuren = new ArrayList<>();
        figuren.add(mock(Figur.class));
        ArrayList<Feld> expected = new ArrayList<>();
        expected.add(mock(Feld.class));
        expected.add(mock(Feld.class));
        expected.add(mock(Feld.class));
        expected.add(mock(Feld.class));


        when(turm.getPosition()).thenReturn(mock(Feld.class));
        when(turm.getPosition().getSpalte()).thenReturn(3);
        when(turm.getPosition().getZeile()).thenReturn(3);

        ArrayList<Feld> result = turmDienst.getMoeglicheZuege(figuren, schachbrett, turm);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), 16);
        Assertions.assertEquals(result, expected);
    }
}