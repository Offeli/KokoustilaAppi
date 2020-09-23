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
	
}
