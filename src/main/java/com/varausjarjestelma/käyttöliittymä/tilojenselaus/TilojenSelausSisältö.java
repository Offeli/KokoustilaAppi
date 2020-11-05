package com.varausjarjestelma.käyttöliittymä.tilojenselaus;

import com.varausjarjestelma.malli.Tila;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TilojenSelausSisältö extends AbstraktiKehyksenLapsi {

	private Tila[] tilat;
	private GridPane korostettu;

	public TilojenSelausSisältö(AbstraktiKehyksenLapsi seuraava) {
		super(seuraava);
		tilat = null;
	}

	public TilojenSelausSisältö(AbstraktiKehyksenLapsi seuraava, Tila[] tilat) {
		super(seuraava);
		this.tilat = tilat;
	}

	public void setTilat(Tila[] tilat) {
		this.tilat = tilat;
	}

	@Override
	public Pane luoPaneeli() {
		final TilePane paneeli = new TilePane();

		paneeli.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event event) {
				if (event.getTarget().equals(paneeli)) {
					poistaKorostus();
				}
			}
		});

		for (final Tila tila : tilat) {
			final GridPane tilaPane = new GridPane();
			Text nimi = new Text(tila.getNimi());
			Text kaupunki = new Text(tila.getKaupunki());
			Text hlömäärä = new Text("Henkilömäärä: " + tila.getHlomaara());
			tilaPane.add(nimi, 0, 0);
			tilaPane.add(kaupunki, 0, 1);
			tilaPane.add(hlömäärä, 0, 2);
			tilaPane.setOnMouseClicked(new EventHandler<Event>() {

				public void handle(Event event) {
					korosta(tilaPane);
					hoida(tila);
				}

			});
			paneeli.getChildren().add(tilaPane);
			tilaPane.setPadding(new Insets(5));
			tilaPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		}
		
		paneeli.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN, null, null)));
		paneeli.setHgap(15);
		paneeli.setVgap(10);
		paneeli.setPadding(new Insets(20));

		return paneeli;
	}

	@Override
	public void hoida(Object olio) {
		super.hoida(olio);
	}

	private void korosta(GridPane korostettava) {
		poistaKorostus();

		if (korostettava != null) {
			korostettu = korostettava;

			korostettu.setBackground(new Background(new BackgroundFill(Color.GOLD, null, null)));
		}
	}

	private void poistaKorostus() {
		if (korostettu != null) {
			korostettu.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			korostettu = null;
		}
	}

}
