package com.varausjarjestelma.nakyma;

import com.varausjarjestelma.nakyma.nakyma.TilojenSelausNäkymä;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TempMain extends Application {
	
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(new TilojenSelausNäkymä(), 800, 500));
		primaryStage.show();
		this.primaryStage = primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
