package com.varausjarjestelma.malli;
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
