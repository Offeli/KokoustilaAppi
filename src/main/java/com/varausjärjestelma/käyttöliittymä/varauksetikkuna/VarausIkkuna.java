package com.varausjärjestelma.käyttöliittymä.varauksetikkuna;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import com.varausjarjestelma.kontrolleri.*;

public class VarausIkkuna extends BorderPane {
	
	private Kontrolleri kontrolleri;
	private StackPane root;
	
	public VarausIkkuna() {
		//kontrolleri = Kontrolleri.haeInstanssi();
		
		Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        root = new StackPane();
        root.getChildren().add(btn);
	}
	
	public StackPane getRoot() {
		return root;
	}

}
