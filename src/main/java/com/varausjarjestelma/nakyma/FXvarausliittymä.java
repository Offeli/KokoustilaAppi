package com.varausjarjestelma.nakyma;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.varausjarjestelma.kontrolleri.Kontrolleri;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class FXvarausliittymä implements Käyttöliittymä {
	
	@FXML
	private Button varauksenVahvistusButton;
	@FXML
	private DatePicker varauksenAloitusPäivä;
	@FXML
	private DatePicker varauksenLopetusPäivä;


	private Kontrolleri kontrolleri;
	
	

	
	public FXvarausliittymä(Kontrolleri kontrolleri) {
		this.kontrolleri = kontrolleri;
	}
	
	@FXML
	public void varaaTila() {
		
		kontrolleri = Kontrolleri.haeInstanssi();
		
		int tilaId = FXkäyttöliittymä.getVarausId();
		
		int alkuTunti = 0;
		int loppuTunti = 0;
		LocalDate alkuInit = varauksenAloitusPäivä.getValue();
		LocalDate loppuInit = varauksenLopetusPäivä.getValue();
		

		Timestamp alkuTs = Timestamp.valueOf(alkuInit.atTime(alkuTunti, 0));
		Timestamp loppuTs = Timestamp.valueOf(loppuInit.atTime(loppuTunti, 0));
		System.out.println(alkuTs);
		System.out.println(loppuTs);
		

		kontrolleri.asetaVaraus( 4, tilaId, alkuTs, loppuTs);
		
	}

	public void tulosta(String merkkijono) {
		// TODO Auto-generated method stub
		
	}
	

}
