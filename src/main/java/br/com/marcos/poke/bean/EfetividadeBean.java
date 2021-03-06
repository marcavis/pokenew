package br.com.marcos.poke.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.marcos.poke.dao.EfetividadeDao;
import br.com.marcos.poke.dao.TipoDao;
import br.com.marcos.poke.domain.Efetividade;
import br.com.marcos.poke.domain.Tipo;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class EfetividadeBean implements Serializable{
	private Efetividade efetividade;
	private List<Efetividade> efetividades;
	private List<Tipo> tipos;
	
	public void excluir(ActionEvent evento) {
		try {
			efetividade = (Efetividade) evento.getComponent().getAttributes().get("efetividadeExcluir");
			EfetividadeDao dao = new EfetividadeDao();
			dao.excluir(efetividade);
			listar();
			//TODO: listar o nome dos tipos, em vez disso
			//Messages.addGlobalInfo(efetividade.getNome() + "-" + efetividade.getSigla());
			Messages.addGlobalInfo("efetividade");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir Efetividade");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
		efetividade = (Efetividade) evento.getComponent().getAttributes().get("efetividadeAlterar");
		carregaTipos();
	}
	
	private void carregaTipos() {
		try {
			TipoDao dao = new TipoDao();
			tipos = dao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao carregar tipos.");
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar() {
		try {
			
			EfetividadeDao dao = new EfetividadeDao();
			efetividades = dao.listarTodos();
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar Efetividades");
			e.printStackTrace();
		}
	}
	
	public void novo(ActionEvent evento) {
		efetividade = new Efetividade();
		Tipo linhaAtual = (Tipo)evento.getComponent().getAttributes().get("linhaAtual");
		Tipo colunaAtual = (Tipo)evento.getComponent().getAttributes().get("colunaAtual");
		efetividade.setTipo1(linhaAtual);
		efetividade.setTipo2(colunaAtual);
		carregaTipos();
	}
	
	public void novo() {
		efetividade = new Efetividade();
		carregaTipos();
	}
	
	public Efetividade getEfetividade() {
		return efetividade;
	}
	
	public Efetividade getEfetividade(Tipo tipo1, Tipo tipo2) {
		for (Efetividade ef : efetividades) {
			if(ef.getTipo1().getCodigo() == tipo1.getCodigo() &&
					ef.getTipo2().getCodigo() == tipo2.getCodigo())
				return ef;
		}
		Efetividade vazia = new Efetividade();
		vazia.setTipo1(tipo1);
		vazia.setTipo2(tipo2);
		vazia.setEfetividade(100);
		return vazia;
	}
	
	public String getCorDaEfetividade(Tipo tipo1, Tipo tipo2) {
		Integer valor = getEfetividade(tipo1, tipo2).getEfetividade();
		String cor;
		if(valor == 0)
			cor = "1f1f1f";
		else if(valor == 50)
			cor = "7f1f1f";
		else if(valor == 100)
			cor = "3f7f3f";
		else
			cor = "3f3fff";
		return "color:#" + cor + ";";
	}

	public void setEfetividade(Efetividade efetividade) {
		this.efetividade = efetividade;
	}

	public List<Efetividade> getEfetividades() {
		return efetividades;
	}

	public void setEfetividades(List<Efetividade> efetividades) {
		this.efetividades = efetividades;
	}

	
	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	public void salvar() {
		try {
			EfetividadeDao dao = new EfetividadeDao();
			for (Efetividade ef : efetividades) {
				if(ef.getCodigo() != efetividade.getCodigo() && 
						ef.getTipo1().getNome().equals(efetividade.getTipo1().getNome()) &&
						ef.getTipo2().getNome().equals(efetividade.getTipo2().getNome())) {
					//substituir a efetividade antiga pela que estamos salvando agora
					efetividade.setCodigo(ef.getCodigo());
				}
			}
			dao.merge(efetividade);
			Messages.addGlobalInfo("Efetividade cadastrada com sucesso");
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar Efetividade");
			e.printStackTrace();
		}
	}
}
