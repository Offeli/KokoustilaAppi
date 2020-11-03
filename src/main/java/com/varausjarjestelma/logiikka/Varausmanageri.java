package com.varausjarjestelma.logiikka;

import java.sql.Timestamp;

import com.varausjarjestelma.kontrolleri.Kontrolleri;
import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.malli.Varaukset;

public class Varausmanageri {

	private final Kontrolleri kontrolleri;

	public Varausmanageri() {
		kontrolleri = Kontrolleri.haeInstanssi();
	}

	public boolean onkoTilaVapaa(Tila tila, Timestamp alku, Timestamp loppu) {
		Varaukset[] varaukset = kontrolleri.haeVarauksetTila(tila);

		for (Varaukset v : varaukset) {
			Timestamp vAlku = v.getAlkuAika();
			Timestamp vLoppu = v.getLoppuAika();

			if ((vAlku.compareTo(alku) >= 0 && vAlku.compareTo(loppu) < 0)
					|| (vLoppu.compareTo(alku) > 0 && vLoppu.compareTo(loppu) <= 0)) {
				return false;
			}
		}

		return true;
	}

}
