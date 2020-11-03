package com.varausjarjestelma.nakyma.nakyma;

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

	public TilojenSelausNäkymä() {
		kontrolleri = new TilojenSelausKontrolleri(this);

		int col = 0, row = 0;
		GridPane grid = new GridPane();
		Tila[] tilat = kontrolleri.haeTilat();
		
		for (final Tila tila : tilat) {
			GridPane tilaPane = new GridPane();
			Text nimi = new Text(tila.getNimi());
			Text kaupunki = new Text(tila.getKaupunki());
			Text hlömäärä = new Text("Henkilömäärä: " + tila.getHlomaara());
			tilaPane.add(nimi, 0, 0);
			tilaPane.add(kaupunki, 0, 1);
			tilaPane.add(hlömäärä, 0, 2);
			tilaPane.setOnMouseClicked(new EventHandler<Event>() {

				public void handle(Event event) {
					System.out.println("Klikatun tilan ID = " + tila.getID());
				}

			});
			grid.add(tilaPane, col, row);
			if (++col > 1) {
				row++;
				col = 0;
			}
			tilaPane.setPadding(new Insets(5));
			tilaPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		}

		setCenter(grid);
		grid.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
		grid.setHgap(15);
		grid.setVgap(10);
		grid.setPadding(new Insets(20));
	}

}
