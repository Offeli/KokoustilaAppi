package com.varausjarjestelma.kontrolleri;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.varausjarjestelma.malli.TestConnection;
import com.varausjarjestelma.malli.Tila;
import com.varausjarjestelma.nakyma.Käyttöliittymä;
import com.varausjarjestelma.malli.TilaDAO;

public class Kontrolleri {
	
	private Käyttöliittymä käyttöliittymä;
	private TestConnection tc;
	private TilaDAO tilaDAO;
	
	public Kontrolleri() {
		tc = new TestConnection();
		tilaDAO = new TilaDAO();
		käyttöliittymä = null;
	}
	
	public void setKäyttöliittymä(Käyttöliittymä käyttöliittymä) {
		this.käyttöliittymä = käyttöliittymä;
	}
	
//	public Kontrolleri(Käyttöliittymä käyttöliittymä) {
//		this.käyttöliittymä = käyttöliittymä;
//		tc = new TestConnection();
//	}

	public Tila[] haeEsimerkki() {
		 return tilaDAO.haeKaikkiTilat();
	}
	
	public Tila tuoTila(int i) {
		int id = i;
		return tilaDAO.etsiTila(id);
	}
	
	
	
	public Object[] tilojenTiedotTaulukkona() throws SQLException {
		
		ResultSet rs =  tc.haeEsimerkkiData();
		 ArrayList<String> rivitListana = new ArrayList<String>();
		int riveja = 0;
		 while (rs.next()) {
		    riveja++;
			int id= rs.getInt("id");
			String nimi= rs.getString("nimi");
			String kuvaus = rs.getString("kuvaus");
		    rivitListana.add(id+" "+nimi+ " "+ kuvaus); 
        } 
		 Object[] rivitTaulukkona =  rivitListana.toArray();
		 for (int i = 0; i<rivitTaulukkona.length; i++) {
			 System.out.println(rivitTaulukkona[i]);
		 }
		 System.out.println("(rivejä yhteensä: " + riveja + ")");
		return rivitTaulukkona;
	}
	
	
}
