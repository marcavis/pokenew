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
			dao.excluir(rotaPokemon);
			listar();
			Messages.addGlobalInfo(rotaPokemon.getRota().getNome() + " não contém mais o pokémon "
					+ rotaPokemon.getPokemon().getNome());
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
		rotaPokemon.setRota(rota);
	}

	public RotaPokemon getRotaPokemon() {
		return rotaPokemon;
	}

	public List<RotaPokemon> getLocalizacoes() {
		listar();
		return localizacoes;
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
	
	public List<Pokemon> getPokemonsDaRota(ActionEvent evento) {
		PokemonDao pdao = new PokemonDao();
		System.out.println("arg");
		Long codRotaFiltro = (Long) evento.getComponent().getAttributes().get("rotaAtual");
		pokemonsDaRota = pdao.listarTodos();
		RotaPokemonDao rpdao = new RotaPokemonDao();
		List<RotaPokemon> localizacoesDaRota = rpdao.listarTodos("rota", codRotaFiltro);
		for (RotaPokemon rotaPokemon : localizacoesDaRota) {
			System.out.println(rotaPokemon);
		}
		return pokemonsDaRota;
	}

	public List<Pokemon> getPokemonsDaRota() {
		System.out.println("NOarg");
		PokemonDao pdao = new PokemonDao();
		pokemonsDaRota = pdao.listarTodos();
		return pokemonsDaRota;
	}

	public void setPokemonsDaRota(List<Pokemon> pokemonsDaRota) {
		this.pokemonsDaRota = pokemonsDaRota;
	}

	public void salvar() {
		try {
			RotaPokemonDao dao = new RotaPokemonDao();
			for (RotaPokemon rp : localizacoes) {
				if(rotaPokemon.getPokemon().getCodigo() == rp.getPokemon().getCodigo() &&
						rotaPokemon.getRota().getCodigo() == rp.getRota().getCodigo()) {
					Messages.addGlobalError("Erro: Pokémon já foi incluído nesta rota!");
					return;
				}
			}
			dao.merge(rotaPokemon);
			Messages.addGlobalInfo(rotaPokemon.getPokemon().getNome() + " incluído na rota "
					+ rotaPokemon.getRota().getNome());
			//novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao incluir Pokémon na Rota");
			e.printStackTrace();
		}
	}
}
