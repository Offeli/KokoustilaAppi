package com.varausjarjestelma.käyttöliittymä.varauksetikkuna;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestiMain extends Application {

	// Testaamiseen luotu main
	private Stage primaryStage;

	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(new VarausIkkuna().getRoot(), 500, 350));
		primaryStage.show();
		this.primaryStage = primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
