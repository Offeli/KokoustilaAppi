package com.varausjarjestelma.malli;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Varaukset")
public class Varaukset {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="kayttaja", nullable=false)
	private Käyttäjä kayttaja;

	@ManyToOne
	@JoinColumn(name="tila", nullable=false)
	private Tila tila;
	
	@Column(name = "alkuaika", nullable = false)
	private java.sql.Timestamp alkuaika;

	@Column(name = "loppuaika", nullable = false)
	private java.sql.Timestamp loppuaika;

	@Column(name = "nakyvyys", nullable = false)
	private boolean nakyvyys;

	@Column(name = "kaupunki", nullable = false)
	private String kaupunki;

	public Varaukset() {
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public Käyttäjä getKayttaja() {
		return kayttaja;
	}

	public void setKayttaja(Käyttäjä kayttaja) {
		this.kayttaja = kayttaja;
	}

	public Tila getTila() {
		return tila;
	}

	public void setTila(Tila tila) {
		this.tila = tila;
	}

	public Timestamp getAlkuAika() {
		return alkuaika;
	}

	public void setAlkuAika(Timestamp alku) {
		this.alkuaika = alku;
	}
	
	public Timestamp getLoppuAika() {
		return loppuaika;
	}

	public void setLoppuAika(Timestamp loppu) {
		this.loppuaika = loppu;
	}

}