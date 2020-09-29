package varausjarjestelma.varausjarjestelma;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.malli.TilaDAO;

public class TietokantaTest {

	private static TilaDAO tilaDAO;
	private static Tila testiTila;

	@BeforeAll
	static void setup() {
		tilaDAO = new TilaDAO();
		testiTila = new Tila();

		testiTila.setNimi("Aurora");
		testiTila.setKuvaus("Helsingin yliopiston aula");
		testiTila.setKaupunki("Helsinki");
		testiTila.setOsoite("Siltavuorenpenger 10");
		testiTila.setHlomaara(15);
		testiTila.setNakyvyys(true);
	}

	@DisplayName("Test lisaaTila()")
	@Test
	void testLisaa() {
		boolean result = tilaDAO.lisaaTila(testiTila);
		assertTrue(result, "Lisäys ei onnistunut.");
	}

	@DisplayName("Test etsiTila()")
	@Test
	void testEtsi() {
		Tila tila = tilaDAO.etsiTila(testiTila.getID());
		assertEquals(testiTila.getID(), tila.getID(), "ID ei täsmännyt.");
	}

	@DisplayName("Test muokkaaTila()")
	@Test
	void testMuokkaa() {
		boolean result = tilaDAO.muokkaaTilaa(1, testiTila);
		assertTrue(result, "Muokkaus ei onnistunut.");
	}

	@DisplayName("Test poistaTila()")
	@Test
	void testPoista() {
		boolean result = tilaDAO.poistaTila(testiTila);
		assertTrue(result, "Poistaminen ei onnistunut.");
	}

}
