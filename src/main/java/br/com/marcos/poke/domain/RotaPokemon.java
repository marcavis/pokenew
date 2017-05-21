package br.com.marcos.poke.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@SuppressWarnings("serial")
@Entity
public class RotaPokemon extends GenericDomain{

	@JoinColumn(nullable=false)
	private Long rota;
	
	@JoinColumn(nullable=false)
	private Long pokemon;

	public Long getRota() {
		return rota;
	}

	public void setRota(Long rota) {
		this.rota = rota;
	}

	public Long getPokemon() {
		return pokemon;
	}

	public void setPokemon(Long pokemon) {
		this.pokemon = pokemon;
	}
}
