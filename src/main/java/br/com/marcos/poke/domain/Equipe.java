package br.com.marcos.poke.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Equipe extends GenericDomain{

	@ManyToOne
	@JoinColumn(nullable=false)
	private Especime pokemon1;
	
	@ManyToOne
	@JoinColumn
	private Especime pokemon2;
	
	@ManyToOne
	@JoinColumn
	private Especime pokemon3;
	
	@ManyToOne
	@JoinColumn
	private Especime pokemon4;
	
	@ManyToOne
	@JoinColumn
	private Especime pokemon5;
	
	@ManyToOne
	@JoinColumn
	private Especime pokemon6;

	public Especime getPokemon1() {
		return pokemon1;
	}

	public void setPokemon1(Especime pokemon1) {
		this.pokemon1 = pokemon1;
	}

	public Especime getPokemon2() {
		return pokemon2;
	}

	public void setPokemon2(Especime pokemon2) {
		this.pokemon2 = pokemon2;
	}

	public Especime getPokemon3() {
		return pokemon3;
	}

	public void setPokemon3(Especime pokemon3) {
		this.pokemon3 = pokemon3;
	}

	public Especime getPokemon4() {
		return pokemon4;
	}

	public void setPokemon4(Especime pokemon4) {
		this.pokemon4 = pokemon4;
	}

	public Especime getPokemon5() {
		return pokemon5;
	}

	public void setPokemon5(Especime pokemon5) {
		this.pokemon5 = pokemon5;
	}

	public Especime getPokemon6() {
		return pokemon6;
	}

	public void setPokemon6(Especime pokemon6) {
		this.pokemon6 = pokemon6;
	}
	
	public List<Especime> getPokemon() {
		ArrayList<Especime> lista = new ArrayList<Especime>();
		for(Especime p: new Especime[] {pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6}) {
			if(p != null)
				lista.add(p);
		}
		return lista;
	}
}
