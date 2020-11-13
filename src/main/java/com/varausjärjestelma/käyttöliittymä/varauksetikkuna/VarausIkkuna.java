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
		
		for(Varaukset v: varaukset) {
		    Label label = new Label(v.getTila().getNimi());
		    pane.getChildren().add(label);
		}
        
        root.getChildren().add(pane);
        
	}
	
	public StackPane getRoot() {
		return root;
	}

}
