package br.com.marcos.poke.domain;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Especime extends GenericDomain{
	@ManyToOne
	@JoinColumn(nullable=false)
	private Pokemon pokemon;
	
	@Column(length=20, nullable=false)
	private String apelido;
	
	public Pokemon getPokemon() {
		return pokemon;
	}
	
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	
	public String getApelido() {
		if(apelido != null)
			return apelido;
		else
			return pokemon.getNome();
	}
	
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
}
