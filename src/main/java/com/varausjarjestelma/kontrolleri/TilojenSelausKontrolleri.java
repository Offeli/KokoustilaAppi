package com.varausjarjestelma.kontrolleri;

import com.varausjarjestelma.käyttöliittymä.tilojenselaus.TilojenSelausKehys;
import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.malli.TilaDAO;

public class TilojenSelausKontrolleri {
	
	private final TilojenSelausKehys näkymä;
	private final TilaDAO tilaDAO;
	
	public TilojenSelausKontrolleri(TilojenSelausKehys ts) {
		näkymä = ts;
		tilaDAO = new TilaDAO();
	}
	
	public Tila[] haeTilat() {
		return tilaDAO.haeKaikkiTilat();
	}
	
}