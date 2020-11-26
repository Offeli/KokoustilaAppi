package com.varausjarjestelma.käyttöliittymä.varauksetikkuna;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.varausjarjestelma.kontrolleri.*;
import com.varausjarjestelma.malli.Varaukset;

public class VarausIkkuna extends BorderPane {
	
	private Kontrolleri kontrolleri;
	private static Varaukset[] varaukset;
	private StackPane root;
	
	public VarausIkkuna() {
		kontrolleri = Kontrolleri.haeInstanssi();
	}
	
	private void load() { // Hae tiedot kannasta
		varaukset = kontrolleri.haeKaikkiVaraukset();
		root = new StackPane();
	}
	
	public void clear() {
		if(varaukset != null) varaukset = null;
		if(root != null) root = null;
	}
	
	private void build() { // Aja tiedot käyttöliittymään
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
	
	private Varaukset[] sort(Varaukset[] varaukset) { // Lajittele varaukset päivämäärän mukaan
		ArrayList<Date> list = new ArrayList<Date>();
		
		for(Varaukset v : varaukset) {
			list.add(v.getAlkuAika());
		}
		
		Collections.sort(list);
		
		System.out.println(list);
		
		List<Varaukset> sorted = new ArrayList<>();
		Varaukset varaus;
		
		for(Date d : list) {
			for(int i = 0; i < varaukset.length; i++) {
				varaus = varaukset[i];
				if(varaus.getAlkuAika() == d && varaus.getLoppuAika().toLocalDateTime().isAfter(LocalDateTime.now())) {
					sorted.add(varaus);
				}
			}
		}
		
		return sorted.toArray(new Varaukset[sorted.size()]);
	}
	
	public StackPane getRoot() { // Palauta näkymä
		load();
		build();
		return root;
	}

}
