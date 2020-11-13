package com.varausjarjestelma.käyttöliittymä.tilojenselaus;

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

public class TilojenSelausKehys extends BorderPane {

	private final TilojenSelausKontrolleri kontrolleri;
	private final List<Pane> tilaPanet;
	private GridPane korostettu;

	public TilojenSelausKehys() {
		kontrolleri = new TilojenSelausKontrolleri(this);
		tilaPanet = new ArrayList<Pane>();
		korostettu = null;
		final TilePane tausta = new TilePane();
		Tila[] tilat = kontrolleri.haeTilat();

		tausta.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event event) {
				if (event.getTarget().equals(tausta)) {
					setRight(null);
					setBottom(null);
					poistaKorostus();
				}
			}
		});

		for (final Tila tila : tilat) {
			final GridPane tilaPaneeli = new GridPane();
			Text nimi = new Text(tila.getNimi());
			Text kaupunki = new Text(tila.getKaupunki());
			Text hlömäärä = new Text("Henkilömäärä: " + tila.getHlomaara());
			tilaPanet.add(tilaPaneeli);
			tilaPaneeli.add(nimi, 0, 0);
			tilaPaneeli.add(kaupunki, 0, 1);
			tilaPaneeli.add(hlömäärä, 0, 2);
			tilaPaneeli.setOnMouseClicked(new EventHandler<Event>() {

				public void handle(Event event) {
					näytäLatausruutu();
					korosta(tilaPaneeli);

					GridPane tiedot = new GridPane();
					String[] otsikot = { "Nimi", "Kaupunki", "Osoite", "Henkilömäärä", "Kuvaus", "Ominaisuudet" };
					String[] arvot = { tila.getNimi(), tila.getKaupunki(), tila.getOsoite(), "" + tila.getHlomaara(),
							tila.getKuvaus(), kontrolleri.näytäTilanOminaisuudetStringinä(tila.getID()) };
					int row = 0;
					for (int i = 0; i < otsikot.length && i < arvot.length; i++) {
						String arvo = arvot[i];
						if (arvo != null && !arvo.isEmpty()) {
							tiedot.add(new Text(otsikot[i] + ":"), 0, row);
							tiedot.add(new Text(arvo), 1, row++);
						}
					}
					setRight(tiedot);
					

					// StackPane n = new StackPane();
					Pane varauslomake = new TilojenSelausVarauslomake().getKokoVarausLomake();					

					varauslomake.setPadding(new Insets(30));

					setBottom(varauslomake);

				}

			});
			tausta.getChildren().add(tilaPaneeli);
			tilaPaneeli.setPadding(new Insets(5));
			tilaPaneeli.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		}

		setCenter(tausta);
		tausta.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN, null, null)));
		tausta.setHgap(15);
		tausta.setVgap(10);
		tausta.setPadding(new Insets(20));
	}

	private void korosta(GridPane korostettava) {
		poistaKorostus();

		if (korostettava != null)
			korostettava.setBackground(new Background(new BackgroundFill(Color.GOLD, null, null)));

		korostettu = korostettava;
	}

	private void poistaKorostus() {
		if (korostettu != null) {
			korostettu.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			korostettu = null;
		}
	}

	private void näytäLatausruutu() {
		Pane latausruutu = new StackPane();
		setRight(latausruutu);

		latausruutu.getChildren().add(new Text("Lataa..."));
	}

}
