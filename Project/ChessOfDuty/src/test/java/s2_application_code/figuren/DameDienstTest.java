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


    private Schachbrett schachbrett;

    @Mock
    private Dame dame;

    @Mock
    private Bauer bauer;
    @Mock
    private Laeufer laeufer;

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
        ArrayList<Feld> moeglicheZuege = new ArrayList<>();
        ArrayList<Figur> figuren = new ArrayList<>();
        figuren.add(dame);
        figuren.add(bauer);
        figuren.add(laeufer);

        moeglicheZuege.add(new Feld(2,2));
        moeglicheZuege.add(new Feld(3,3));
        moeglicheZuege.add(new Feld(4,4));
        moeglicheZuege.add(new Feld(5,5));
        moeglicheZuege.add(new Feld(6,6));
        moeglicheZuege.add(new Feld(7,7));
        moeglicheZuege.add(new Feld(8,8));
        moeglicheZuege.add(new Feld(1,2));
        moeglicheZuege.add(new Feld(1,3));


        when(dame.getPosition()).thenReturn(new Feld(1,1));
        when(dame.getFarbe()).thenReturn(0);

        when(bauer.getPosition()).thenReturn(new Feld(1,3));
        when(bauer.getFarbe()).thenReturn(1);
        when(laeufer.getPosition()).thenReturn(new Feld(2,1));
        when(laeufer.getFarbe()).thenReturn(0);

        ArrayList<Feld> ergebnis = dameDienst.getMoeglicheZuege(figuren, schachbrett, dame);

        assertEquals(moeglicheZuege, ergebnis);
    }
}