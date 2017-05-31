package br.com.marcos.poke.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import br.com.marcos.poke.dao.RotaPokemonDao;

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
	
	public List<RotaPokemon> getLocalizacoesDestaRota() {
		RotaPokemonDao dao = new RotaPokemonDao();
		List<RotaPokemon> localizacoes = dao.listarTodos();
		ArrayList<RotaPokemon> resultado = new ArrayList<RotaPokemon>();
		for (RotaPokemon rp : localizacoes) {
			if(rp.getRota().getCodigo() == getRota().getCodigo())
				resultado.add(rp);
		}
		return resultado;
	}
}
