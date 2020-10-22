package com.varausjarjestelma.malli;

import javax.persistence.*;

@Entity
@Table(name = "Kayttaja")
public class Käyttäjä {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "etunimi", nullable = false)
	private String etunimi;

	@Column(name = "sukunimi", nullable = false)
	private String sukunimi;

	@Column(name = "sahkoposti", nullable = false)
	private String sposti;

	@Column(name = "hallinnoija", nullable = false)
	private boolean hallinnoija;
	
	@Column(name = "kieli", nullable = false)
	private String kieli;

	public Käyttäjä() {
	}
	
	public Käyttäjä(int id, String n, String m) {
		this.id = id;
		this.etunimi = n;
		this.sukunimi = m;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getEtunimi() {
		return etunimi;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public String getSukunimi() {
		return sukunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}
	
	public String getSposti() {
		return sposti;
	}

	public void setSposti(String sposti) {
		this.sposti = sposti;
	}

	public boolean getOikeudet() {
		return hallinnoija;
	}

	public void setOikeudet(boolean hallinnoija) {
		this.hallinnoija = hallinnoija;
	}
	
	public String getKieli() {
		return kieli;
	}

	public void setKieli(String kieli) {
		this.kieli = kieli;
	}

}
