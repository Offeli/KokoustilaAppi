package com.varausjarjestelma.käyttöliittymä.tilanlisäys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TilanLisäyslomake extends HBox {

	public TilanLisäyslomake() {
		VBox kolumni1 = new VBox(), kolumni2 = new VBox(), nimiBox = new VBox(), osoiteBox = new VBox(),
				kaupunkiBox = new VBox(), hlömääräBox = new VBox(), kuvausBox = new VBox();

		Label nimiLabel = new Label("Test:"), osoiteLabel = new Label("Test:"), kaupunkiLabel = new Label(),
				hlömääräLabel = new Label("Henkilömäärä:"), kuvausLabel = new Label();

		TextField nimi = new TextField(), osoite = new TextField(), kaupunki = new TextField();
		TextArea kuvaus = new TextArea();
		ObservableList<Integer> hlömääräVaihtoehdot = FXCollections.observableArrayList();

		for (int i = 1; i < 100; i++)
			hlömääräVaihtoehdot.add(i);

		ComboBox<Integer> hlömäärä = new ComboBox<>(hlömääräVaihtoehdot);
		
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
		kolumni2.getChildren().addAll(hlömääräBox, kuvausBox);
		
		getChildren().addAll(kolumni1, kolumni2);
	}

}
