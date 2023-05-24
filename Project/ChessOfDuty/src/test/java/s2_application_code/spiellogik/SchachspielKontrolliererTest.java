package s2_application_code.spiellogik;

import de.dhbw.chessofduty.s2_application_code.figuren.*;
import de.dhbw.chessofduty.s2_application_code.schachbrett.FeldDienst;
import de.dhbw.chessofduty.s2_application_code.schachbrett.SchachbrettDienst;
import de.dhbw.chessofduty.s2_application_code.spiellogik.SchachspielDienst;
import de.dhbw.chessofduty.s2_application_code.spiellogik.SchachspielKontrollierer;
import de.dhbw.chessofduty.s2_application_code.spielzug.SchachzugDienst;
import de.dhbw.chessofduty.s2_application_code.spielzug.SpielzugDienst;
import de.dhbw.chessofduty.s3_domain_code.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SchachspielKontrolliererTest {

    @Test
    public void initialisiereFigurenTest() {

        FigurDienst figurDienst = new FigurDienst();
        BauerDienst bauerDienst = new BauerDienst();
        DameDienst dameDienst = new DameDienst();
        KoenigDienst koenigDienst = new KoenigDienst();
        LaeuferDienst laeuferDienst = new LaeuferDienst();
        SpringerDienst springerDienst = new SpringerDienst();
        TurmDienst turmDienst = new TurmDienst();
        Schachspiel schachspiel = new Schachspiel(new Schachbrett());
        FeldDienst feldDienst = new FeldDienst();
        SchachbrettDienst schachbrettDienst = new SchachbrettDienst();
        SchachspielDienst schachspielDienst = new SchachspielDienst(schachbrettDienst);
        SchachzugDienst schachzugDienst = new SchachzugDienst();
        SpielzugDienst spielzugDienst = new SpielzugDienst();

        // Create test object and call method
        Schachbrett schachbrett = new Schachbrett();

        SchachspielKontrollierer schachspielKontrollierer = new SchachspielKontrollierer(schachspiel, figurDienst, bauerDienst, dameDienst, koenigDienst, laeuferDienst, springerDienst, turmDienst, feldDienst, schachbrettDienst, schachzugDienst, spielzugDienst);
        schachspielKontrollierer.initialisiereFiguren(bauerDienst, dameDienst, koenigDienst, laeuferDienst, springerDienst, turmDienst);

        // Verify that the correct number of pieces was created
        List<Figur> figuren = schachspielKontrollierer.getSchachspiel().getFiguren();
        assertEquals(32, figuren.size());

        // Verify that the correct types of pieces were created
        assertTrue(figuren.stream().anyMatch(figur -> figur instanceof Bauer));
        assertTrue(figuren.stream().anyMatch(figur -> figur instanceof Dame));
        assertTrue(figuren.stream().anyMatch(figur -> figur instanceof Koenig));
        assertTrue(figuren.stream().anyMatch(figur -> figur instanceof Laeufer));
        assertTrue(figuren.stream().anyMatch(figur -> figur instanceof Springer));
        assertTrue(figuren.stream().anyMatch(figur -> figur instanceof Turm));
    }

    //TODO: klickauswerten Test => figur auswählen und zug ausführen
    /*@Test
    public void testKlickAuswerten(){

        FigurDienst figurDienst = new FigurDienst();
        BauerDienst bauerDienst = new BauerDienst();
        DameDienst dameDienst = new DameDienst();
        KoenigDienst koenigDienst = new KoenigDienst();
        LaeuferDienst laeuferDienst = new LaeuferDienst();
        SpringerDienst springerDienst = new SpringerDienst();
        TurmDienst turmDienst = new TurmDienst();
        FeldDienst feldDienst = new FeldDienst();
        SchachbrettDienst schachbrettDienst = new SchachbrettDienst();
        SchachspielDienst schachspielDienst = new SchachspielDienst(schachbrettDienst);
        SchachzugDienst schachzugDienst = new SchachzugDienst();
        SpielzugDienst spielzugDienst = new SpielzugDienst();

        // Create test object and call method
        Schachbrett schachbrett = new Schachbrett();



        //Test Koenig auswählen
        Laeufer laeufer = mock(Laeufer.class);
        Dame dame = mock(Dame.class);
        Koenig koenig = mock(Koenig.class);
        Schachspiel schachspiel = mock(Schachspiel.class);

        when(laeufer.getPosition()).thenReturn(new Feld(1, 1)); // Beispielposition
        when(laeufer.getFarbe()).thenReturn(1);
        when(dame.getPosition()).thenReturn(new Feld(1, 2)); // Beispielposition
        when(dame.getFarbe()).thenReturn(0);
        when(koenig.getPosition()).thenReturn(new Feld(2, 1)); // Beispielposition
        when(koenig.getFarbe()).thenReturn(0);

        ArrayList<Figur> figuren = new ArrayList<>(Arrays.asList(laeufer, dame, koenig));

        when(schachspiel.getFiguren()).thenReturn(figuren);
        when(schachspiel.getSchachbrett()).thenReturn(schachbrett);

        int mausX = 350;
        int mausY = 950;

        SchachspielKontrollierer schachspielKontrollierer = new SchachspielKontrollierer(schachspiel, figurDienst, bauerDienst, dameDienst, koenigDienst, laeuferDienst, springerDienst, turmDienst, feldDienst, schachbrettDienst, schachzugDienst, spielzugDienst);
        schachspielKontrollierer.klickAuswerten(mausX, mausY);

        assertEquals(koenig, schachspielKontrollierer.getAusgewaelteFigur());





    }*/
}
