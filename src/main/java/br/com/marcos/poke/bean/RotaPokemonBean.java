package br.com.marcos.poke.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.marcos.poke.dao.PokemonDao;
import br.com.marcos.poke.dao.RotaDao;
import br.com.marcos.poke.dao.RotaPokemonDao;
import br.com.marcos.poke.domain.Pokemon;
import br.com.marcos.poke.domain.Rota;
import br.com.marcos.poke.domain.RotaPokemon;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class RotaPokemonBean implements Serializable{
	private RotaPokemon rotaPokemon;
	private List<RotaPokemon> localizacoes;
	private List<Pokemon> pokemonsDaRota;
	
	private Rota rota;
	private String codigoRota;
	
	public void excluir(ActionEvent evento) {
		try {
			rotaPokemon = (RotaPokemon) evento.getComponent().getAttributes().get("rotaPokemonExcluir");
			RotaPokemonDao dao = new RotaPokemonDao();
			RotaDao rdao = new RotaDao();
			PokemonDao pdao = new PokemonDao();
			dao.excluir(rotaPokemon);
			listar();
			Messages.addGlobalInfo(rdao.buscar(rotaPokemon.getRota()).getNome() + " não contém mais o pokémon "
					+ pdao.buscar(rotaPokemon.getPokemon()).getNome());
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao desassociar Rota com Pokémon");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
		rotaPokemon = (RotaPokemon) evento.getComponent().getAttributes().get("rotaPokemonAlterar");
	}

	@PostConstruct
	public void listar() {
		try {
			
			RotaPokemonDao dao = new RotaPokemonDao();
			setLocalizacoes(dao.listarTodos());
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar localizações de Pokémons");
			e.printStackTrace();
		}
	}
	
	public void novo(ActionEvent evento) {
		rotaPokemon = new RotaPokemon();
		rota = (Rota)evento.getComponent().getAttributes().get("rotaAtual");
		rotaPokemon.setRota(rota.getCodigo());
	}

	public RotaPokemon getRotaPokemon() {
		return rotaPokemon;
	}


	public void setRota(RotaPokemon rotaPokemon) {
		this.rotaPokemon = rotaPokemon;
	}

	public List<RotaPokemon> getLocalizacoes() {
		listar();
		return localizacoes;
	}
	
	public List<Pokemon> getPokemonsDaRota(ActionEvent evento) {
		PokemonDao pdao = new PokemonDao();
		Long codRotaFiltro = (Long) evento.getComponent().getAttributes().get("rotaAtual");
		return pdao.listarTodos();
	}

	public List<Pokemon> getPokemonsDaRota() {
		PokemonDao pdao = new PokemonDao();
		return pdao.listarTodos();
	}

	public void setPokemonsDaRota(List<Pokemon> pokemonsDaRota) {
		this.pokemonsDaRota = pokemonsDaRota;
	}
	
	public void setLocalizacoes(List<RotaPokemon> localizacoes) {
		this.localizacoes = localizacoes;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public String getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	public void salvar() {
		try {
			RotaPokemonDao dao = new RotaPokemonDao();
			for (RotaPokemon rp : localizacoes) {
				if(rotaPokemon.getPokemon() == rp.getPokemon() &&
						rotaPokemon.getRota() == rp.getRota()) {
					Messages.addGlobalError("Erro: Pokémon já foi incluído nesta rota!");
					return;
				}
			}
			dao.merge(rotaPokemon);
			RotaDao rdao = new RotaDao();
			PokemonDao pdao = new PokemonDao();
			Messages.addGlobalInfo(pdao.buscar(rotaPokemon.getPokemon()).getNome() + " incluído na rota "
					+ rdao.buscar(rotaPokemon.getRota()).getNome());
			//novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao incluir Pokémon na Rota");
			e.printStackTrace();
		}
	}
}
