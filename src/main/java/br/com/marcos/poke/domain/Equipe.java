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

	public Especime getEspecime1() {
		return pokemon1;
	}

	public void setEspecime1(Especime pokemon1) {
		this.pokemon1 = pokemon1;
	}

	public Especime getEspecime2() {
		return pokemon2;
	}

	public void setEspecime2(Especime pokemon2) {
		this.pokemon2 = pokemon2;
	}

	public Especime getEspecime3() {
		return pokemon3;
	}

	public void setEspecime3(Especime pokemon3) {
		this.pokemon3 = pokemon3;
	}

	public Especime getEspecime4() {
		return pokemon4;
	}

	public void setEspecime4(Especime pokemon4) {
		this.pokemon4 = pokemon4;
	}

	public Especime getEspecime5() {
		return pokemon5;
	}

	public void setEspecime5(Especime pokemon5) {
		this.pokemon5 = pokemon5;
	}

	public Especime getEspecime6() {
		return pokemon6;
	}

	public void setEspecime6(Especime pokemon6) {
		this.pokemon6 = pokemon6;
	}
	
	public List<Especime> getEspecimes() {
		ArrayList<Especime> lista = new ArrayList<Especime>();
		for(Especime p: new Especime[] {pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6}) {
			if(p != null)
				lista.add(p);
		}
		return lista;
	}
}
