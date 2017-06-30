package br.com.marcos.poke.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.marcos.poke.dao.RotaDao;
import br.com.marcos.poke.dao.RotaPokemonDao;
import br.com.marcos.poke.domain.Rota;
import br.com.marcos.poke.domain.RotaPokemon;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class RotaBean implements Serializable{
	private Rota rota;
	private List<Rota> rotas;
	private RotaPokemon rotaPokemon;
	
	public void excluir(ActionEvent evento) {
		try {
			rota = (Rota) evento.getComponent().getAttributes().get("rotaExcluir");
			excluirLocalizacoes();
			RotaDao dao = new RotaDao();
			dao.excluir(rota);
			listar();
			Messages.addGlobalInfo("Rota " + rota.getNome() + " excluída com sucesso.");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir Rota");
			e.printStackTrace();
		}
	}
	
	public void excluirLocalizacoes() {
		try {
			RotaPokemonDao dao = new RotaPokemonDao();
			List<RotaPokemon> localizacoes = dao.listarTodos("rota.codigo", getRota().getCodigo());
			for (RotaPokemon rp : localizacoes) {
				dao.excluir(rp);
			}
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir pokémons cadastrados na rota");
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
