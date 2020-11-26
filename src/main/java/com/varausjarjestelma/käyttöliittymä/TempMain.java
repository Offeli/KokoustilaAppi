package com.varausjarjestelma.käyttöliittymä;

import java.util.Locale;

import com.varausjarjestelma.i18n.I18n;
import com.varausjarjestelma.käyttöliittymä.tilojenselaus.TilojenSelaus;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TempMain extends Application {
	
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane mainPane = new BorderPane();

		TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Tilat"  , new TilojenSelaus());
        tab1.closableProperty().setValue(false);
        Tab tab2 = new Tab("Lisää uusi tila", new Label("Diibadaabadaa.."));
        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        
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
