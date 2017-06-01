package br.com.marcos.poke.domain;

import java.util.List;

import javax.persistence.Entity;

import br.com.marcos.poke.dao.EspecimeDao;

@SuppressWarnings("serial")
@Entity
public class Equipe extends GenericDomain{
	
	public List<Especime> getPokemons() {
		EspecimeDao dao = new EspecimeDao();
		for (Especime eps : dao.listarTodos()) {
			System.out.println(eps.getPokemon().getNome());
		}
		return dao.listarTodos();
	}
}
