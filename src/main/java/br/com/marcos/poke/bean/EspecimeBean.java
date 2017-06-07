package br.com.marcos.poke.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.marcos.poke.dao.EspecimeDao;
import br.com.marcos.poke.domain.Equipe;
import br.com.marcos.poke.domain.Especime;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class EspecimeBean implements Serializable{
	private Especime especime;
	private List<Especime> especimes;
	private Equipe equipeSelecionada;
	
	public void excluir(ActionEvent evento) {
		try {
			especime = (Especime) evento.getComponent().getAttributes().get("especimeExcluir");
			EspecimeDao dao = new EspecimeDao();
			dao.excluir(especime);
			listar();
			Messages.addGlobalInfo(especime.getApelido() + " excluído da equipe.");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir " + especime.getApelido());
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
	}

	public Especime getEspecime() {
		return especime;
	}

	public void setEspecime(Especime especime) {
		this.especime = especime;
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
			if(dao.listarTodos().size() >= 6) {
				Messages.addGlobalError("Equipe já possui o número máximo de Pokémons");
				return;
			}
			dao.merge(especime);
			Messages.addGlobalInfo(especime.getApelido() + " adicionado(a) à equipe");
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar Pokémon na equipe");
			e.printStackTrace();
		}
	}

	public Equipe getEquipeSelecionada() {
		System.out.println("fjasiofjas");
		return equipeSelecionada;
	}

	public void setEquipeSelecionada(Equipe equipeSelecionada) {
		System.out.println("fj3333asiofjas");
		this.equipeSelecionada = equipeSelecionada;
	}
}
