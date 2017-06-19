package br.com.marcos.poke.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Ataque extends GenericDomain{

	@Column(length=20, nullable=false)
	private String nome;
	
	//private Tipo tipo;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
