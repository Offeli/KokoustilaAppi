package com.varausjarjestelma.malli;
import javax.persistence.*;

@Entity
@Table(name="TilanOminaisuus")
public class TilanOminaisuus {
	@Id
	@GeneratedValue	
	@Column(name="id", nullable=false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="tila", nullable=false)
	private Tila tila;
	
	@ManyToOne
	@JoinColumn(name="ominaisuus", nullable=false)
	private Ominaisuus ominaisuus;
	
	@Column(name="lisatietoa", length=500)
	private String lisatietoa;
	
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public Tila getTila() {
		return tila;
	}

	public void setTila(Tila tila) {
		this.tila = tila;
	}
	
	public Ominaisuus getOminaisuus() {
		return ominaisuus;
	}

	public void setOminaisuus(Ominaisuus ominaisuus) {
		this.ominaisuus = ominaisuus;
	}

	public String getLisatiedot() {
		return lisatietoa;
	}

	public void setLisatiedot(String lisatietoa) {
		this.lisatietoa = lisatietoa;
	}
	
}
