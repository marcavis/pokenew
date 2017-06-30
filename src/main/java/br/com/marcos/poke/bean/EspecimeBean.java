package br.com.marcos.poke.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.marcos.poke.dao.AtaqueEnsinadoDao;
import br.com.marcos.poke.dao.EquipeDao;
import br.com.marcos.poke.dao.EspecimeDao;
import br.com.marcos.poke.domain.AtaqueEnsinado;
import br.com.marcos.poke.domain.Equipe;
import br.com.marcos.poke.domain.Especime;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class EspecimeBean implements Serializable{
	private Especime especime;
	private List<Especime> especimes;
	private Equipe equipeSelecionada;
	private Especime especimeSelecionado;
	
	public void excluir(ActionEvent evento) {
		try {
			especime = (Especime) evento.getComponent().getAttributes().get("especimeExcluir");
			EspecimeDao dao = new EspecimeDao();
			excluirAtaquesEnsinados();
			dao.excluir(especime);
			listar();
			Messages.addGlobalInfo(especime.getApelidoOuNome() + " excluído da equipe.");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir " + especime.getApelido());
			e.printStackTrace();
		}
	}
	
	public void excluirAtaquesEnsinados() {
		try {
			AtaqueEnsinadoDao dao = new AtaqueEnsinadoDao();
			List<AtaqueEnsinado> ensinados = dao.listarTodos("especime.codigo", getEspecime().getCodigo());
			for (AtaqueEnsinado atq : ensinados) {
				dao.excluir(atq);
			}
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir ataques aprendidos por este Pokémon");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
		especime = (Especime) evento.getComponent().getAttributes().get("especimeAlterar");
	}
	
	@PostConstruct
	public void listar() {
		try {
			
			EspecimeDao dao = new EspecimeDao();
			especimes = dao.listarTodos();
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar Pokémons da equipe");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		especime = new Especime();
		especime.setEquipe(getEquipeSelecionada());
	}

	public Especime getEspecime() {
		return especime;
	}

	public void setEspecime(Especime especime) {
		this.especime = especime;
	}
	
	public Especime getEspecimeSelecionado() {
		return especimeSelecionado;
	}

	public void setEspecimeSelecionado(Especime especimeSelecionado) {
		this.especimeSelecionado = especimeSelecionado;
	}

	public List<Especime> getEspecimes() {
		return especimes;
	}

	public void setEspecimes(List<Especime> especimes) {
		this.especimes = especimes;
	}

	public void salvar() {
		try {
			EspecimeDao dao = new EspecimeDao();
			if(especime.getEquipe() == null) {
				Messages.addGlobalError("Pokémon deve pertencer a uma equipe. Cadastre uma equipe primeiro.");
				return;
			}
			if(dao.listarTodos("equipe.codigo", especime.getEquipe().getCodigo()).size() >= 6) {
				Messages.addGlobalError("Equipe já possui o número máximo de Pokémons");
				return;
			}
			dao.merge(especime);
			Messages.addGlobalInfo(especime.getApelidoOuNome() + " adicionado(a) à equipe");
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar Pokémon na equipe");
			e.printStackTrace();
		}
	}

	public Equipe getEquipeSelecionada() {
		if(equipeSelecionada == null) {
			EquipeDao dao = new EquipeDao();
			List<Equipe> equipes = dao.listarTodos();
			if(equipes.isEmpty())
				return null;
			else
				return dao.listarTodos().get(0);
		}
		return equipeSelecionada;
	}

	public void setEquipeSelecionada(Equipe equipeSelecionada) {
		this.equipeSelecionada = equipeSelecionada;
	}
}
