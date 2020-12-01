package com.varausjarjestelma.käyttöliittymä;

import java.util.Locale;

import com.varausjarjestelma.i18n.I18n;
import com.varausjarjestelma.käyttöliittymä.tilanlisäys.TilanLisäyslomake;
import com.varausjarjestelma.käyttöliittymä.tilojenselaus.TilojenSelaus;
import com.varausjarjestelma.käyttöliittymä.varauksetikkuna.VarausIkkuna;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TempMain extends Application {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane mainPane = new BorderPane();

		TabPane tabPane = new TabPane();

		TilojenSelaus tilojenselaus = new TilojenSelaus();
		StackPane varausikkuna = new VarausIkkuna().getRoot();
		TilanLisäyslomake tilanlisäys = new TilanLisäyslomake();

		Tab tab1 = new Tab();
		tab1.setContent(tilojenselaus);
		tab1.setText("Tilat");
		tab1.closableProperty().setValue(false);
		Tab tab2 = new Tab();
		tab2.setContent(varausikkuna);
		tab2.setText("Varaukset");
		tab2.closableProperty().setValue(false);
		Tab tab3 = new Tab();
		tab3.setContent(tilanlisäys);
		tab3.setText("Lisää uusi tila");
		
		tabPane.getTabs().addAll(tab1, tab2, tab3);

		mainPane.setTop(new TilojenSelaus().kieliboxi());
		mainPane.setCenter(tabPane);

		primaryStage.setScene(new Scene(mainPane, 1000, 600));
		primaryStage.show();
		this.primaryStage = primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
