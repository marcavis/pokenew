package br.com.marcos.poke.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.marcos.poke.dao.EquipeDao;
import br.com.marcos.poke.domain.Equipe;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class EquipeBean implements Serializable{
	private Equipe equipe;
	private List<Equipe> equipes;
	
	public void excluir(ActionEvent evento) {
		try {
			equipe = (Equipe) evento.getComponent().getAttributes().get("equipeExcluir");
			EquipeDao dao = new EquipeDao();
			dao.excluir(equipe);
			listar();
			Messages.addGlobalInfo("Equipe excluída com sucesso.");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir Equipe");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
		equipe = (Equipe) evento.getComponent().getAttributes().get("equipeAlterar");
	}
	
	@PostConstruct
	public void listar() {
		try {
			
			EquipeDao dao = new EquipeDao();
			equipes = dao.listarTodos();
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar Equipes");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		equipe = new Equipe();
	}
	
	public void adicionar() {
		listar();
		if (getEquipes().size() >= 1) {
			equipe = getEquipes().get(0);
		} else {
			novo();
		}
		salvar();
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public void salvar() {
		try {
			EquipeDao dao = new EquipeDao();
			dao.merge(equipe);
			Messages.addGlobalInfo("Equipe cadastrada com sucesso");
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar Equipe");
			e.printStackTrace();
		}
	}
}
