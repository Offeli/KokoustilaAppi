package com.test.tietokanta;

import java.util.List;
import org.hibernate.*;
import org.hibernate.query.*;
import org.junit.jupiter.api.*;

import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.malli.TilaDAO;

public class TietokantaTest {
	
	private static SessionFactory sessionFactory;
	private Session session;
	private static TilaDAO tilaDAO;
	
	@BeforeAll
	  static void setup() {
	    sessionFactory = TilaDAO.getIstuntotehdas();
	    System.out.println("SessionFactory created.");
	  }

	  @BeforeEach
	  void openSession() {
	    session = sessionFactory.openSession();
	    System.out.println("Session created.");
	  }

	  @DisplayName("Test lisaaTila()")
	  @Test
	  void testLisaa() throws Exception {
	    System.out.println("Test lisaaTila(nimi, kuvaus, kaupunki, osoite, hlomäärä, näkyvyys)");
	    
	    session.beginTransaction();
	    TilaDAO.lisaaTila("Aurora", "Helsingin yliopiston aula", "Helsinki", "Siltavuorenpenger 10", 15, true);
	    session.getTransaction().commit();
	    
	    System.out.println("Tieto lisätty");
	  }

	  @DisplayName("Test haeTila()")
	  @Test
	  void testHae() {
	    System.out.println("Testaa haeTila(nimi)");
	    
	    session.beginTransaction();
	    Tila result = TilaDAO.haeTila("Aurora");
	    session.getTransaction().commit();
	    
	    System.out.println("Tieto haettu:\n" + result.getID() + " " + result.getNimi());
	  }

	  @DisplayName("Test muokkaaTila()")
	  @Test
	  void testMuokkaa() {
		  System.out.println("Testaa muokkaaTila(id, uusiNimi)");
		  
		  session.beginTransaction();
		  TilaDAO.muokkaaTila(1, "Auroora");
		  session.getTransaction().commit();
	  }
	  
	  @DisplayName("Test poistaTila()")
	  @Test
	  void testPoista() {
		  System.out.println("Testaa poistaTila(nimi, osoite)");
		  
		  session.beginTransaction();
		  TilaDAO.poistaTila("Aurora", "Siltavuorenpenger 10");
		  session.getTransaction().commit();
		  
		  System.out.println("Poistettu onnistuneesti.");
	  }

	  @AfterEach
	  void closeSession() {
	    if(session != null) session.close();
	    System.out.println("Session closed.");
	  }

	  @AfterAll
	  static void tearDown() {
	    if(sessionFactory != null) sessionFactory.close();
	    System.out.println("SessionFactory destroyed.");
	  }


}
