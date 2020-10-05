package com.varausjarjestelma.nakyma;

import java.awt.event.ActionListener;

import com.varausjarjestelma.kontrolleri.Kontrolleri;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class FXkäyttöliittymä implements Käyttöliittymä {
	
	
		private Kontrolleri kontrolleri;
		@FXML private Text kokoustila1nimi;
		@FXML private Text kokoustila2nimi;
		@FXML private Text kokoustila3nimi;
		@FXML private Text kokoustila4nimi;
		
		@FXML private Text kokoustila1kaupunki;
		@FXML private Text kokoustila2kaupunki;
		@FXML private Text kokoustila3kaupunki;
		@FXML private Text kokoustila4kaupunki;
		
		@FXML private Text kokoustila1hlömäärä;
		@FXML private Text kokoustila2hlömäärä;
		@FXML private Text kokoustila3hlömäärä;
		@FXML private Text kokoustila4hlömäärä;
		
		@FXML private Text tilannimiDETAILS;
		@FXML private Text hlömääräDETAILS;
		@FXML private Text tilankuvausDETAILS;
		@FXML private Text tilanosoiteDETAILS;
		@FXML private Text tilankaupunkiDETAILS;
		
		
		@FXML private Button tilantarkasteluButton1;
		

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
		public void näytäTilanLisätiedot() {
		
			int id = 1;
			kontrolleri = new Kontrolleri();
			
			tilannimiDETAILS.setText(kontrolleri.tuoTila(id).getNimi());
		    hlömääräDETAILS.setText(String.valueOf(kontrolleri.tuoTila(id).getHlomaara()));
		    tilankuvausDETAILS.setText(kontrolleri.tuoTila(id).getKuvaus());
		    tilanosoiteDETAILS.setText(kontrolleri.tuoTila(id).getOsoite());
		    tilankaupunkiDETAILS.setText(kontrolleri.tuoTila(id).getKaupunki());
			
		}
		
		EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() 
		{
		    public void handle( final MouseEvent ME ) 
		    {
		        
		            System.out.println( "moi" );
		        
		    }
		};

		
		
		
		
		@FXML
	    public void initialize() {
			
			kontrolleri = new Kontrolleri();
			
			// haetaan tilojen 1,2 ja 3 nimet, kuvaukset ja kapasiteetit kannasta käyttöliittymään
			String tn1 = kontrolleri.tuoTila(1).getNimi();
			String tn2 = kontrolleri.tuoTila(2).getNimi();
			String tn3 = kontrolleri.tuoTila(3).getNimi();
			String tn4 = kontrolleri.tuoTila(4).getNimi();
			
			String tk1 = kontrolleri.tuoTila(1).getKaupunki();
			String tk2 = kontrolleri.tuoTila(2).getKaupunki();
			String tk3 = kontrolleri.tuoTila(3).getKaupunki();
			String tk4 = kontrolleri.tuoTila(4).getKaupunki();
			
			 int tmaara1 = kontrolleri.tuoTila(1).getHlomaara();
			 int tmaara2 = kontrolleri.tuoTila(2).getHlomaara();
			 int tmaara3 = kontrolleri.tuoTila(3).getHlomaara();
			 int tmaara4 = kontrolleri.tuoTila(4).getHlomaara();
			
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
