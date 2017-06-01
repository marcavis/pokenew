package br.com.marcos.poke.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Rota extends GenericDomain{

	@Column(length=20, nullable=false)
	private String nome;
	
	//@OneToMany(mappedBy = "rota", targetEntity = RotaPokemon.class, cascade = CascadeType.ALL)
	//private List<RotaPokemon> pokemonsDaRota;

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

//	public List<RotaPokemon> getPokemonsDaRota() {
//		return pokemonsDaRota;
//	}
//
//	public void setPokemonsDaRota(List<RotaPokemon> pokemonsDaRota) {
//		this.pokemonsDaRota = pokemonsDaRota;
//	}

	@Override
	public String toString() {
		return "Rota [nome=" + nome + "]";
	}
}
