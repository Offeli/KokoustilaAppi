package com.varausjarjestelma.käyttöliittymä.tilojenselaus;

import javafx.scene.layout.Pane;

public abstract class AbstraktiKehyksenLapsi {

	private final AbstraktiKehyksenLapsi seuraava;

	public AbstraktiKehyksenLapsi(AbstraktiKehyksenLapsi seuraava) {
		this.seuraava = seuraava;
	}

	public void hoida(Object olio) {
		if (seuraava != null)
			seuraava.hoida(olio);
	}

	public void hoida(Object[] oliot) {
		if (seuraava != null)
			seuraava.hoida(oliot);
	}
	
	public abstract Pane luoPaneeli();

}
