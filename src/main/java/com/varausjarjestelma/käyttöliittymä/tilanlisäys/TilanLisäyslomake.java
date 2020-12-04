package com.varausjarjestelma.käyttöliittymä.tilanlisäys;

import com.varausjarjestelma.i18n.I18n;
import com.varausjarjestelma.kontrolleri.Kontrolleri;
import com.varausjarjestelma.malli.Tila;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TilanLisäyslomake extends HBox {
	
	private final Kontrolleri kontrolleri;

	public TilanLisäyslomake() {
		kontrolleri = Kontrolleri.haeInstanssi();
		
		VBox kolumni1 = new VBox(), kolumni2 = new VBox(), nimiBox = new VBox(), osoiteBox = new VBox(),
				kaupunkiBox = new VBox(), hlömääräBox = new VBox(), kuvausBox = new VBox();

		Label nimiLabel = I18n.stringForLabel("nimi", null, null), osoiteLabel = I18n.stringForLabel("osoite", null, null), kaupunkiLabel = I18n.stringForLabel("kaupunki", null, null),
				hlömääräLabel = I18n.stringForLabel("henkilömäärä", null, null), kuvausLabel = I18n.stringForLabel("kuvaus", null, null);

		TextField nimi = new TextField(), osoite = new TextField(), kaupunki = new TextField();
		TextArea kuvaus = new TextArea();
		ObservableList<Integer> hlömääräVaihtoehdot = FXCollections.observableArrayList();

		for (int i = 1; i < 100; i++)
			hlömääräVaihtoehdot.add(i);

		ComboBox<Integer> hlömäärä = new ComboBox<>(hlömääräVaihtoehdot);
		Button vahvista = I18n.buttonForKey("button.vahvistatilanlisäys", null, null);
		
		vahvista.setOnAction(e -> {
			Tila tila = new Tila();
			
			tila.setHlomaara(hlömäärä.getValue());
			tila.setKaupunki(kaupunki.getText());
			tila.setKuvaus(kuvaus.getText());
			tila.setNakyvyys(true);
			tila.setNimi(nimi.getText());
			tila.setOsoite(osoite.getText());
			
			kontrolleri.lisääTila(tila);
		});
		
		setPadding(new Insets(50));
		setSpacing(20);
		
		kolumni1.setSpacing(10);
		kolumni2.setSpacing(10);
		
		hlömäärä.setVisibleRowCount(12);
		hlömäärä.setValue(1);
		kuvaus.setPrefWidth(300);
		kuvaus.setPrefRowCount(5);
		kuvaus.setWrapText(true);
		
		nimiBox.getChildren().addAll(nimiLabel, nimi);
		osoiteBox.getChildren().addAll(osoiteLabel, osoite);
		kaupunkiBox.getChildren().addAll(kaupunkiLabel, kaupunki);
		hlömääräBox.getChildren().addAll(hlömääräLabel, hlömäärä);
		kuvausBox.getChildren().addAll(kuvausLabel, kuvaus);

		kolumni1.getChildren().addAll(nimiBox, osoiteBox, kaupunkiBox);
		kolumni2.getChildren().addAll(hlömääräBox, kuvausBox, vahvista);
		
		getChildren().addAll(kolumni1, kolumni2);
	}

}
