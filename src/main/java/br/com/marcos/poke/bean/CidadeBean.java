package br.com.marcos.poke.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.marcos.poke.dao.CidadeDao;
import br.com.marcos.poke.dao.EstadoDao;
import br.com.marcos.poke.domain.Cidade;
import br.com.marcos.poke.domain.Estado;
import br.com.marcos.poke.util.Settings;

//viewscoped - presente durante a presença da tela
@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class CidadeBean implements Serializable{
	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;
	
	public void upload(FileUploadEvent evento) {
		
		try {
			UploadedFile arquivo = evento.getFile();
			Path caminho = Files.createTempFile(null, null);
			Files.copy(arquivo.getInputstream(), caminho, StandardCopyOption.REPLACE_EXISTING);
			cidade.setCaminhoImg(caminho.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeExcluir");
			CidadeDao dao = new CidadeDao();
			dao.excluir(cidade);
			Path caminho = Paths.get(Settings.getCaminho() + "img/" + cidade.getCodigo() + ".png");
			Files.deleteIfExists(caminho);
			
			listar();
			Messages.addGlobalInfo(cidade.getNome() + " excluída com sucesso.");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir cidade");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
		cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeAlterar");
		cidade.setCaminhoImg(Settings.getCaminho() + "img/" + cidade.getCodigo() + ".png");
		carregaEstados();
	}
	
	private void carregaEstados() {
		try {
			EstadoDao dao = new EstadoDao();
			estados = dao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao carregar estados.");
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar() {
		try {
			
			CidadeDao dao = new CidadeDao();
			cidades = dao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar cidades");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		cidade = new Cidade();
		carregaEstados();
	}
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void salvar() {
		try {
			CidadeDao dao = new CidadeDao();
			Cidade cidadeNova = dao.merge(cidade);
			
			Path origem = Paths.get(cidade.getCaminhoImg());
			Path destino = Paths.get(Settings.getCaminho() + "img/" + cidadeNova.getCodigo() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			
			Messages.addGlobalInfo("Cidade cadastrada com sucesso");
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar cidade");
			e.printStackTrace();
		}
	}
}
