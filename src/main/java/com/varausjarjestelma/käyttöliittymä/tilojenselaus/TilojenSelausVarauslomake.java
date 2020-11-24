package com.varausjarjestelma.käyttöliittymä.tilojenselaus;


import java.time.LocalDate;

import com.varausjarjestelma.i18n.I18n;
import com.varausjarjestelma.kontrolleri.Kontrolleri;
import com.varausjarjestelma.logiikka.Varausmanageri;
import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.sähköposti.SimppeliMaili;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TilojenSelausVarauslomake {
	
	int valitunTilanID;
	private Kalenteri calendar;
	ChoiceBox checkinvaihtoehdot = new ChoiceBox(FXCollections.observableArrayList());
	ChoiceBox checkoutvaihtoehdot = new ChoiceBox(FXCollections.observableArrayList());
	
	public TilojenSelausVarauslomake() {
		calendar = new Kalenteri();
	}
	
	public GridPane getVarausFormi() {
		
		GridPane gridpane = new GridPane();
		
		Label headerLabel = I18n.stringForLabel("varaatasta");
	    headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	    gridpane.add(headerLabel, 0,0,2,1);
	    gridpane.setHalignment(headerLabel, HPos.CENTER);
	    gridpane.setMargin(headerLabel, new Insets(10,10,10,10));
        
	    // Add Name Label
	    Label nameLabel = I18n.stringForLabel("nimesi");
	    gridpane.add(nameLabel, 0,1);

	    // Add Name Text Field
	    TextField nameField = new TextField();
	    nameField.setPrefHeight(40);
	    nameField.setPrefWidth(5);
	    gridpane.add(nameField, 1,1);


	    // Add Email Label
	    Label emailLabel = I18n.stringForLabel("sahkopostisi");
	    gridpane.add(emailLabel, 0, 2);

	    // Add Email Text Field
	    final TextField emailField = new TextField();
	    emailField.setPrefHeight(40);
	    gridpane.add(emailField, 1, 2);
	    
	    // Add aloitusaikalabel
	    Label aloitusaikaLabel = I18n.stringForLabel("aloitusaika");
	    gridpane.add(aloitusaikaLabel, 0, 3);
	    
	    // Add check-in vaihtoehdot
	    ObservableList<Integer> tuntilistaIN = FXCollections.observableArrayList();
		   tuntilistaIN.addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
		  checkinvaihtoehdot.setItems(tuntilistaIN);
	    	  
	    gridpane.add(checkinvaihtoehdot, 1, 3);
	    
	 // Add lopetussaikalabel
	    Label lopetusaikaLabel = I18n.stringForLabel("lopetusaika");
	    gridpane.add(lopetusaikaLabel, 0, 4);
	    
	    // Add check-out vaihtoehdot¨
	    
	    ObservableList<Integer> tuntilistaOUT = FXCollections.observableArrayList();
		   tuntilistaOUT.addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
		  checkoutvaihtoehdot.setItems(tuntilistaOUT);
		  
	    gridpane.add(checkoutvaihtoehdot, 1, 4);
	   

	    // Add Submit Button
	    Button submitButton = I18n.buttonForKey("button.vahvistavaraus");
	    submitButton.setTooltip(I18n.tooltipForKey("button.vahvistavaraus.tooltip"));
	    submitButton.setPrefHeight(40);
	    submitButton.setDefaultButton(true);
	    submitButton.setPrefWidth(100);
	    gridpane.add(submitButton, 0, 5, 2, 1);
	    gridpane.setHalignment(submitButton, HPos.CENTER);
	    gridpane.setMargin(submitButton, new Insets(10,10,10,10));
	    
	  //  gridpane.setBorder(new Border(new BorderStroke(Color.BLACK, 
	 //           BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	    gridpane.setMargin(gridpane, new Insets(20));
	    gridpane.setMaxWidth(300);
	    
	    submitButton.setOnAction(new EventHandler<ActionEvent>() {
	         public void handle(ActionEvent e) {
	        	 try {
	        		 lähetäVaraus();
	        		 Alert a = new Alert(AlertType.CONFIRMATION);
	        		 Tila t = Kontrolleri.haeInstanssi().etsiTila(valitunTilanID);
	        		 a.setHeaderText(t.getNimi()+" varattu, kiitos!");
	                 a.show();
	                 new SimppeliMaili().sendMail(emailField.getText());
				} catch (Exception ex) {
					ex.printStackTrace();
				}  
	        }
	    });
	    
	    return gridpane;

	}
	
	public Pane getKalenteri (int id) {
		Pane kalenteri = calendar.getRoot(id);
		System.out.println(calendar.getInDate() + " & " + calendar.getOutDate());
		kalenteri.setPrefWidth(200);
		kalenteri.setPrefHeight(150);
		kalenteri.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		return kalenteri;
	}
	
	public GridPane getKokoVarausLomake(Tila tila) {
		
		GridPane kokoLomake = new GridPane();
		GridPane formi = getVarausFormi();
		Pane kalenteripane = getKalenteri(tila.getID());
		kokoLomake.add(kalenteripane, 0, 0);
		kokoLomake.add(formi, 1, 0);
		kokoLomake.setMargin(kalenteripane, new Insets(40));
		valitunTilanID = tila.getID();
		
		return kokoLomake;
	}
	
	public void lähetäVaraus() {
		
		Varausmanageri varausmanageri = new Varausmanageri();
		
		ChoiceBox alkuTuntiPicker = checkinvaihtoehdot;
		ChoiceBox loppuTuntiPicker = checkoutvaihtoehdot;
		LocalDate varauksenAloitusPäivä = calendar.getInDate();
		LocalDate varauksenLopetusPäivä = calendar.getOutDate();
		int tilanId = valitunTilanID;
		varausmanageri.varaaTila(alkuTuntiPicker, loppuTuntiPicker, varauksenAloitusPäivä, varauksenLopetusPäivä, tilanId);
	}
	
	
}