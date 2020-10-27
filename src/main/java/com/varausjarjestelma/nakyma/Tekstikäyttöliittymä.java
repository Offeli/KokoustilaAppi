package com.varausjarjestelma.nakyma;

import com.varausjarjestelma.kontrolleri.Kontrolleri;
import com.varausjarjestelma.malli.Käyttäjä;
import com.varausjarjestelma.malli.Ominaisuus;
import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.malli.TilanOminaisuus;
import com.varausjarjestelma.malli.Varaukset;

public class Tekstikäyttöliittymä implements Käyttöliittymä {

	private Kontrolleri kontrolleri;

	public Tekstikäyttöliittymä() {
		kontrolleri = Kontrolleri.haeInstanssi();
	}

	public void tulosta(String merkkijono) {
		System.out.println(merkkijono);
	}

	public void käynnistä() {
//		testi1haeTilanOminaisuudet();
		testi2haeTilaanLiittyvätVaraukset();

	}

	public void tilaEsiin() {
		Tila tila = kontrolleri.etsiTila(2);
		System.out.println("Haetun tilan tiedot: ");
		System.out.println(tila.getID() + ", " + tila.getNimi() + ", " + tila.getKuvaus() + ", " + tila.getKaupunki());
	}

	@SuppressWarnings("unused")
	private void testi1haeTilanOminaisuudet() {
		Tila[] tilat = kontrolleri.haeEsimerkki();
		TilanOminaisuus[] tilanOminaisuudet = null;

		for (Tila tila : tilat) {
			System.out.println(tila.getNimi() + " ominaisuudet:");

			tilanOminaisuudet = kontrolleri.etsiTilanOminaisuudet(tila);

			for (TilanOminaisuus to : tilanOminaisuudet) {
				Ominaisuus ominaisuus = to.getOminaisuus();

				System.out.println("\t" + ominaisuus.getNimi() + "\n\t\t" + ominaisuus.getKuvaus() + "\n\t\t"
						+ to.getLisatiedot());
			}
		}

		Ominaisuus[] ominaisuudet = kontrolleri.haeOminaisuudet();

		for (Ominaisuus ominaisuus : ominaisuudet) {
			System.out.println(ominaisuus.getNimi() + " löytyy tiloista:");

			tilanOminaisuudet = kontrolleri.etsiTilanOminaisuudet(ominaisuus);

			for (TilanOminaisuus to : tilanOminaisuudet) {
				Tila tila = to.getTila();

				System.out.println("\t" + tila.getNimi() + "\n\t\t" + tila.getOsoite() + "\n\t\t" + tila.getKaupunki());
			}
		}
	}

	private void testi2haeTilaanLiittyvätVaraukset() {
		Tila tila = kontrolleri.etsiTila(1);
		Varaukset[] varaukset = kontrolleri.haeVaraukset(tila);

		for (Varaukset v : varaukset) {
			Käyttäjä käyttäjä = v.getKayttaja();

			System.out.println(String.format("%s %s on varannut tilan %s sijainnissa %s, %s klo %s-%s.",
					käyttäjä.getEtunimi(), käyttäjä.getSukunimi(), tila.getNimi(), tila.getOsoite(), tila.getKaupunki(),
					v.getAlkuAika(), v.getLoppuAika()));
		}
	}

}
