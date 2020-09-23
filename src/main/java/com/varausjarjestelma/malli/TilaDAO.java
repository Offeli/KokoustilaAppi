package com.varausjarjestelma.malli;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class TilaDAO {

	private static SessionFactory istuntotehdas;
	private static StandardServiceRegistry rekisteri;

	public TilaDAO() {
		istuntotehdas = null;
		rekisteri = new StandardServiceRegistryBuilder().configure().build();
	}
	
	public static SessionFactory getIstuntotehdas() {
		try {
			istuntotehdas = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			System.out.println("TilaDAO - istuntotehtaan luonti epäonnistui");
			StandardServiceRegistryBuilder.destroy(rekisteri);
			e.printStackTrace();
		}
		
		return istuntotehdas;
	}

	public static Tila[] haeKaikkiTilat() {
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
			System.exit(-1);

		} finally {
			if (istunto != null)
				istunto.close();
		}

		return null;
	}
	
	public static void lisaaTila(String nimi, String kuvaus, String kaupunki, String osoite, int hlomaara, boolean nakyvyys) throws Exception {
		Transaction transaktio = null;
		
		try{
			Session istunto = istuntotehdas.openSession();
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
			
		}catch(Exception e) {
			if(transaktio != null) transaktio.rollback();
			throw e;
		}
	}
	
	public static Tila haeTila(String nimi) {
		 Tila[] tilat = haeKaikkiTilat();
		 Tila result = null;
		 
		 for(Tila t : tilat) {
			 if(t.getNimi().equals(nimi)) result = t;
		 }
		 
		 return result;
	}
	
	public static void muokkaaTila(int id, String uusiNimi) {
		 Session istunto = istuntotehdas.openSession();
		 istunto.beginTransaction();
		 
		 Tila t = istunto.get(Tila.class, id);
		 if(t != null) {
			 System.out.println(t.getNimi() + " päivitetään " + uusiNimi);
			 t.setNimi(uusiNimi);
			 System.out.println("Nimi päivitetty.");
		 }else {
			 System.out.println("Ei löytynyt päivitettävää.");
		 }
		 istunto.getTransaction().commit();
		 istunto.close();
	}
	
	public static void poistaTila(String nimi, String osoite) {
		 Tila[] tilat = haeKaikkiTilat();
		 int result = 0;
		 
		 for(Tila t : tilat) {
			 if(t.getNimi().equals(nimi) && t.getOsoite().equals(osoite)) result = t.getID();
		 }
		 
		 Session istunto = istuntotehdas.openSession();
		 istunto.beginTransaction();
		 
		 Tila tila = istunto.get(Tila.class, result);
		 istunto.delete(tila);
		 
		 istunto.getTransaction().commit();
		 istunto.close();
	}

}
