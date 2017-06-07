package br.com.marcos.poke.bean;

import java.io.File;
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

import br.com.marcos.poke.dao.EquipeDao;
import br.com.marcos.poke.domain.Equipe;
import br.com.marcos.poke.util.Settings;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class EquipeBean implements Serializable{
	private Equipe equipe;
	private List<Equipe> equipes;
	
	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivo = evento.getFile();
			Path caminho = Files.createTempFile(null, null);
			Files.copy(arquivo.getInputstream(), caminho, StandardCopyOption.REPLACE_EXISTING);
			equipe.setCaminhoImg(caminho.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
			Equipe equipeNova = dao.merge(equipe);
			Path origem = Paths.get(equipe.getCaminhoImg());
			File diretorio = new File(Settings.getCaminho() + "img/treinador/");
			if(! diretorio.exists())
				diretorio.mkdir();
			Path destino = Paths.get(Settings.getCaminho() + "img/treinador/" + equipeNova.getCodigo() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			
			Messages.addGlobalInfo("Equipe cadastrada com sucesso");
		
		} catch (NullPointerException | IOException n) {
			System.out.println("Treinador cadastrado sem imagem - " + equipe.getTreinador());
			Messages.addGlobalInfo("Equipe cadastrada com sucesso");
		}
			novo();
			listar();
	}
}
