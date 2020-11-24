package com.varausjarjestelma.kontrolleri;

import java.util.ArrayList;

import com.varausjarjestelma.käyttöliittymä.tilojenselaus.TilojenSelaus;
import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.malli.TilaDAO;
import com.varausjarjestelma.malli.TilanOminaisuus;

public class TilojenSelausKontrolleri {
	
	private final TilojenSelaus näkymä;
	private final TilaDAO tilaDAO;
	
	public TilojenSelausKontrolleri(TilojenSelaus ts) {
		näkymä = ts;
		tilaDAO = new TilaDAO();
	}
	
	public Tila[] haeTilat() {
		return tilaDAO.haeKaikkiTilat();
	}
	
	 public String näytäTilanOminaisuudetStringinä(int tilaID) {
			
			Kontrolleri kontrolleri = Kontrolleri.haeInstanssi();
			Tila kyseinenTila = kontrolleri.etsiTila(tilaID);
			TilanOminaisuus[] ominaisuusArray = kontrolleri.etsiTilanOminaisuudet(kyseinenTila);
	       ArrayList<String> ominaisuudetListattuna = new ArrayList <String>();
	       
			for (int i = 0; i <= ominaisuusArray.length - 1; i++) {
				TilanOminaisuus o = ominaisuusArray[i];
				String nimi = o.getOminaisuus().getNimi();
				String kuvaus = o.getOminaisuus().getKuvaus();
				ominaisuudetListattuna.add(nimi + " (" + kuvaus + ")");
			}
			
			StringBuilder sb = new StringBuilder(); 
			for (int i = 0; i < ominaisuudetListattuna.size(); i++) {
				sb.append("\n \t" + ominaisuudetListattuna.get(i));
			}
			return sb.toString();
		}
	
}