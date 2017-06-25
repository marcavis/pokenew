package br.com.marcos.poke.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.marcos.poke.dao.AtaqueEnsinadoDao;
import br.com.marcos.poke.domain.AtaqueEnsinado;
import br.com.marcos.poke.domain.Especime;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class AtaqueEnsinadoBean implements Serializable{
	private AtaqueEnsinado ataqueEnsinado;
	private List<AtaqueEnsinado> ataquesEnsinados;
	private Especime especimeSelecionado;
	
	public void excluir(ActionEvent evento) {
		try {
			ataqueEnsinado = (AtaqueEnsinado) evento.getComponent().getAttributes().get("ataqueEnsinadoExcluir");
			AtaqueEnsinadoDao dao = new AtaqueEnsinadoDao();
			dao.excluir(ataqueEnsinado);
			listar();
			Messages.addGlobalInfo("Ataque esquecido com sucesso.");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir ataque");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
		ataqueEnsinado = (AtaqueEnsinado) evento.getComponent().getAttributes().get("ataqueEnsinadoAlterar");
	}
	
	@PostConstruct
	public void listar() {
		try {
			
			AtaqueEnsinadoDao dao = new AtaqueEnsinadoDao();
			ataquesEnsinados = dao.listarTodos();
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar Ataques ensinados");
			e.printStackTrace();
		}
	}
	
	public void novo(ActionEvent evento) {
		ataqueEnsinado = new AtaqueEnsinado();
		Especime especimeAtual = (Especime)evento.getComponent().getAttributes().get("especimeAtual");
		ataqueEnsinado.setEspecime(especimeAtual);
	}
	
	public void novo() {
		ataqueEnsinado = new AtaqueEnsinado();
	}

	public AtaqueEnsinado getAtaqueEnsinado() {
		return ataqueEnsinado;
	}

	public void setAtaqueEnsinado(AtaqueEnsinado ataqueEnsinado) {
		this.ataqueEnsinado = ataqueEnsinado;
	}

	public List<AtaqueEnsinado> getAtaquesEnsinados() {
		return ataquesEnsinados;
	}

	public void setAtaquesEnsinados(List<AtaqueEnsinado> ataquesEnsinados) {
		this.ataquesEnsinados = ataquesEnsinados;
	}

	public Especime getEspecimeSelecionado() {
		return especimeSelecionado;
	}

	public void setEspecimeSelecionado(Especime especimeSelecionado) {
		this.especimeSelecionado = especimeSelecionado;
	}

	public void salvar() {
		try {
			AtaqueEnsinadoDao dao = new AtaqueEnsinadoDao();
			for (AtaqueEnsinado atq : ataquesEnsinados) {
				if(ataqueEnsinado.getAtaque().getCodigo() == atq.getAtaque().getCodigo() &&
						ataqueEnsinado.getEspecime().getCodigo() == atq.getEspecime().getCodigo()) {
					Messages.addGlobalError("Erro: Pokémon já aprendeu esse ataque!");
					return;
				}
				if(dao.listarTodos("especime.codigo", ataqueEnsinado.getEspecime().getCodigo()).size() >= 4) {
					Messages.addGlobalError("Pokémon já aprendeu o máximo de 4 ataques!");
					return;
				}
			}
			dao.merge(ataqueEnsinado);
			Messages.addGlobalInfo("Ataque ensinado a " + ataqueEnsinado.getEspecime().getApelidoOuNome() + " com sucesso.");
			//novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao ensinar Ataque");
			e.printStackTrace();
		}
	}
}
