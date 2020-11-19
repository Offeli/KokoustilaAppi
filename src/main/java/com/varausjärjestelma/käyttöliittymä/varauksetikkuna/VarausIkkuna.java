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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
        
        varaukset = sort(varaukset);
		
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
	
	private Varaukset[] sort(Varaukset[] varaukset) {
		ArrayList<Date> list = new ArrayList<Date>();
		
		for(Varaukset v : varaukset) {
			list.add(v.getAlkuAika());
		}
		
		Collections.sort(list);
		
		System.out.println(list);
		
		Varaukset[] sorted = new Varaukset[varaukset.length];
		
		for(Date d : list) {
			for(int i = 0; i < varaukset.length; i++) {
				if(varaukset[i].getAlkuAika() == d) {
					sorted[i] = varaukset[i];
					//System.out.println(sorted[i].getAlkuAika());
				}
			}
		}
		
		return sorted;
	}
	
	public StackPane getRoot() {
		return root;
	}

}
