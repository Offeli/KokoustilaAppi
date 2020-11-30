package com.varausjarjestelma.käyttöliittymä.tilojenselaus;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import com.varausjarjestelma.i18n.I18n;
import com.varausjarjestelma.kontrolleri.Kontrolleri;
import com.varausjarjestelma.malli.Varaukset;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Callback;

/**
 * Producer of visual calendar elements that are used for picking dates.
 * 
 * @author V. Ahlstén
 * 
 */
public class Kalenteri {

	private DatePicker checkInDatePicker, checkOutDatePicker;
	private Kontrolleri kontrolleri;
	private Varaukset[] varaukset;
	private LocalDate[] ajat;
	private StackPane root;
	private ArrayList<Date> aukiolot;
	private List<String> alkuAukiolot;

	/**
	 * Constructor.
	 */
	public Kalenteri() {
		Locale.setDefault(Locale.UK);
		kontrolleri = Kontrolleri.haeInstanssi();
		setAukiolo();
	}
	
	public void setKalenteri(int tila) {
		varaukset = kontrolleri.haeVarauksetTila(kontrolleri.etsiTila(tila));
		ajat = new LocalDate[varaukset.length];
		root = new StackPane();
	}
	
	public void setAukiolo() {
		aukiolot = new ArrayList<Date>();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");

		for(int i = 5; i < 24; i++) {
			try {
				Date date = dateFormat.parse(i + ":00");
				aukiolot.add(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Creates a new stack pane that contains two date pickers. The date pickers
	 * retrieve information from the database via a controller and display the
	 * information relevant to the selected space's availability.
	 * 
	 * @param tila
	 * @return two date pickers within a new stack pane
	 */
	public StackPane getRoot() {

		TilePane pane = new TilePane();
		pane.setHgap(10);
		pane.setVgap(10);

		checkInDatePicker = new DatePicker();

		checkOutDatePicker = new DatePicker();
		checkInDatePicker.setValue(LocalDate.now());

		// Rajaa minä päivinä on varattavissa | Ei voi varata jos päivä on jo varattu
		// tai mennyt
		final Callback<DatePicker, DateCell> dayCellFactoryVarauksetIn = new Callback<DatePicker, DateCell>() {
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						setMinSize(30, 30);

						if (item.isBefore(LocalDate.now())) {
							setDisable(true);
							// setStyle("-fx-background-color: #fffff;");
						}

						for (int i = 0; i < varaukset.length; i++) {
							ajat[i] = (varaukset[i].getAlkuAika()).toLocalDateTime().toLocalDate();
						}

						for (LocalDate l : ajat) {
							if (item.isEqual(l)) {
								//setDisable(true);
								setStyle("-fx-background-color: #ffc0cb;");
							}
						}
					}
				};
			}
		};

		// Rajaa minä päivinä on varattavissa | Ei voi varata jos päivä on jo varattu
		// tai mennyt
		final Callback<DatePicker, DateCell> dayCellFactoryVarauksetOut = new Callback<DatePicker, DateCell>() {
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						setMinSize(30, 30);

						if (item.isBefore(checkInDatePicker.getValue())) {
							setDisable(true);
							// setStyle("-fx-background-color: #fffff;");
						}

						for (int i = 0; i < varaukset.length; i++) {
							ajat[i] = (varaukset[i].getAlkuAika()).toLocalDateTime().toLocalDate();
						}

						for (LocalDate l : ajat) {
							if (item.isEqual(l)) {
								//setDisable(true);
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
			public void handle(ActionEvent e) {
				// get the date picker value
				LocalDate date = checkInDatePicker.getValue();
				loadAlkuAukiolo();

				//System.out.println(date);
			}
		};

		checkInDatePicker.setOnAction(inEvent);

		EventHandler<ActionEvent> outEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// get the date picker value
				LocalDate date = checkOutDatePicker.getValue();
				//System.out.println(date);
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

	/**
	 * Returns the selected starting date for a reservation.
	 * 
	 * @return the value of the selected start date as a local date object
	 */
	public LocalDate getInDate() {
		return checkInDatePicker.getValue();
	}

	/**
	 * Returns the selected ending date for a reservation.
	 * 
	 * @return the value of the selected end date as a local date object
	 */
	public LocalDate getOutDate() {
		return checkOutDatePicker.getValue();
	}
	
	private void loadAlkuAukiolo() {
		System.out.println("Aukiolo latautuu.");
		alkuAukiolot = new ArrayList<String>();
		
		for(int i = 0; i < varaukset.length; i++) {
			LocalDate v = varaukset[i].getAlkuAika().toLocalDateTime().toLocalDate();
			
			if(v.equals(checkInDatePicker.getValue())) {
				int formatted = varaukset[i].getAlkuAika().getHours();
				
				for(Date d : aukiolot) {
					
					if(d.getHours() == formatted) {
						alkuAukiolot.add("VARATTU");
					}else {
						alkuAukiolot.add(Integer.toString(d.getHours()));
					}
				}
				
			}else {
				for(Date d : aukiolot) {
					alkuAukiolot.add(Integer.toString(d.getHours()));
				}
			}
		}
	}
	
	private void removeDuplicates() {
		ArrayList<String> newList = new ArrayList<String>();
		
		for(String s : alkuAukiolot) {
			if(!newList.contains(s)) {
				newList.add(s);
			}
		}
		
		alkuAukiolot = new ArrayList<String>(newList);
	}
	
	public ObservableList<String> getAlkuAukiolo(){
		ObservableList<String> palautus = FXCollections.observableArrayList();
		loadAlkuAukiolo();
		removeDuplicates();
		
		System.out.println(alkuAukiolot.size());
		
		for(String i : alkuAukiolot) {
			palautus.add(i);
		}
		
		return palautus;
	}
	
	public void getLoppuAukiolo() {}
}
