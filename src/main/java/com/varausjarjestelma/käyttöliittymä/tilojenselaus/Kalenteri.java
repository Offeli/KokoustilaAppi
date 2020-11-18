package com.varausjarjestelma.käyttöliittymä.tilojenselaus;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import com.varausjarjestelma.i18n.I18n;
import com.varausjarjestelma.kontrolleri.Kontrolleri;
import com.varausjarjestelma.käyttöliittymä.TempMain;
import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.malli.Varaukset;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
 
public class Kalenteri{
 
    private DatePicker checkInDatePicker, checkOutDatePicker;
    private Kontrolleri kontrolleri;
    private Varaukset[] varaukset;
    private LocalDate[] ajat;
    private StackPane root;
    
    public Kalenteri() {
    	Locale.setDefault(Locale.UK);
    	kontrolleri = Kontrolleri.haeInstanssi(); 	
    }
    
    public StackPane getRoot(int tila) {
    	varaukset = kontrolleri.haeVarauksetTila(kontrolleri.etsiTila(tila));
    	ajat = new LocalDate[varaukset.length];
    	
    	root = new StackPane();
        
        TilePane pane = new TilePane();
        pane.setHgap(10);
        pane.setVgap(10);
        
        checkInDatePicker = new DatePicker();
        checkOutDatePicker = new DatePicker();
        checkInDatePicker.setValue(LocalDate.now());
        
        
     // Rajaa minä päivinä on varattavissa | Ei voi varata jos päivä on jo varattu tai mennyt
        final Callback<DatePicker, DateCell> dayCellFactoryVarauksetIn = 
                new Callback<DatePicker, DateCell>() {
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                
                                if (item.isBefore(
                                        LocalDate.now())
                                    ) {
                                        setDisable(true);
                                        //setStyle("-fx-background-color: #fffff;");
                                }
                                
                                for(int i = 0; i < varaukset.length; i++) {
                                	ajat[i] = (varaukset[i].getAlkuAika()).toLocalDateTime().toLocalDate();
                                }
                                
                                for(LocalDate l : ajat) {
                                	if(item.isEqual(l)) {
                                		setDisable(true);
                                        setStyle("-fx-background-color: #ffc0cb;");
                                	}
                                }
                        }
                    };
                }
            };
            
         // Rajaa minä päivinä on varattavissa | Ei voi varata jos päivä on jo varattu tai mennyt
            final Callback<DatePicker, DateCell> dayCellFactoryVarauksetOut = 
                    new Callback<DatePicker, DateCell>() {
                        public DateCell call(final DatePicker datePicker) {
                            return new DateCell() {
                                @Override
                                public void updateItem(LocalDate item, boolean empty) {
                                    super.updateItem(item, empty);
                                    
                                    if (item.isBefore(
                                            checkInDatePicker.getValue())
                                        ) {
                                            setDisable(true);
                                            //setStyle("-fx-background-color: #fffff;");
                                    }
                                    
                                    for(int i = 0; i < varaukset.length; i++) {
                                    	ajat[i] = (varaukset[i].getAlkuAika()).toLocalDateTime().toLocalDate();
                                    }
                                    
                                    for(LocalDate l : ajat) {
                                    	if(item.isEqual(l)) {
                                    		setDisable(true);
                                            setStyle("-fx-background-color: #ffc0cb;");
                                    	}
                                    }
                            }
                        };
                    }
                };
        
        checkInDatePicker.setDayCellFactory(dayCellFactoryVarauksetIn);
        checkOutDatePicker.setDayCellFactory(dayCellFactoryVarauksetOut);
        checkOutDatePicker.setValue(checkInDatePicker.getValue());
        
        EventHandler<ActionEvent> inEvent = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                // get the date picker value 
                LocalDate date = checkInDatePicker.getValue(); 
  
                System.out.println(date);
            } 
        };
        
        checkInDatePicker.setOnAction(inEvent);
        
        EventHandler<ActionEvent> outEvent = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                // get the date picker value 
                LocalDate date = checkOutDatePicker.getValue(); 
  
                System.out.println(date);
            } 
        };
        
        checkOutDatePicker.setOnAction(outEvent);
        
        @SuppressWarnings("restriction")
		DatePickerSkin datePickerSkinIn = new DatePickerSkin(checkInDatePicker);
        @SuppressWarnings("restriction")
		Node popupContentIn = datePickerSkinIn.getPopupContent();
        
        @SuppressWarnings("restriction")
		DatePickerSkin datePickerSkinOut = new DatePickerSkin(checkOutDatePicker);
        @SuppressWarnings("restriction")
		Node popupContentOut = datePickerSkinOut.getPopupContent();
        
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Label checkInlabel = I18n.stringForLabel("alkamispäivä");
        gridPane.add(checkInlabel, 0, 0);
        GridPane.setHalignment(checkInlabel, HPos.LEFT);
        gridPane.add(popupContentIn, 0, 1);
        Label checkOutlabel = I18n.stringForLabel("loppumispäivä");
        gridPane.add(checkOutlabel, 1, 0);
        GridPane.setHalignment(checkOutlabel, HPos.LEFT);
        gridPane.add(popupContentOut, 1, 1);
        
        root.getChildren().add(gridPane);
    	
    	return root;
    }
    
    public LocalDate getInDate() {
    	return checkInDatePicker.getValue();
    }
    
    public LocalDate getOutDate() {
    	return checkOutDatePicker.getValue();
    }
}
