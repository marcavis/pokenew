package br.com.marcos.poke.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Ataque extends GenericDomain{

	@Column(length=20, nullable=false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Tipo tipo;
	
	@Column(nullable=false)
	private Integer poder;
	
	@Column
	private Boolean especial;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Integer getPoder() {
		return poder;
	}

	public void setPoder(Integer poder) {
		this.poder = poder;
	}

	public Boolean getEspecial() {
		return especial;
	}

	public void setEspecial(Boolean especial) {
		this.especial = especial;
	}
	
	public String getDescricaoEspecial() {
		if (getEspecial())
			return "Especial";
		else
			return "Físico";
	}
}
