package com.varausjarjestelma.kontrolleri;

import com.varausjarjestelma.malli.TestConnection;
import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.nakyma.Käyttöliittymä;
import com.varausjarjestelma.malli.TilaDAO;

public class Kontrolleri {
	
	private Käyttöliittymä käyttöliittymä;
	private TestConnection tc;
	private TilaDAO tilaDAO;
	
	public Kontrolleri() {
		tc = new TestConnection();
		tilaDAO = new TilaDAO();
		käyttöliittymä = null;
	}
	
	public void setKäyttöliittymä(Käyttöliittymä käyttöliittymä) {
		this.käyttöliittymä = käyttöliittymä;
	}
	
//	public Kontrolleri(Käyttöliittymä käyttöliittymä) {
//		this.käyttöliittymä = käyttöliittymä;
//		tc = new TestConnection();
//	}

	public Tila[] haeEsimerkki() {
		 return tilaDAO.haeKaikkiTilat();
	}
	
}
