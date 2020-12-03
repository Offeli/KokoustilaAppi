package com.varausjarjestelma.käyttöliittymä.varauksetikkuna;

import java.text.SimpleDateFormat;

import com.varausjarjestelma.i18n.I18n;
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
			
			Label labelsNimi = I18n.stringForLabel("popupnimi");
			Label labelsOsoite = I18n.stringForLabel("popuposoite");
			Label labelsKuvaus = I18n.stringForLabel("popupkuvaus");
			
			Label labelsAlku = I18n.stringForLabel("popupalku");
			Label labelsLoppu = I18n.stringForLabel("popuploppu");
			
			Label labelNimi = new Label(v.getTila().getNimi());
			Label labelOsoite = new Label(v.getTila().getOsoite());
			Label labelKuvaus = new Label(v.getTila().getKuvaus());
			
			Label labelAlku = new Label(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(v.getAlkuAika()));
			Label labelLoppu = new Label(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(v.getLoppuAika()));
			
			// Luo sulje-nappi
			Button close = I18n.buttonForKey("button.sulje");
			close.setTooltip(I18n.tooltipForKey("button.sulje.tooltip"));
			close.setMaxWidth(100);
			close.setOnAction(new EventHandler<ActionEvent>() { // Sulje ikkuna
				public void handle(ActionEvent e) {
					popupwindow.close();
				}
			});
			
			// Lue poista-nappi
			Button delete = I18n.buttonForKey("button.poista");
			delete.setTooltip(I18n.tooltipForKey("button.poista.tooltip"));
			delete.setStyle("-fx-background-color: red; -fx-text-fill: white;");
			delete.setMaxWidth(150);
			delete.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) { // Poista varaus ja sulje ikkuna
					kontrolleri.poistaVaraus(v.getID());
					popupwindow.close();
				}
			});
			
			// Asettele näkymään
			GridPane layout= new GridPane();
			layout.add(labelsNimi, 0, 0);
			layout.add(labelNimi, 1, 0);
			layout.add(labelsOsoite, 0, 1);
			layout.add(labelOsoite, 1, 1);
			layout.add(labelsKuvaus, 0, 2);
			layout.add(labelKuvaus, 1, 2);
			layout.add(labelsAlku, 0, 3);
			layout.add(labelAlku, 1, 3);
			layout.add(labelsLoppu, 0, 4);
			layout.add(labelLoppu, 1, 4);
			layout.add(delete, 0, 5);
			layout.add(close, 1, 5);
			layout.setAlignment(Pos.CENTER);
			layout.setHgap(5);
			layout.setVgap(10);
			Scene scene1= new Scene(layout, 300, 250); 
			popupwindow.setScene(scene1);
			popupwindow.showAndWait();
		       
		}

}
