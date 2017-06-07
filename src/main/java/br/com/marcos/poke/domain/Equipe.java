package br.com.marcos.poke.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import br.com.marcos.poke.dao.EspecimeDao;

@SuppressWarnings("serial")
@Entity
public class Equipe extends GenericDomain{
	
	@Column(length=20, nullable=false)
	private String treinador;
	
	@Transient
	private String caminhoImg;
	
	public List<Especime> getPokemons() {
		EspecimeDao dao = new EspecimeDao();
		for (Especime eps : dao.listarTodos()) {
			System.out.println(eps.getPokemon().getNome());
		}
		return dao.listarTodos();
	}

	public String getTreinador() {
		return treinador;
	}

	public void setTreinador(String treinador) {
		this.treinador = treinador;
	}

	public String getCaminhoImg() {
		return caminhoImg;
	}

	public void setCaminhoImg(String caminhoImg) {
		this.caminhoImg = caminhoImg;
	}
}
