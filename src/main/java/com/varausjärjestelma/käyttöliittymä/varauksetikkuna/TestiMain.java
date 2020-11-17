package com.varausjärjestelma.käyttöliittymä.varauksetikkuna;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestiMain extends Application {

	private Stage primaryStage;

	public void start(Stage primaryStage) throws Exception {
		VarausIkkuna varausIkkuna = new VarausIkkuna();
		primaryStage.setScene(new Scene(varausIkkuna.getRoot(), 500, 350));
		primaryStage.show();
		this.primaryStage = primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
