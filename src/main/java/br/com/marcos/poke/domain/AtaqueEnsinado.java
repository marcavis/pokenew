package br.com.marcos.poke.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class AtaqueEnsinado extends GenericDomain{

	@ManyToOne
	@JoinColumn(nullable=false)
	private Ataque ataque;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Especime especime;

	public Ataque getAtaque() {
		return ataque;
	}

	public void setAtaque(Ataque ataque) {
		this.ataque = ataque;
	}

	public Especime getEspecime() {
		return especime;
	}

	public void setEspecime(Especime especime) {
		this.especime = especime;
	}
}
