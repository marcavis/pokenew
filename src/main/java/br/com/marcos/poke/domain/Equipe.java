package br.com.marcos.poke.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Equipe extends GenericDomain{

	@ManyToOne
	@JoinColumn(nullable=false)
	private Pokemon pokemon1;
	
	@ManyToOne
	@JoinColumn
	private Pokemon pokemon2;
	
	@ManyToOne
	@JoinColumn
	private Pokemon pokemon3;

	public Pokemon getPokemon1() {
		return pokemon1;
	}

	public void setPokemon1(Pokemon pokemon1) {
		this.pokemon1 = pokemon1;
	}

	public Pokemon getPokemon2() {
		return pokemon2;
	}

	public void setPokemon2(Pokemon pokemon2) {
		this.pokemon2 = pokemon2;
	}

	public Pokemon getPokemon3() {
		return pokemon3;
	}

	public void setPokemon3(Pokemon pokemon3) {
		this.pokemon3 = pokemon3;
	}
}
