package br.com.marcos.poke.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.marcos.poke.dao.TipoDao;
import br.com.marcos.poke.domain.Tipo;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class TipoBean implements Serializable{
	private Tipo tipo;
	private List<Tipo> tipos;
	
	public void excluir(ActionEvent evento) {
		try {
			tipo = (Tipo) evento.getComponent().getAttributes().get("tipoExcluir");
			TipoDao dao = new TipoDao();
			dao.excluir(tipo);
			listar();
			Messages.addGlobalInfo("Tipo " + tipo.getNome() + " excluído com sucesso.");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir Tipo");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
		tipo = (Tipo) evento.getComponent().getAttributes().get("tipoAlterar");
	}
	
	@PostConstruct
	public void listar() {
		try {
			
			TipoDao dao = new TipoDao();
			tipos = dao.listarTodos();
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar Tipos");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		tipo = new Tipo();
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	public void salvar() {
		try {
			TipoDao dao = new TipoDao();
			dao.merge(tipo);
			Messages.addGlobalInfo("Tipo cadastrado com sucesso");
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar Tipo");
			e.printStackTrace();
		}
	}
}
