package com.varausjarjestelma.logiikka;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.varausjarjestelma.kontrolleri.Kontrolleri;
import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.malli.Varaukset;

import javafx.scene.control.ChoiceBox;

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
	
	public void varaaTila(ChoiceBox alkuTuntiPicker, ChoiceBox loppuTuntiPicker, LocalDate varauksenAloitusPäivä, 
   		 LocalDate varauksenLopetusPäivä, int tilanId) {
	
		kontrolleri.haeInstanssi();
		
		Object alkuTunti = alkuTuntiPicker.getValue();
		Object loppuTunti = loppuTuntiPicker.getValue();

		LocalDate alkuInit = varauksenAloitusPäivä;
		LocalDate loppuInit = varauksenLopetusPäivä;
		

		Timestamp alkuTs = Timestamp.valueOf(alkuInit.atTime((int) alkuTunti, 0));
		Timestamp loppuTs = Timestamp.valueOf(loppuInit.atTime((int) loppuTunti, 0));
		System.out.println(alkuTs);
		System.out.println(loppuTs);
		

		kontrolleri.asetaVaraus( 4, tilanId, alkuTs, loppuTs);
		
	}

}
