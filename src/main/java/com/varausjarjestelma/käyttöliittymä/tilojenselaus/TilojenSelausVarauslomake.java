package com.varausjarjestelma.käyttöliittymä.tilojenselaus;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
	
	public GridPane getVarausFormi() {
		
		GridPane gridpane = new GridPane();
		
		Label headerLabel = new Label("Varaa tästä!");
	    headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	    gridpane.add(headerLabel, 0,0,2,1);
	    gridpane.setHalignment(headerLabel, HPos.CENTER);
	    gridpane.setMargin(headerLabel, new Insets(10,10,10,10));
        
	    // Add Name Label
	    Label nameLabel = new Label("Nimesi : ");
	    gridpane.add(nameLabel, 0,1);

	    // Add Name Text Field
	    TextField nameField = new TextField();
	    nameField.setPrefHeight(40);
	    nameField.setPrefWidth(5);
	    gridpane.add(nameField, 1,1);


	    // Add Email Label
	    Label emailLabel = new Label("Sähköpostisi : ");
	    gridpane.add(emailLabel, 0, 2);

	    // Add Email Text Field
	    TextField emailField = new TextField();
	    emailField.setPrefHeight(40);
	    gridpane.add(emailField, 1, 2);
	   

	    // Add Submit Button
	    Button submitButton = new Button("Vahvista varaus");
	    submitButton.setPrefHeight(40);
	    submitButton.setDefaultButton(true);
	    submitButton.setPrefWidth(100);
	    gridpane.add(submitButton, 0, 4, 2, 1);
	    gridpane.setHalignment(submitButton, HPos.CENTER);
	    gridpane.setMargin(submitButton, new Insets(10,10,10,10));
	    
	  //  gridpane.setBorder(new Border(new BorderStroke(Color.BLACK, 
	 //           BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	    gridpane.setMargin(gridpane, new Insets(20));
	    gridpane.setMaxWidth(300);
	    
	    return gridpane;

	}
	
	public Pane getKalenteri () {
		Pane kalenteri = new Kalenteri().getRoot(1);
		kalenteri.setPrefWidth(200);
		kalenteri.setPrefHeight(150);
		kalenteri.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		return kalenteri;
	}
	
	public GridPane getKokoVarausLomake() {
		
		GridPane kokoLomake = new GridPane();
		GridPane formi = getVarausFormi();
		Pane kalenteripane = getKalenteri();
		kokoLomake.add(formi, 0, 0);
		kokoLomake.add(kalenteripane, 1, 0);
		kokoLomake.setMargin(kalenteripane, new Insets(20));
		
		return kokoLomake;
	}
	
	
}