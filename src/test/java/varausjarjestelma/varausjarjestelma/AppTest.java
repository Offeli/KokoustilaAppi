package varausjarjestelma.varausjarjestelma;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;

import com.varausjarjestelma.malli.Ominaisuus;
import com.varausjarjestelma.malli.OminaisuusDAO;
import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.malli.TilaDAO;
import com.varausjarjestelma.malli.TilanOminaisuus;
import com.varausjarjestelma.malli.TilanOminaisuusDAO;

/**
 * Unit test for simple App.
 * Päivitetty testiluokka
 */
@TestMethodOrder(Alphanumeric.class)
public class AppTest 
{
	private static TilaDAO tilaDAO;
	private static Tila testiTila;
	private static OminaisuusDAO ominaisuusDAO;
	private static Ominaisuus testiOminaisuus;
	private static TilanOminaisuusDAO tilanOminaisuusDAO;
	private static TilanOminaisuus testiTilanOminaisuus;

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
		
		ominaisuusDAO = new OminaisuusDAO();
		testiOminaisuus = new Ominaisuus();
		
		testiOminaisuus.setNimi("Videotykki");
		testiOminaisuus.setKuvaus("Voit heijastaa tietokoneella olevaa tietoa valkotaululle.");
		
		tilanOminaisuusDAO = new TilanOminaisuusDAO();
		testiTilanOminaisuus = new TilanOminaisuus();
		
		testiTilanOminaisuus.setOminaisuus(testiOminaisuus);
		testiTilanOminaisuus.setTila(testiTila);
		testiTilanOminaisuus.setLisatiedot("Löytyy kaapistosta työpöydän oikealta puolelta.");
	}

	@Test
	void testALisaaTila() {
		boolean result = tilaDAO.lisaaTila(testiTila);
		assertTrue(result, "Lisäys ei onnistunut.");
	}
	
	@Test
	void testALisaaOminaisuus() {
		boolean result = ominaisuusDAO.lisaaOminaisuus(testiOminaisuus);
		assertTrue(result, "Lisäys ei onnistunut.");
	}
	
	@Test
	void testALisaaTilanOminaisuus() {
		boolean result = tilanOminaisuusDAO.lisaaTilanOminaisuus(testiTilanOminaisuus);
		assertTrue(result, "Lisäys ei onnistunut.");
	}

	@Test
	void testBEtsiTila() {
		Tila tila = tilaDAO.etsiTila(testiTila.getID());
		assertEquals(testiTila.getID(), tila.getID(), "ID ei täsmännyt.");
	}
	
	@Test
	void testBEtsiOminaisuus() {
		Ominaisuus ominaisuus = ominaisuusDAO.etsiOminaisuus(testiOminaisuus.getID());
		assertEquals(testiOminaisuus.getID(), ominaisuus.getID(), "ID ei täsmännyt.");
	}
	
	@Test
	void testBEtsiTilanOminaisuus() {
		TilanOminaisuus tilanOminaisuus = tilanOminaisuusDAO.etsiTilanOminaisuus(testiTilanOminaisuus.getID());
		assertEquals(testiTilanOminaisuus.getID(), tilanOminaisuus.getID(), "ID ei täsmännyt.");
	}

	@Test
	void testCMuokkaaTila() {
		testiTila.setHlomaara(10);
		boolean result = tilaDAO.muokkaaTilaa(testiTila.getID(), testiTila);
		assertTrue(result, "Muokkaus ei onnistunut.");
	}
	
	@Test
	void testCMuokkaaOminaisuus() {
		testiOminaisuus.setKuvaus("Videotykillä voit heijastaa tietoa valkokankaalle.");
		boolean result = ominaisuusDAO.muokkaaOminaisuutta(testiOminaisuus.getID(), testiOminaisuus);
		assertTrue(result, "Muokkaus ei onnistunut.");
	}
	
	@Test
	void testCMuokkaaTilanOminaisuus() {
		testiTilanOminaisuus.setLisatiedot("Videotykki on näkyvillä työpöydällä, mutta mikäli se ei ole siinä, on se mitä luultavimmin kaapistossa vasemmalla puolella.");
		boolean result = tilanOminaisuusDAO.muokkaaTilanOminaisuutta(testiTilanOminaisuus.getID(), testiTilanOminaisuus);
		assertTrue(result, "Muokkaus ei onnistunut.");
	}

	@Test
	void testDPoistaTila() {
		boolean result = tilaDAO.poistaTila(testiTila);
		assertTrue(result, "Poistaminen ei onnistunut.");
	}
	
	@Test
	void testDPoistaOminaisuus() {
		boolean result = ominaisuusDAO.poistaOminaisuus(testiOminaisuus);
		assertTrue(result, "Poistaminen ei onnistunut.");
	}
	
	@Test
	void testDPoistaTilanOminaisuus() {
		boolean result = tilanOminaisuusDAO.poistaTilanOminaisuus(testiTilanOminaisuus);
		assertTrue(result, "Poistaminen ei onnistunut.");
	}
}