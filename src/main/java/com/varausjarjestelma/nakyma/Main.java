package com.varausjarjestelma.nakyma;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	 private Stage primaryStage;
	 private VBox rootLayout;

	public static void main(String[] args)  {
		System.out.println("terve");
		launch(args);
		
		Tekstikäyttöliittymä käyttöliittymä = new Tekstikäyttöliittymä();
		
		käyttöliittymä.käynnistä();
		käyttöliittymä.tilaEsiin();
	}
	
	public void initRootLayout() {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("templateTilojenselaus.fxml"));
            rootLayout = (VBox) loader.load();
            
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
		 
		
	}

}

