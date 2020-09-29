package com.varausjarjestelma.nakyma;

import com.varausjarjestelma.kontrolleri.Kontrolleri;
import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.malli.TilaDAO;

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
	
	public void tilaEsiin() {
		Tila tila = kontrolleri.tuoTila(2);
		System.out.println("Haetun tilan tiedot: ");
		System.out.println( tila.getID()+ ", " + tila.getNimi() + ", " + tila.getKuvaus() + ", " + tila.getKaupunki() );
	}

}
