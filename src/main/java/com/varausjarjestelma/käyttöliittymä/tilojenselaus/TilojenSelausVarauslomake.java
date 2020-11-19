package com.varausjarjestelma.käyttöliittymä.tilojenselaus;


import com.varausjarjestelma.i18n.I18n;
import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.sähköposti.SimppeliMaili;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
	private Kalenteri calendar;
	
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
	    ChoiceBox checkinvaihtoehdot = new ChoiceBox(FXCollections.observableArrayList(
	    	    "5:00",  "6:00", "7:00", "8:00", "9:00", "10:00",  "11:00", "12:00", "13:00", "14:00", "15:00",
	    	    "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00")
	    	);
	    gridpane.add(checkinvaihtoehdot, 1, 3);
	    
	 // Add lopetussaikalabel
	    Label lopetusaikaLabel = I18n.stringForLabel("lopetusaika");
	    gridpane.add(lopetusaikaLabel, 0, 4);
	    
	    // Add check-out vaihtoehdot¨
	    ChoiceBox checkoutvaihtoehdot = new ChoiceBox(FXCollections.observableArrayList(
	    	    "6:00", "7:00", "8:00", "9:00", "10:00",  "11:00", "12:00", "13:00", "14:00", "15:00",
	    	    "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00")
	    	);
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
	        		 
	        		 Alert a = new Alert(AlertType.CONFIRMATION);
	        		 a.setHeaderText("Tila varattu. Kiitos!");
	                 a.show();
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
		
		return kokoLomake;
	}
	
	
}