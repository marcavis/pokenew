package br.com.marcos.poke.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import br.com.marcos.poke.dao.RotaPokemonDao;

@SuppressWarnings("serial")
@Entity
public class Rota extends GenericDomain{

	@Column(length=20, nullable=false)
	private String nome;
	
	@Transient
	private Integer contagem;

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Rota [nome=" + nome + "]";
	}
	
	public Integer getContagem() {
		RotaPokemonDao dao = new RotaPokemonDao();
		List<RotaPokemon> localizacoes = dao.listarTodos();
		Integer resultado = 0;
		for (RotaPokemon rp : localizacoes) {
			if(rp.getRota().getCodigo() == getCodigo())
				resultado++;
		}
		return resultado;
	}
}
