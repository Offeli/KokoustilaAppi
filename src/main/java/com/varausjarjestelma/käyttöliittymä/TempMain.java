package com.varausjarjestelma.käyttöliittymä;

import com.varausjarjestelma.käyttöliittymä.tilojenselaus.TilojenSelaus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TempMain extends Application {
	
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(new TilojenSelaus(), 1000, 600));
		primaryStage.show();
		this.primaryStage = primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
