package br.com.marcos.poke.domain;

import java.util.ArrayList;
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
		List<Especime> especimes = dao.listarTodos();
		List<Especime> destaEquipe = new ArrayList<Especime>();
		for (Especime ep : especimes) {
			if(ep.getEquipe().getCodigo() == getCodigo())
				destaEquipe.add(ep);
		}
		System.out.println(getTreinador());
		return destaEquipe;
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
