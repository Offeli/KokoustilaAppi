package com.varausjarjestelma.käyttöliittymä.tilojenselaus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.varausjarjestelma.i18n.I18n;
import com.varausjarjestelma.kontrolleri.TilojenSelausKontrolleri;
import com.varausjarjestelma.malli.Tila;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TilojenSelaus extends BorderPane {

	private final TilojenSelausKontrolleri kontrolleri;
	private final List<Pane> tilaPanet;
	private GridPane korostettu;

	public TilojenSelaus() {
		kontrolleri = new TilojenSelausKontrolleri(this);
		tilaPanet = new ArrayList<Pane>();
		korostettu = null;
		final TilePane tausta = new TilePane();
		Tila[] tilat = kontrolleri.haeTilat();
		
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setSpacing(5);
		Button buttonEnglish = I18n.buttonForKey("button.english");
		buttonEnglish.setTooltip(I18n.tooltipForKey("button.english.tooltip"));
		buttonEnglish.setOnAction((evt) -> switchLanguage(Locale.ENGLISH));
		hbox.getChildren().add(buttonEnglish);
		Button buttonFinnish = I18n.buttonForKey("button.finnish");
		buttonFinnish.setTooltip(I18n.tooltipForKey("button.finnish.tooltip"));
		buttonFinnish.setOnAction((evt) -> switchLanguage(I18n.FINNISH));
		hbox.getChildren().add(buttonFinnish);
		setTop(hbox);


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
//					näytäLatausruutu();
					korosta(tilaPaneeli);

					ScrollPane selausikkuna = new ScrollPane();
					VBox tietoikkunanSisältö = new VBox();
					Text[] otsikot = { I18n.stringForText("nimi"), I18n.stringForText("kaupunki"), I18n.stringForText("osoite"),
							I18n.stringForText("henkilömäärä"), I18n.stringForText("kuvaus"), I18n.stringForText("ominaisuudet") };
					String[] arvot = { tila.getNimi(), tila.getKaupunki(), tila.getOsoite(), "" + tila.getHlomaara(),
							tila.getKuvaus(), kontrolleri.näytäTilanOminaisuudetStringinä(tila.getID()) };
					ScrollBar sivuttainenSelaus = new ScrollBar();
					int row = 0;
					
					tietoikkunanSisältö.setPadding(new Insets(0, 15, 0, 15));
					tietoikkunanSisältö.setSpacing(10);
//					tietoikkunanSisältö.setMinWidth(100);
//					tietoikkunanSisältö.setMaxWidth(100);
					
					for (int i = 0; i < otsikot.length && i < arvot.length; i++) {
						VBox tiedonKappale = new VBox();
						Text otsikko = otsikot[i];
						double fonttikoko = otsikko.getFont().getSize();
						otsikko.setFont(Font.font(null, FontWeight.BOLD, fonttikoko));
						String arvo = arvot[i];
						if (arvo != null && !arvo.isEmpty()) {
//							tiedot.add(otsikot[i], 0, row);
//							tiedot.add(new Text(arvo), 1, row++);
//							tiedonKappale.add(otsikko, 0, 0);
//							tiedonKappale.add(new Text(arvo), 0, 1);
							tiedonKappale.getChildren().add(otsikko);
							tiedonKappale.getChildren().add(new Text(arvo));
							tietoikkunanSisältö.getChildren().add(tiedonKappale);
						}
					}
					
//					sivuttainenSelaus.setMin(0);
//					sivuttainenSelaus.setMax(200);
//					sivuttainenSelaus.setValue(110);
//					sivuttainenSelaus.setOrientation(Orientation.HORIZONTAL);
//					sivuttainenSelaus.setUnitIncrement(12);
//					sivuttainenSelaus.setBlockIncrement(10);
//					
//					tietoikkunanSisältö.getChildren().add(sivuttainenSelaus);
					
					selausikkuna.setContent(tietoikkunanSisältö);
					selausikkuna.setMinWidth(225);
					selausikkuna.setMaxWidth(225);

//					setRight(tietoikkunanSisältö);
					setRight(selausikkuna);

					

					// StackPane n = new StackPane();
					Pane varauslomake = new TilojenSelausVarauslomake().getKokoVarausLomake(tila);					

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

//	HUOM! KÄYTTÄMÄTÖN, POISTOA HARKITAAN
//	private void näytäLatausruutu() {
//		Pane latausruutu = new StackPane();
//		setRight(latausruutu);
//
//		latausruutu.getChildren().add(new Text("Lataa..."));
//	}
	
    private void switchLanguage(Locale locale) {
        I18n.setLocale(locale);
    }


}
