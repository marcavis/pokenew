package br.com.marcos.poke.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.marcos.poke.dao.EstadoDao;
import br.com.marcos.poke.domain.Estado;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class EstadoBean implements Serializable{
	private Estado estado;
	private List<Estado> estados;
	
	public void excluir(ActionEvent evento) {
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("ufExcluir");
			EstadoDao dao = new EstadoDao();
			dao.excluir(estado);
			listar();
			Messages.addGlobalInfo(estado.getNome() + "-" + estado.getSigla());
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir UF");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
		estado = (Estado) evento.getComponent().getAttributes().get("ufAlterar");
	}
	
	@PostConstruct
	public void listar() {
		try {
			
			EstadoDao dao = new EstadoDao();
			estados = dao.listarTodos();
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar UFs");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		estado = new Estado();
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void salvar() {
		try {
			EstadoDao dao = new EstadoDao();
			dao.merge(estado);
			Messages.addGlobalInfo("UF cadastrada com sucesso");
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar UF");
			e.printStackTrace();
		}
//		FacesMessage fMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvar", null);
//		FacesContext contexto = FacesContext.getCurrentInstance();
//		contexto.addMessage(null, fMsg);
	}
}
