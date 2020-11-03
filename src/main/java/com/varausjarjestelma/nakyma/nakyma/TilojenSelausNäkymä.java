package com.varausjarjestelma.nakyma.nakyma;

import java.util.ArrayList;
import java.util.List;

import com.varausjarjestelma.kontrolleri.TilojenSelausKontrolleri;
import com.varausjarjestelma.malli.Tila;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TilojenSelausNäkymä extends BorderPane {

	private final TilojenSelausKontrolleri kontrolleri;
	private final List<Pane> tilaPanet;

	public TilojenSelausNäkymä() {
		kontrolleri = new TilojenSelausKontrolleri(this);
		tilaPanet = new ArrayList<Pane>();

		final TilePane bg = new TilePane();
		Tila[] tilat = kontrolleri.haeTilat();

		bg.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event event) {
				if (event.getTarget().equals(bg)){
					setRight(null);
					poistaKorostus();
				}
			}
		});

		for (final Tila tila : tilat) {
			final GridPane tilaPane = new GridPane();
			Text nimi = new Text(tila.getNimi());
			Text kaupunki = new Text(tila.getKaupunki());
			Text hlömäärä = new Text("Henkilömäärä: " + tila.getHlomaara());
			tilaPanet.add(tilaPane);
			tilaPane.add(nimi, 0, 0);
			tilaPane.add(kaupunki, 0, 1);
			tilaPane.add(hlömäärä, 0, 2);
			tilaPane.setOnMouseClicked(new EventHandler<Event>() {

				public void handle(Event event) {
					poistaKorostus();
					tilaPane.setBackground(new Background(new BackgroundFill(Color.GOLD, null, null)));

					GridPane tiedot = new GridPane();
					String[] otsikot = { "Nimi", "Kaupunki", "Osoite", "Henkilömäärä", "Kuvaus" };
					String[] arvot = { tila.getNimi(), tila.getKaupunki(), tila.getOsoite(), "" + tila.getHlomaara(),
							tila.getKuvaus() };
					int row = 0;
					for (int i = 0; i < otsikot.length && i < arvot.length; i++) {
						String arvo = arvot[i];
						if (arvo != null && !arvo.isEmpty()) {
							tiedot.add(new Text(otsikot[i] + ":"), 0, row);
							tiedot.add(new Text(arvo), 1, row++);
						}
					}
					setRight(tiedot);
				}

			});
			bg.getChildren().add(tilaPane);
			tilaPane.setPadding(new Insets(5));
			tilaPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		}

		setCenter(bg);
		bg.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN, null, null)));
		bg.setHgap(15);
		bg.setVgap(10);
		bg.setPadding(new Insets(20));
	}
	
	private void poistaKorostus() {
		for (Pane pane : tilaPanet)
			pane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
	}

}
