package br.com.marcos.poke.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.marcos.poke.dao.PokemonDao;
import br.com.marcos.poke.dao.RotaDao;
import br.com.marcos.poke.domain.Pokemon;
import br.com.marcos.poke.domain.Rota;
import br.com.marcos.poke.domain.RotaPokemon;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class RotaBean implements Serializable{
	private Rota rota;
	private List<Rota> rotas;
	private Pokemon pokemonDaRota;
	private RotaPokemon rotaPokemon;
	
	public void excluir(ActionEvent evento) {
		try {
			rota = (Rota) evento.getComponent().getAttributes().get("rotaExcluir");
			RotaDao dao = new RotaDao();
			dao.excluir(rota);
			listar();
			Messages.addGlobalInfo("Rota " + rota.getNome() + " excluída com sucesso.");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir Rota");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
		rota = (Rota) evento.getComponent().getAttributes().get("rotaAlterar");
	}
	
	public void pegaRota(ActionEvent evento) {
		rota = (Rota) evento.getComponent().getAttributes().get("pokeCadastrar");
	}
	
	@PostConstruct
	public void listar() {
		try {
			
			RotaDao dao = new RotaDao();
			rotas = dao.listarTodos();
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar Rotas");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		rota = new Rota();
	}
	
	public void novoPokemon() {
		rotaPokemon = new RotaPokemon();
	}

	public Rota getRota() {
		return rota;
	}

	public List<Rota> getRotas() {
		return rotas;
	}

	public void setRotas(List<Rota> rotas) {
		this.rotas = rotas;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

//	public void addPokemon() {
//		pokemonsDaRota.add(pokemonDaRota);
//	}

	public Pokemon getPokemonDaRota() {
		return pokemonDaRota;
	}

	public void setPokemonDaRota(Pokemon pokemonDaRota) {
		this.pokemonDaRota = pokemonDaRota;
	}

	public RotaPokemon getRotaPokemon() {
		return rotaPokemon;
	}

	public void setRotaPokemon(RotaPokemon rotaPokemon) {
		this.rotaPokemon = rotaPokemon;
	}

	public void salvar() {
		try {
			RotaDao dao = new RotaDao();
			dao.merge(rota);
			Messages.addGlobalInfo("Rota cadastrada com sucesso");
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar Rota");
			e.printStackTrace();
		}
	}
}
