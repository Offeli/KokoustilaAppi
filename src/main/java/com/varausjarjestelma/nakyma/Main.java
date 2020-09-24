package com.varausjarjestelma.nakyma;

import java.sql.SQLException;

import com.varausjarjestelma.kontrolleri.Kontrolleri;
import com.varausjarjestelma.malli.TilaDAO;

public class Main {

	public static void main(String[] args)  {
		System.out.println("terve");
		Kontrolleri kontrolleri = new Kontrolleri();
		try {
			kontrolleri.tilojenTiedotTaulukkona();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	//	Tekstikäyttöliittymä käyttöliittymä = new Tekstikäyttöliittymä(kontrolleri);
		
	//	kontrolleri.setKäyttöliittymä(käyttöliittymä);
		
	//	käyttöliittymä.käynnistä();
	}

}
