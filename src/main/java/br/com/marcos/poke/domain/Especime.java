package br.com.marcos.poke.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Especime extends GenericDomain{
	@ManyToOne
	@JoinColumn(nullable=false)
	private Pokemon pokemon;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Equipe equipe;
	
	@Column(length=20)
	private String apelido;
	
	public Pokemon getPokemon() {
		return pokemon;
	}
	
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	
	public String getApelidoOuNome() {
		if(getApelido().length() == 0)
			return pokemon.getNome();
		else
			return getApelido();
	}
	
	public String getApelido() {
		return apelido;
	}
	
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
}
