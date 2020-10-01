package com.varausjarjestelma.nakyma;

import com.varausjarjestelma.kontrolleri.Kontrolleri;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args)  {
		System.out.println("terve");
		Kontrolleri kontrolleri = new Kontrolleri();
		/*
		try {
			kontrolleri.tilojenTiedotTaulukkona();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
		Tekstikäyttöliittymä käyttöliittymä = new Tekstikäyttöliittymä(kontrolleri);
		
		kontrolleri.setKäyttöliittymä(käyttöliittymä);
		
		käyttöliittymä.käynnistä();
		käyttöliittymä.tilaEsiin();
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("App käynnistyi!");
		
		
		BorderPane ikkuna = new BorderPane();
		Scene scene = new Scene(ikkuna, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

