package com.test.tietokanta;

import org.junit.jupiter.api.*;

import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.malli.TilaDAO;

public class TietokantaTest {

	private static TilaDAO tilaDAO;

	@BeforeAll
	static void setup() {
		tilaDAO = new TilaDAO();
	}

	@DisplayName("Test lisaaTila()")
	@Test
	void testLisaa() throws Exception {
		tilaDAO.lisaaTila("Aurora", "Helsingin yliopiston aula", "Helsinki", "Siltavuorenpenger 10", 15, true);
	}

	@DisplayName("Test haeTila()")
	@Test
	void testHae() {
		Tila result = tilaDAO.haeTila("Aurora");
	}

	@DisplayName("Test muokkaaTila()")
	@Test
	void testMuokkaa() {
		tilaDAO.muokkaaTila(1, "Auroora");
	}

	@DisplayName("Test poistaTila()")
	@Test
	void testPoista() {
		tilaDAO.poistaTila("Aurora", "Siltavuorenpenger 10");
	}

}
