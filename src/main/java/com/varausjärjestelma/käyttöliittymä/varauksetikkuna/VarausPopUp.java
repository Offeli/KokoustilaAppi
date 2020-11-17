package com.varausjärjestelma.käyttöliittymä.varauksetikkuna;

import com.varausjarjestelma.kontrolleri.Kontrolleri;
import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.malli.Varaukset;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;


public class VarausPopUp {
	private Kontrolleri kontrolleri;
	private Varaukset varaus;
	private String[] otsikot = { "Nimi", "Kaupunki", "Osoite",
			"Henkilömäärä", "Kuvaus", "Alkupäivämäärä",
			"Loppupäivämäärä", "Alkukellonaika", "Loppukellonaika" };
	
	public VarausPopUp(Kontrolleri k) {
		kontrolleri = k;
	}
   
	public void display(Varaukset v)
		{
			final Stage popupwindow=new Stage();
			      
			popupwindow.initModality(Modality.APPLICATION_MODAL);
			popupwindow.setTitle(v.getTila().getNimi());
			      
			Label labelNimi = new Label("Tilan nimi: " + v.getTila().getNimi());
			Label labelOsoite = new Label("Tilan osoite: " + v.getTila().getOsoite());
			Label labelKuvaus = new Label("Tilan kuvaus: " + v.getTila().getKuvaus());
			
			Label labelAlku = new Label("Varauksen alku: " + v.getAlkuAika());
			Label labelLoppu = new Label("Varauksen loppu: " + v.getLoppuAika());
			      
			     
			Button button1= new Button("Close this pop up window");     
			button1.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					popupwindow.close();
				}
			});
			
			VBox layout= new VBox(10);
			layout.getChildren().addAll(labelNimi, labelOsoite, labelKuvaus, labelAlku, labelLoppu, button1);
			layout.setAlignment(Pos.CENTER);
			Scene scene1= new Scene(layout, 300, 250); 
			popupwindow.setScene(scene1);
			popupwindow.showAndWait();
		       
		}

}
