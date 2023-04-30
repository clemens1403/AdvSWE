package s2_application_code.figuren;

import de.dhbw.chessofduty.s2_application_code.figuren.DameDienst;
import de.dhbw.chessofduty.s3_domain_code.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DameDienstTest {

    @Mock
    private Schachbrett schachbrett;

    @Mock
    private Dame dame;

    @Mock
    private Figur figur;

    private DameDienst dameDienst;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        schachbrett = new Schachbrett();
        dameDienst = new DameDienst();
    }

    @Test
    public void testErzeugeDame() {
        Feld startPosition = new Feld(3, 3);
        Dame expected = new Dame(1, startPosition);
        Dame actual = dameDienst.erzeugeDame(1, startPosition);
        assertEquals(expected.getFarbe(), actual.getFarbe());
        assertEquals(expected.getPosition(), actual.getPosition());
    }

    @Test
    public void testGetMoeglicheZuege() {
        ArrayList<Feld> expected = new ArrayList<>();
        Feld feld = new Feld(1, 1);
        expected.add(feld);
        when(dame.getPosition()).thenReturn(feld);
        when(figur.getPosition()).thenReturn(feld);
        when(figur.getFarbe()).thenReturn(0);

        ArrayList<Feld> actual = dameDienst.getMoeglicheZuege(new ArrayList<Figur>(){{add(dame);}}, schachbrett, dame);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0), actual.get(0));
    }
}