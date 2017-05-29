package br.com.marcos.poke.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class RotaPokemon extends GenericDomain{

	@ManyToOne
	@JoinColumn(nullable=false)
	private Rota rota;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Pokemon pokemon;
	
	@Transient
	private String nomeDaRota;

	@Transient
	private String nomeDoPokemon;
	
	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
}
