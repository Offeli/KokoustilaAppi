package com.varausjarjestelma.malli;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Tila")
public class Tila {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "nimi", nullable = false)
	private String nimi;

	@Column(name = "kuvaus", length = 500)
	private String kuvaus;

	@Column(name = "osoite", nullable = false)
	private String osoite;

	@Column(name = "hlomaara", nullable = false)
	private int hlomaara;

	@Column(name = "nakyvyys", nullable = false)
	private boolean nakyvyys;

	@Column(name = "kaupunki", nullable = false)
	private String kaupunki;
	
	@OneToMany(mappedBy = "TilanOminaisuus")
	private List<TilanOminaisuus> tilanOminaisuudet;

	public Tila() {
	}
	
	public Tila(int id, String n ) {
		this.id = id;
		this.nimi = n;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public int getHlomaara() {
		return hlomaara;
	}

	public void setHlomaara(int hlomaara) {
		this.hlomaara = hlomaara;
	}

	public String getKaupunki() {
		return kaupunki;
	}

	public void setKaupunki(String kaupunki) {
		this.kaupunki = kaupunki;
	}

	public String getOsoite() {
		return osoite;
	}

	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}

	public boolean getNakyvyys() {
		return nakyvyys;
	}

	public void setNakyvyys(boolean nakyvyys) {
		this.nakyvyys = nakyvyys;
	}
	
	public List<TilanOminaisuus> getTilanOminaisuudet() {
		return tilanOminaisuudet;
	}

}
