package com.test.tietokanta;

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
		boolean onnistui = tilaDAO.lisaaTila(testiTila);
	}

	@DisplayName("Test haeTila()")
	@Test
	void testHae() {
		Tila tila = tilaDAO.haeTila(testiTila.getID());
	}

	@DisplayName("Test muokkaaTila()")
	@Test
	void testMuokkaa() {
		boolean onnistui = tilaDAO.muokkaaTilaa(1, testiTila);
	}

	@DisplayName("Test poistaTila()")
	@Test
	void testPoista() {
		boolean onnistui = tilaDAO.poistaTila(testiTila);
	}

}
