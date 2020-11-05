package com.varausjarjestelma.käyttöliittymä.tilojenselaus;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;

import com.varausjarjestelma.kontrolleri.Kontrolleri;
import com.varausjarjestelma.käyttöliittymä.TempMain;
import com.varausjarjestelma.malli.Varaukset;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
 
public class Kalenteri extends TempMain{
 
    private Stage stage;
    private DatePicker checkInDatePicker, checkOutDatePicker;
    private final String pattern = "yyyy-MM-dd";
    private Kontrolleri kontrolleri;
    private Varaukset[] varaukset;
    private LocalDate[] ajat;
 
    public static void main(String[] args) {
        Locale.setDefault(Locale.UK);
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        kontrolleri = Kontrolleri.haeInstanssi();
        varaukset = kontrolleri.haeVarauksetTila(kontrolleri.etsiTila(1));
        ajat = new LocalDate[varaukset.length];
        stage.setTitle("DatePickerSample ");
        initUI();
        stage.show();
    }
 
    private void initUI() {
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);

        checkInDatePicker = new DatePicker();
        checkOutDatePicker = new DatePicker();
        checkInDatePicker.setValue(LocalDate.now());
        
        // Rajaa minä päivinä on varattavissa | Ei voi varata jos päivä on jo mennyt
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                           
                            if (item.isBefore(
                                    checkInDatePicker.getValue().plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }
                    }
                };
            }
        };
        
        
     // Rajaa minä päivinä on varattavissa | Ei voi varata jos päivä on jo varattu
        final Callback<DatePicker, DateCell> dayCellFactoryVaraukset = 
                new Callback<DatePicker, DateCell>() {
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                
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
        
        checkInDatePicker.setDayCellFactory(dayCellFactoryVaraukset);
        checkOutDatePicker.setDayCellFactory(dayCellFactory);
        checkOutDatePicker.setValue(checkInDatePicker.getValue().plusDays(1));
        
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Label checkInlabel = new Label("Check-In Date:");
        gridPane.add(checkInlabel, 0, 0);
        GridPane.setHalignment(checkInlabel, HPos.LEFT);
        gridPane.add(checkInDatePicker, 0, 1);
        Label checkOutlabel = new Label("Check-Out Date:");
        gridPane.add(checkOutlabel, 0, 2);
        GridPane.setHalignment(checkOutlabel, HPos.LEFT);
        gridPane.add(checkOutDatePicker, 0, 3);
        
        vbox.getChildren().add(gridPane);
    }
}
