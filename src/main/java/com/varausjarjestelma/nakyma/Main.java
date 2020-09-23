package com.varausjarjestelma.nakyma;

import com.varausjarjestelma.kontrolleri.Kontrolleri;
import com.varausjarjestelma.malli.TilaDAO;

public class Main {

	public static void main(String[] args) {
		System.out.println("terve");
		Kontrolleri kontrolleri = new Kontrolleri();
		
		Tekstikäyttöliittymä käyttöliittymä = new Tekstikäyttöliittymä(kontrolleri);
		
		kontrolleri.setKäyttöliittymä(käyttöliittymä);
		TilaDAO td = new TilaDAO();
		System.out.println(td.haeKaikkiTilat());
		
		käyttöliittymä.käynnistä();
	}

}
