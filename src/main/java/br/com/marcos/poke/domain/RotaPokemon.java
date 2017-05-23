package br.com.marcos.poke.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import br.com.marcos.poke.dao.PokemonDao;
import br.com.marcos.poke.dao.RotaDao;

@SuppressWarnings("serial")
@Entity
public class RotaPokemon extends GenericDomain{

	@JoinColumn(nullable=false)
	private Long rota;
	
	@JoinColumn(nullable=false)
	private Long pokemon;
	
	@Transient
	private String nomeDaRota;

	@Transient
	private String nomeDoPokemon;
	
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
	
	public String getNomeDaRota() {
		RotaDao rdao = new RotaDao();
		return rdao.buscar(rota).getNome();
	}
	
	public void setNomeDaRota(String nome) {
		nomeDaRota = nome;
	}
	
	public String getNomeDoPokemon() {
		PokemonDao pdao = new PokemonDao();
		return pdao.buscar(pokemon).getNome();
	}
	
	public void setNomeDoPokemon(String nome) {
		nomeDoPokemon = nome;
	}
}
