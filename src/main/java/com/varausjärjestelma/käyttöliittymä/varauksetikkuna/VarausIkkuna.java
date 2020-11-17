package com.varausjärjestelma.käyttöliittymä.varauksetikkuna;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import com.varausjarjestelma.kontrolleri.*;
import com.varausjarjestelma.malli.Varaukset;

public class VarausIkkuna extends BorderPane {
	
	private Kontrolleri kontrolleri;
	private Varaukset[] varaukset;
	private StackPane root;
	
	public VarausIkkuna() {
		kontrolleri = Kontrolleri.haeInstanssi();
		varaukset = kontrolleri.haeKaikkiVaraukset();
		root = new StackPane();
        
        TilePane pane = new TilePane();
        pane.setHgap(10);
        pane.setVgap(10);
		
		for(final Varaukset v: varaukset) {
		    Button btn = new Button(v.getTila().getNimi());
		    
		    btn.setOnAction(new EventHandler<ActionEvent>() {
		    	
		    	public void handle(ActionEvent event) {
		    		VarausPopUp pop = new VarausPopUp(kontrolleri);
		    		pop.display(v);
		    	}
		    });
		    
		    pane.getChildren().add(btn);
		}
        
        root.getChildren().add(pane);
        
	}
	
	public StackPane getRoot() {
		return root;
	}

}
