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

//import br.com.marcos.poke.dao.CidadeDao;
//import br.com.marcos.poke.dao.EstadoDao;
import br.com.marcos.poke.dao.PokemonDao;
import br.com.marcos.poke.dao.TipoDao;
import br.com.marcos.poke.domain.Tipo;
import br.com.marcos.poke.util.Settings;
import br.com.marcos.poke.domain.Pokemon;

//viewscoped - presente durante a presença da tela
@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class PokemonBean implements Serializable{
	private Pokemon pokemon;
	private List<Pokemon> pokemons;
	private List<Tipo> tipos;
	
	public void upload(FileUploadEvent evento) {
		
		try {
			UploadedFile arquivo = evento.getFile();
			Path caminho = Files.createTempFile(null, null);
			Files.copy(arquivo.getInputstream(), caminho, StandardCopyOption.REPLACE_EXISTING);
			pokemon.setCaminhoImg(caminho.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			pokemon = (Pokemon) evento.getComponent().getAttributes().get("pokemonExcluir");
			PokemonDao dao = new PokemonDao();
			dao.excluir(pokemon);
			Path caminho = Paths.get(Settings.getCaminho() + "/img/poke/"
			+ pokemon.getCodigo() + ".png");
			Files.deleteIfExists(caminho);
			listar();
			Messages.addGlobalInfo(pokemon.getNome() + " excluído com sucesso.");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir Pokémon");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
		pokemon = (Pokemon) evento.getComponent().getAttributes().get("pokemonAlterar");
		pokemon.setCaminhoImg(Settings.getCaminho() + "img/poke/"
		+ pokemon.getCodigo() + ".png");
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
			
			PokemonDao dao = new PokemonDao();
			pokemons = dao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar Pokémons");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		pokemon = new Pokemon();
		carregaTipos();
	}
	
	public void salvar() {
		try {
			PokemonDao dao = new PokemonDao();
			Pokemon pokemonNovo = dao.merge(pokemon);
			
			Path origem = Paths.get(pokemon.getCaminhoImg());
			Path destino = Paths.get(Settings.getCaminho() + "img/poke/" + pokemonNovo.getCodigo() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			
			Messages.addGlobalInfo("Pokémon cadastrado com sucesso");
		
		} catch (NullPointerException | IOException n) {
			System.out.println("Pokemon cadastrado sem imagem - " + pokemon.getNome());
			Messages.addGlobalInfo("Pokémon cadastrado com sucesso");
		}
		novo();
		listar();
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	public List<Pokemon> getPokemons() {
		return pokemons;
	}

	public void setPokemons(List<Pokemon> pokemons) {
		this.pokemons = pokemons;
	}

	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}
	
	
}
