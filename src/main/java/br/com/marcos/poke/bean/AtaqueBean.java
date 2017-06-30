package br.com.marcos.poke.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.marcos.poke.dao.AtaqueDao;
import br.com.marcos.poke.dao.AtaqueEnsinadoDao;
import br.com.marcos.poke.domain.Ataque;
import br.com.marcos.poke.domain.AtaqueEnsinado;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class AtaqueBean implements Serializable{
	private Ataque ataque;
	private List<Ataque> ataques;
	
	public void excluir(ActionEvent evento) {
		try {
			ataque = (Ataque) evento.getComponent().getAttributes().get("ataqueExcluir");
			AtaqueDao dao = new AtaqueDao();
			excluirAtaquesEnsinados();
			dao.excluir(ataque);
			listar();
			Messages.addGlobalInfo("Ataque " + ataque.getNome() + " excluído com sucesso.");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir ataque");
			e.printStackTrace();
		}
	}
	
	public void excluirAtaquesEnsinados() {
		try {
			AtaqueEnsinadoDao dao = new AtaqueEnsinadoDao();
			List<AtaqueEnsinado> ensinados = dao.listarTodos("ataque.codigo", getAtaque().getCodigo());
			for (AtaqueEnsinado atq : ensinados) {
				dao.excluir(atq);
			}
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir cadastramentos deste ataque em pokémons");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
		ataque = (Ataque) evento.getComponent().getAttributes().get("ataqueAlterar");
	}
	
	@PostConstruct
	public void listar() {
		try {
			
			AtaqueDao dao = new AtaqueDao();
			ataques = dao.listarTodos();
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar Ataques");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		ataque = new Ataque();
	}

	public Ataque getAtaque() {
		return ataque;
	}

	public void setAtaque(Ataque ataque) {
		this.ataque = ataque;
	}

	public List<Ataque> getAtaques() {
		listar();
		return ataques;
	}

	public void setAtaques(List<Ataque> ataques) {
		this.ataques = ataques;
	}

	public void salvar() {
		try {
			AtaqueDao dao = new AtaqueDao();
			dao.merge(ataque);
			Messages.addGlobalInfo("Ataque cadastrado com sucesso");
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar Ataque");
			e.printStackTrace();
		}
	}
}
