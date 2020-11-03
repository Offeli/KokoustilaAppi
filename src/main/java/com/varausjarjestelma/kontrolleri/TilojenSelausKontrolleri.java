package com.varausjarjestelma.kontrolleri;

import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.malli.TilaDAO;
import com.varausjarjestelma.nakyma.nakyma.TilojenSelausNäkymä;

public class TilojenSelausKontrolleri {
	
	private final TilojenSelausNäkymä näkymä;
	private final TilaDAO tilaDAO;
	
	public TilojenSelausKontrolleri(TilojenSelausNäkymä ts) {
		näkymä = ts;
		tilaDAO = new TilaDAO();
	}
	
	public Tila[] haeTilat() {
		return tilaDAO.haeKaikkiTilat();
	}
	
}