package br.com.marcos.poke.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.marcos.poke.dao.CidadeDao;
import br.com.marcos.poke.dao.EstadoDao;
import br.com.marcos.poke.domain.Cidade;
import br.com.marcos.poke.domain.Estado;

//viewscoped - presente durante a presença da tela
@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class TesteBean implements Serializable{
	private Estado estadoSelecionado;
	private Cidade cidadeSelecionada;
	private List<Estado> estados;
	private List<Cidade> cidades;
	
	@PostConstruct
	public void iniciar() {
		try {
			EstadoDao dao = new EstadoDao();
			estados = dao.listarTodos();
			
			estadoSelecionado = new Estado();
			cidadeSelecionada = new Cidade();
			
			cidades = new ArrayList<Cidade>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public void popula() {
		try {
			cidades = new ArrayList<Cidade>();
			if(estadoSelecionado != null) {
				CidadeDao dao = new CidadeDao();
				cidades = dao.listarPorUf(estadoSelecionado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
