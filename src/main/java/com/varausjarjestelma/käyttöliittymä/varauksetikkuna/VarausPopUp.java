package com.varausjarjestelma.käyttöliittymä.varauksetikkuna;

import java.text.SimpleDateFormat;

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
	
	public VarausPopUp(Kontrolleri k) {
		kontrolleri = k;
	}
   
	public void display(Varaukset v)
		{
			final Stage popupwindow=new Stage();
			      
			popupwindow.initModality(Modality.APPLICATION_MODAL);
			popupwindow.setTitle(v.getTila().getNimi());
			      
			// Printtaa varauksen tiedot näkyviin
			Label labelNimi = new Label("Tilan nimi: " + v.getTila().getNimi());
			Label labelOsoite = new Label("Tilan osoite: " + v.getTila().getOsoite());
			Label labelKuvaus = new Label("Tilan kuvaus: " + v.getTila().getKuvaus());
			
			Label labelAlku = new Label("Varauksen alku: " + new SimpleDateFormat("dd-MM-yyyy HH:mm").format(v.getAlkuAika()));
			Label labelLoppu = new Label("Varauksen loppu: " + new SimpleDateFormat("dd-MM-yyyy HH:mm").format(v.getLoppuAika()));
			
			// Luo sulje-nappi
			Button close = new Button("Close"); 
			close.setMaxWidth(100);
			close.setOnAction(new EventHandler<ActionEvent>() { // Sulje ikkuna
				public void handle(ActionEvent e) {
					popupwindow.close();
				}
			});
			
			// Lue poista-nappi
			Button delete = new Button("Delete");
			delete.setStyle("-fx-background-color: red; -fx-text-fill: white;");
			delete.setMaxWidth(150);
			delete.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) { // Poista varaus ja sulje ikkuna
					kontrolleri.poistaVaraus(v.getID());
					popupwindow.close();
				}
			});
			
			// Asettele näkymään
			VBox layout= new VBox(10);
			layout.getChildren().addAll(labelNimi, labelOsoite, labelKuvaus, labelAlku, labelLoppu, close);
			layout.setAlignment(Pos.CENTER);
			layout.getChildren().add(delete);
			layout.setAlignment(Pos.CENTER);
			Scene scene1= new Scene(layout, 300, 250); 
			popupwindow.setScene(scene1);
			popupwindow.showAndWait();
		       
		}

}
