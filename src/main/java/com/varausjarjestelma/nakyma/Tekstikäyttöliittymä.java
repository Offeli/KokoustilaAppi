package com.varausjarjestelma.nakyma;

import com.varausjarjestelma.kontrolleri.Kontrolleri;
import com.varausjarjestelma.malli.Tila;

public class Tekstikäyttöliittymä implements Käyttöliittymä {

	private Kontrolleri kontrolleri;

	public Tekstikäyttöliittymä(Kontrolleri kontrolleri) {
		this.kontrolleri = kontrolleri;
	}

	public void tulosta(String merkkijono) {
		System.out.println(merkkijono);
	}
	

	public void käynnistä() {
		Tila[] tilat = kontrolleri.haeEsimerkki();
		for (Tila tila : tilat) {
			System.out.println(tila.getNimi());
		}
	}

}
