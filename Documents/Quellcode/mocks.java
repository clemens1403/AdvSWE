@BeforeEach
public void setUp() {
    koenigDienst = new KoenigDienst();
    schachbrett = new Schachbrett();
    koenigMock = mock(Koenig.class);
    when(koenigMock.getPosition()).thenReturn(new Feld(1, 1)); // Beispielposition
    when(koenigMock.getFarbe()).thenReturn(1);
}

@Test
public void getMoeglicheZuegeTest() {
    Dame figur1 = mock(Dame.class);
    Dame figur2 = mock(Dame.class);
    when(figur1.getPosition()).thenReturn(new Feld(2, 2)); // Beispielposition
    when(figur1.getFarbe()).thenReturn(1);
    when(figur2.getPosition()).thenReturn(new Feld(1, 2)); // Beispielposition
    when(figur2.getFarbe()).thenReturn(0);
    ArrayList<Figur> figuren = new ArrayList<>(Arrays.asList(figur1, figur2));

    ArrayList<Feld> moeglicheZuege = new ArrayList<>(Arrays.asList(new Feld(1, 2), new Feld(2, 1)));

    assertEquals(moeglicheZuege, koenigDienst.getMoeglicheZuege(figuren, schachbrett, koenigMock));

    verify(koenigMock, atLeast(1)).getPosition();
    verify(koenigMock, atLeast(1)).getFarbe();

    verify(figur1, atLeast(1)).getPosition();
    verify(figur1, atLeast(1)).getFarbe();

    verify(figur2, atLeast(1)).getPosition();
    verify(figur2, atLeast(1)).getFarbe();
}

