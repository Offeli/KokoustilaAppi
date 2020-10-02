package com.varausjarjestelma.nakyma;

import java.io.IOException;

import com.varausjarjestelma.kontrolleri.Kontrolleri;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	
	 private Stage primaryStage;
	 private Pane rootLayout;

	public static void main(String[] args)  {
		System.out.println("terve");
		Kontrolleri kontrolleri = new Kontrolleri();
		
		Tekstikäyttöliittymä käyttöliittymä = new Tekstikäyttöliittymä(kontrolleri);
		
		kontrolleri.setKäyttöliittymä(käyttöliittymä);
		
		käyttöliittymä.käynnistä();
		käyttöliittymä.tilaEsiin();
		
		launch(args);
	}
	
	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("tilojenselaus.fxml"));
            rootLayout = (Pane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// TODO Auto-generated method stub
		
		System.out.println("App käynnistyi!");
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("KokousTilaApp");

		 initRootLayout();
		 
		/*
		BorderPane ikkuna = new BorderPane();
		Scene scene = new Scene(ikkuna, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		*/
	}

}

