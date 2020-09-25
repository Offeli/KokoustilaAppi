package com.varausjarjestelma.malli;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class TilaDAO {

	private SessionFactory istuntotehdas;
	private StandardServiceRegistry rekisteri;

	public TilaDAO() {
		try {
			istuntotehdas = new Configuration().configure().buildSessionFactory();
			rekisteri = new StandardServiceRegistryBuilder().configure().build();
		} catch (Exception e) {
			System.out.println("TilaDAO - istuntotehtaan luonti epäonnistui");
			StandardServiceRegistryBuilder.destroy(rekisteri);
			e.printStackTrace();
			System.exit(0);
		}
	}

	public Tila[] haeKaikkiTilat() {
		Session istunto = null;
		Transaction transaktio = null;

		// try-with-resources ei ole tarjolla. JRE-versio-ongelma.
		try {
			istunto = istuntotehdas.openSession();
			transaktio = istunto.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Tila> tilat = istunto.createQuery("from Tila").getResultList();

			istunto.getTransaction().commit();

			return tilat.toArray(new Tila[tilat.size()]);

		} catch (Exception e) {
			if (transaktio != null)
				transaktio.rollback();
			System.err.println(e.getMessage());

		} finally {
			if (istunto != null)
				istunto.close();
		}

		return null;
	}

	public void lisaaTila(String nimi, String kuvaus, String kaupunki, String osoite, int hlomaara, boolean nakyvyys) {
		Transaction transaktio = null;
		Session istunto = null;

		try {
			istunto = istuntotehdas.openSession();
			transaktio = istunto.beginTransaction();

			Tila tila = new Tila();
			tila.setNimi(nimi);
			tila.setKuvaus(kuvaus);
			tila.setKaupunki(kaupunki);
			tila.setOsoite(osoite);
			tila.setHlomaara(hlomaara);
			tila.setNakyvyys(nakyvyys);

			istunto.save(tila);
			istunto.getTransaction().commit();

		} catch (Exception e) {
			if (transaktio != null)
				transaktio.rollback();
			e.printStackTrace();
		} finally {
			if (istunto != null)
				istunto.close();
		}
	}

	public Tila haeTila(String nimi) {
		Tila result = null;
		Session istunto = null;
		Transaction transaktio = null;

		// try-with-resources ei ole tarjolla. JRE-versio-ongelma.
		try {
			istunto = istuntotehdas.openSession();
			transaktio = istunto.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Tila> tilat = istunto.createQuery("from Tila").getResultList();

			istunto.getTransaction().commit();

			for (Tila t : tilat) {
				if (t.getNimi().equals(nimi))
					result = t;
			}

		} catch (Exception e) {
			if (transaktio != null)
				transaktio.rollback();
			System.err.println(e.getMessage());
			System.exit(-1);

		} finally {
			if (istunto != null)
				istunto.close();
		}

		return result;
	}

	public void muokkaaTila(int id, String uusiNimi) {
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();

		Tila t = istunto.get(Tila.class, id);
		if (t != null) {
			System.out.println(t.getNimi() + " päivitetään " + uusiNimi);
			t.setNimi(uusiNimi);
			System.out.println("Nimi päivitetty.");
		} else {
			System.out.println("Ei löytynyt päivitettävää.");
		}
		istunto.getTransaction().commit();
		istunto.close();
	}

	public void poistaTila(String nimi, String osoite) {
		int result = 0;
		Session istunto = null;
		Transaction transaktio = null;

		// try-with-resources ei ole tarjolla. JRE-versio-ongelma.
		try {
			istunto = istuntotehdas.openSession();
			transaktio = istunto.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Tila> tilat = istunto.createQuery("from Tila").getResultList();

			istunto.getTransaction().commit();

			for (Tila t : tilat) {
				if (t.getNimi().equals(nimi) && t.getOsoite().equals(osoite))
					result = t.getID();
			}

		} catch (Exception e) {
			if (transaktio != null)
				transaktio.rollback();
			System.err.println(e.getMessage());
			System.exit(-1);

		} finally {
			if (istunto != null)
				istunto.close();
		}

		Session istuntoTwo = istuntotehdas.openSession();
		istuntoTwo.beginTransaction();

		Tila tila = istuntoTwo.get(Tila.class, result);
		istuntoTwo.delete(tila);

		istuntoTwo.getTransaction().commit();
		istuntoTwo.close();
	}

}
