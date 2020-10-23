package com.varausjarjestelma.nakyma;

import com.varausjarjestelma.kontrolleri.Kontrolleri;
import com.varausjarjestelma.malli.Ominaisuus;
import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.malli.TilanOminaisuus;

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
		TilanOminaisuus[] tilanOminaisuudet = null;
		
		for (Tila tila : tilat) {
			System.out.println(tila.getNimi() + " ominaisuudet:");
			
			tilanOminaisuudet = kontrolleri.etsiTilanOminaisuudet(tila);
			
			for (TilanOminaisuus to:tilanOminaisuudet) {
				Ominaisuus ominaisuus = to.getOminaisuus();
				
				System.out.println("\t" + ominaisuus.getNimi() + "\n\t\t" + ominaisuus.getKuvaus() + "\n\t\t" + to.getLisatiedot());
			}
		}
		
		Ominaisuus[] ominaisuudet = kontrolleri.haeOminaisuudet();
		
		for (Ominaisuus ominaisuus:ominaisuudet) {
			System.out.println(ominaisuus.getNimi() + " löytyy tiloista:");
			
			tilanOminaisuudet = kontrolleri.etsiTilanOminaisuudet(ominaisuus);
			
			for (TilanOminaisuus to:tilanOminaisuudet) {
				Tila tila = to.getTila();
				
				System.out.println("\t" + tila.getNimi() + "\n\t\t" + tila.getOsoite() + "\n\t\t" + tila.getKaupunki());
			}
		}
	}
	
	public void tilaEsiin() {
		Tila tila = kontrolleri.tuoTila(2);
		System.out.println("Haetun tilan tiedot: ");
		System.out.println( tila.getID()+ ", " + tila.getNimi() + ", " + tila.getKuvaus() + ", " + tila.getKaupunki() );
	}

}
