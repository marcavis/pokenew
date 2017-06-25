package br.com.marcos.poke.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Efetividade extends GenericDomain{

	@ManyToOne
	@JoinColumn(nullable=false)
	private Tipo tipo1;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Tipo tipo2;
	
	//porcentagem
	@Column
	private Integer efetividade;

	public Integer getEfetividade() {
		return efetividade;
	}

	public void setEfetividade(Integer efetividade) {
		this.efetividade = efetividade;
	}
	
	public Tipo getTipo1() {
		return tipo1;
	}

	public void setTipo1(Tipo tipo1) {
		this.tipo1 = tipo1;
	}

	public Tipo getTipo2() {
		return tipo2;
	}

	public void setTipo2(Tipo tipo2) {
		this.tipo2 = tipo2;
	}
}
