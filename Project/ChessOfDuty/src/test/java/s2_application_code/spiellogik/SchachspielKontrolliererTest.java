package s2_application_code.spiellogik;

import de.dhbw.chessofduty.s2_application_code.figuren.*;
import de.dhbw.chessofduty.s2_application_code.schachbrett.FeldDienst;
import de.dhbw.chessofduty.s2_application_code.schachbrett.SchachbrettDienst;
import de.dhbw.chessofduty.s2_application_code.spiellogik.SchachspielDienst;
import de.dhbw.chessofduty.s2_application_code.spiellogik.SchachspielKontrollierer;
import de.dhbw.chessofduty.s3_domain_code.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SchachspielKontrolliererTest {

    @Test
    public void testInitialisiereFiguren() {

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


        // Create test object and call method
        Schachbrett schachbrett = new Schachbrett();

        SchachspielKontrollierer schachspielKontrollierer = new SchachspielKontrollierer(schachspielDienst, figurDienst, bauerDienst, dameDienst, koenigDienst, laeuferDienst, springerDienst, turmDienst, feldDienst, schachbrettDienst);
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
}
