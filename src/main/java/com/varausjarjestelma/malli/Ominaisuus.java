package com.varausjarjestelma.malli;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Ominaisuus")
public class Ominaisuus {
	@Id
	@GeneratedValue	
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="nimi", nullable=false)
	private String nimi;
	
	@Column(name="kuvaus", length=500)
	private String kuvaus;
	
	@OneToMany(mappedBy="TilanOminaisuus")
	List<TilanOminaisuus> tilanOminaisuudet;
	
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
	
	public List<TilanOminaisuus> getTilanOminaisuudet() {
		return tilanOminaisuudet;
	}

}
