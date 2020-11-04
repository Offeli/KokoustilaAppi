package com.varausjarjestelma.kontrolleri;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import com.varausjarjestelma.malli.*;

public class Kontrolleri {

	private static Kontrolleri instanssi = null;
	
	private TestConnection tc;
	private TilaDAO tilaDAO;
	private TilanOminaisuusDAO toDAO;
	private OminaisuusDAO ominaisuusDAO;
	private KäyttäjäDAO käyttäjäDAO;
	private VarauksetDAO varauksetDAO;

	private Kontrolleri() {
		tc = new TestConnection();
		tilaDAO = new TilaDAO();
		käyttäjäDAO = new KäyttäjäDAO();
		varauksetDAO = new VarauksetDAO();
		toDAO = new TilanOminaisuusDAO();
		ominaisuusDAO = new OminaisuusDAO();
	}

	public static Kontrolleri haeInstanssi() {
		if (instanssi == null)
			instanssi = new Kontrolleri();

		return instanssi;
	}

	public Tila[] haeTilat() {
		return tilaDAO.haeKaikkiTilat();
	}

	public Ominaisuus[] haeOminaisuudet() {
		return ominaisuusDAO.haeKaikkiOminaisuudet();
	}
	
	public Käyttäjä[] haeKäyttäjät() {
		return käyttäjäDAO.haeKaikkiKayttajat();
	}

	public Tila etsiTila(int id) {
		return tilaDAO.etsiTila(id);
	}

	public int haeTilatMaara() {
		Tila[] tilat = tilaDAO.haeKaikkiTilat();

		return tilat.length;
	}

	public Object[] tilojenTiedotTaulukkona() throws SQLException {

		ResultSet rs = tc.haeEsimerkkiData();
		ArrayList<String> rivitListana = new ArrayList<String>();
		int riveja = 0;
		while (rs.next()) {
			riveja++;
			int id = rs.getInt("id");
			String nimi = rs.getString("nimi");
			String kuvaus = rs.getString("kuvaus");
			rivitListana.add(id + " " + nimi + " " + kuvaus);
		}
		Object[] rivitTaulukkona = rivitListana.toArray();
		for (int i = 0; i < rivitTaulukkona.length; i++) {
			System.out.println(rivitTaulukkona[i]);
		}
		System.out.println("(rivejä yhteensä: " + riveja + ")");
		return rivitTaulukkona;
	}

	// Varaamiseen tarvittavat metodit

	public Käyttäjä etsiKäyttäjä(int id) {
		return käyttäjäDAO.etsiKayttaja(id);
	}

	public boolean asetaVaraus(int käyttäjänID, int tilaID, Timestamp alku, Timestamp loppu) {
		Varaukset varaus = new Varaukset();
		Käyttäjä kayttaja = etsiKäyttäjä(käyttäjänID);
		Tila tila = etsiTila(tilaID);

		varaus.setKayttaja(kayttaja);
		varaus.setTila(tila);
		varaus.setAlkuAika(alku);
		varaus.setLoppuAika(loppu);

		return varauksetDAO.lisaaVaraus(varaus);
	}

	// Varauksen muokkaus

	public Varaukset etsiVaraus(int id) {
		return varauksetDAO.etsiVaraus(id);
	}

	public boolean muokkaaVarausta(int varausID, Timestamp alku, Timestamp loppu) {
		Varaukset varaus = etsiVaraus(varausID);

		varaus.setAlkuAika(alku);
		varaus.setLoppuAika(loppu);

		return varauksetDAO.muokkaaVarausta(varausID, varaus);
	}

	// Varauksen poistaminen

	public boolean poistaVaraus(int varausID) {
		Varaukset varaus = etsiVaraus(varausID);

		return varauksetDAO.poistaVaraus(varaus);
	}

	public TilanOminaisuus[] etsiTilanOminaisuudet(Tila tila) {
		return toDAO.etsiTilaanLiittyvätTilanOminaisuudet(tila);
	}

	public TilanOminaisuus[] etsiTilanOminaisuudet(Ominaisuus ominaisuus) {
		return toDAO.etsiOminaisuuteenLiittyvätTilanOminaisuudet(ominaisuus);
	}
	
	public Varaukset[] haeVarauksetTila(Tila tila) {
		return varauksetDAO.haeVarauksetTila(tila);
	}
	
	public Varaukset[] haeVaraukset(Käyttäjä käyttäjä) {
		return varauksetDAO.haeVaraukset(käyttäjä);
	}

}
