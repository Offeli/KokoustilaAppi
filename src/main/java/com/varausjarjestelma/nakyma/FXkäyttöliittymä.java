package com.varausjarjestelma.nakyma;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import com.varausjarjestelma.kontrolleri.Kontrolleri;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXkäyttöliittymä implements Käyttöliittymä {

	private Kontrolleri kontrolleri;
	@FXML
	private Text kokoustila1nimi;
	@FXML
	private Text kokoustila2nimi;
	@FXML
	private Text kokoustila3nimi;
	@FXML
	private Text kokoustila4nimi;

	@FXML
	private Text kokoustila1kaupunki;
	@FXML
	private Text kokoustila2kaupunki;
	@FXML
	private Text kokoustila3kaupunki;
	@FXML
	private Text kokoustila4kaupunki;

	@FXML
	private Text kokoustila1hlömäärä;
	@FXML
	private Text kokoustila2hlömäärä;
	@FXML
	private Text kokoustila3hlömäärä;
	@FXML
	private Text kokoustila4hlömäärä;

	@FXML
	private Label tilannimiDETAILS;
	@FXML
	private Label hlömääräDETAILS;
	@FXML
	private Label tilankuvausDETAILS;
	@FXML
	private Label tilanosoiteDETAILS;
	@FXML
	private Label tilankaupunkiDETAILS;
	@FXML
	private Label tilanominaisuusDETAILS;

	@FXML
	private Button tilantarkasteluButton1;
	@FXML
	private Button tilantarkasteluButton2;
	@FXML
	private Button tilantarkasteluButton3;
	@FXML
	private Button tilantarkasteluButton4;
	
	@FXML
	private Button tilanVarausButton1;
	@FXML
	private Button tilanVarausButton2;
	@FXML
	private Button tilanVarausButton3;
	@FXML
	private Button tilanVarausButton4;
	@FXML
	int tilanId = 0;

	@FXML
	private DatePicker varauksenAloitusPäivä;
	@FXML
	private DatePicker varauksenLopetusPäivä;
	




	public FXkäyttöliittymä(Kontrolleri kontrolleri) {
		this.kontrolleri = kontrolleri;
	}

	public FXkäyttöliittymä() {

	}

	public void tulosta(String merkkijono) {
		System.out.println(merkkijono);
	}

	@FXML
	public void annaTilalleNimi() {

	}


	@FXML
	public void näytäTilanLisätiedot(ActionEvent e) {

		int id = 0;
		// hakee kannasta tilaiideellä lisätiedot
		if (e.getSource() == tilantarkasteluButton1) {
			id = 1;
		} else if (e.getSource() == tilantarkasteluButton2) {
			id = 2;
		} else if (e.getSource() == tilantarkasteluButton3) {
			id = 3;
		} else if (e.getSource() == tilantarkasteluButton4) {
			id = 4;
		}

		kontrolleri = Kontrolleri.haeInstanssi();

		tilannimiDETAILS.setText(kontrolleri.etsiTila(id).getNimi());
		hlömääräDETAILS.setText(String.valueOf(kontrolleri.etsiTila(id).getHlomaara()));
		tilankuvausDETAILS.setText(kontrolleri.etsiTila(id).getKuvaus());
		tilanosoiteDETAILS.setText(kontrolleri.etsiTila(id).getOsoite());
		tilankaupunkiDETAILS.setText(kontrolleri.etsiTila(id).getKaupunki());

	}
	
	@FXML
	public void varaaTila(ActionEvent event) {
		
		if (event.getSource() == tilanVarausButton1) {
			tilanId = 1;
		} else if (event.getSource() == tilanVarausButton2) {
			tilanId = 2;
		} else if (event.getSource() == tilanVarausButton3) {
			tilanId = 3;
		} else if (event.getSource() == tilanVarausButton4) {
			tilanId = 4;
		}
		
		kontrolleri = Kontrolleri.haeInstanssi();
		
		
		
		int alkuTunti = 0;
		int loppuTunti = 0;
		LocalDate alkuInit = varauksenAloitusPäivä.getValue();
		LocalDate loppuInit = varauksenLopetusPäivä.getValue();
		

		Timestamp alkuTs = Timestamp.valueOf(alkuInit.atTime(alkuTunti, 0));
		Timestamp loppuTs = Timestamp.valueOf(loppuInit.atTime(loppuTunti, 0));
		System.out.println(alkuTs);
		System.out.println(loppuTs);
		

		kontrolleri.asetaVaraus( 4, tilanId, alkuTs, loppuTs);
		
	}
	/*
	@FXML
	public void näytäVarausIkkuna(ActionEvent event) throws IOException {
		
			
			if (event.getSource() == tilanVarausButton1) {
				tilanId = 1;
			} else if (event.getSource() == tilanVarausButton2) {
				tilanId = 2;
			} else if (event.getSource() == tilanVarausButton3) {
				tilanId = 3;
			} else if (event.getSource() == tilanVarausButton4) {
				tilanId = 4;
			}
			System.out.println(tilanId);
		
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VarausIkkuna.fxml"));
		    AnchorPane anchorpane = new AnchorPane();
		    fxmlLoader.setRoot(anchorpane);
		    AnchorPane varausikkuna = (AnchorPane) fxmlLoader.load();
		    Stage stage = new Stage();
		    stage.setScene(new Scene(varausikkuna));  
		    stage.show();
		
	}
	*/
	@FXML
	public void initialize() {

		kontrolleri = Kontrolleri.haeInstanssi();

		String tn1 = kontrolleri.etsiTila(1).getNimi();
		String tn2 = kontrolleri.etsiTila(2).getNimi();
		String tn3 = kontrolleri.etsiTila(3).getNimi();
		String tn4 = kontrolleri.etsiTila(4).getNimi();

		String tk1 = kontrolleri.etsiTila(1).getKaupunki();
		String tk2 = kontrolleri.etsiTila(2).getKaupunki();
		String tk3 = kontrolleri.etsiTila(3).getKaupunki();
		String tk4 = kontrolleri.etsiTila(4).getKaupunki();

		int tmaara1 = kontrolleri.etsiTila(1).getHlomaara();
		int tmaara2 = kontrolleri.etsiTila(2).getHlomaara();
		int tmaara3 = kontrolleri.etsiTila(3).getHlomaara();
		int tmaara4 = kontrolleri.etsiTila(4).getHlomaara();

		kokoustila1nimi.setText(tn1);
		kokoustila2nimi.setText(tn2);
		kokoustila3nimi.setText(tn3);
		kokoustila4nimi.setText(tn4);

		kokoustila1kaupunki.setText(tk1);
		kokoustila2kaupunki.setText(tk2);
		kokoustila3kaupunki.setText(tk3);
		kokoustila4kaupunki.setText(tk4);

		kokoustila1hlömäärä.setText(String.valueOf(tmaara1));
		kokoustila2hlömäärä.setText(String.valueOf(tmaara2));
		kokoustila3hlömäärä.setText(String.valueOf(tmaara3));
		kokoustila4hlömäärä.setText(String.valueOf(tmaara4));

	}

}

