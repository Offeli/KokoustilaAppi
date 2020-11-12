package com.varausjarjestelma.käyttöliittymä.tilojenselaus;

import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TilojenSelausVarauslomake {
	
	public StackPane getVarauslomake() {
	      
	      StackPane stackPane = new StackPane(); 
	      
	      Text text = new Text("Varaa tila tästä!"); 
	      TextField emailfield = new TextField();
	      emailfield.maxWidth(50);
	      
	      
	      stackPane.getChildren().addAll(text, emailfield); 
	      return stackPane;

	}
	
	
}