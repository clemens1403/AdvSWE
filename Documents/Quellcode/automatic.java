@Before
public void setup() {
    MockitoAnnotations.initMocks(this);
    bauerDienst = new BauerDienst();
    schachbrett = new Schachbrett();
}

@Test
public void testErzeugeBauer() {
    int farbe = 1;
    Feld startPosition = new Feld(4,1);
    Bauer ergebnis = bauerDienst.erzeugeBauer(farbe, startPosition);

    Assertions.assertNotNull(ergebnis);
    Assertions.assertEquals(ergebnis.getFarbe(), farbe);
    Assertions.assertEquals(ergebnis.getPosition(), startPosition);
}
