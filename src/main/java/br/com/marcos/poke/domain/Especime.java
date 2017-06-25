package br.com.marcos.poke.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import br.com.marcos.poke.dao.AtaqueEnsinadoDao;

@SuppressWarnings("serial")
@Entity
public class Especime extends GenericDomain{
	@ManyToOne
	@JoinColumn(nullable=false)
	private Pokemon pokemon;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Equipe equipe;
	
	@Column(length=20)
	private String apelido;
	
	@Transient
	private Integer nivel;
	
	@Transient
	private Integer vida;
	@Transient
	private Integer ataque;
	@Transient
	private Integer defesa;
	@Transient
	private Integer ataque_e;
	@Transient
	private Integer defesa_e;
	@Transient
	private Integer velocidade;
	
	public Pokemon getPokemon() {
		return pokemon;
	}
	
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	
	public String getApelidoOuNome() {
		if(getApelido().length() == 0)
			return pokemon.getNome();
		else
			return getApelido();
	}
	
	public String getNomeOuApelidoENome() {
		if(getApelido().length() == 0)
			return pokemon.getNome();
		else
			return getApelido() + " (" + pokemon.getNome() + ")";
	}
	
	public String getApelido() {
		return apelido;
	}
	
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Integer getNivel() {
		return 30;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getVida() {
		Double base = (double) getPokemon().getVida();
		return (int) Math.floor(2 * base * getNivel()/100) + getNivel() + 10;
	}

	public void setVida(Integer vida) {
		this.vida = vida;
	}

	public Integer getAtaque() {
		Double base = (double) getPokemon().getAtaque();
		return (int) Math.floor(2 * base * getNivel()/100) + 5;
	}

	public void setAtaque(Integer ataque) {
		this.ataque = ataque;
	}

	public Integer getDefesa() {
		Double base = (double) getPokemon().getDefesa();
		return (int) Math.floor(2 * base * getNivel()/100) + 5;
	}

	public void setDefesa(Integer defesa) {
		this.defesa = defesa;
	}

	public Integer getAtaque_e() {
		Double base = (double) getPokemon().getAtaque_e();
		return (int) Math.floor(2 * base * getNivel()/100) + 5;
	}

	public void setAtaque_e(Integer ataque_e) {
		this.ataque_e = ataque_e;
	}

	public Integer getDefesa_e() {
		Double base = (double) getPokemon().getDefesa_e();
		return (int) Math.floor(2 * base * getNivel()/100) + 5;
	}

	public void setDefesa_e(Integer defesa_e) {
		this.defesa_e = defesa_e;
	}

	public Integer getVelocidade() {
		Double base = (double) getPokemon().getVelocidade();
		return (int) Math.floor(2 * base * getNivel()/100) + 5;
	}

	public void setVelocidade(Integer velocidade) {
		this.velocidade = velocidade;
	}
	
	public List<AtaqueEnsinado> getMeusAtaques() {
		AtaqueEnsinadoDao dao = new AtaqueEnsinadoDao();
		List<AtaqueEnsinado> ataques = dao.listarTodos();
		ArrayList<AtaqueEnsinado> resultado = new ArrayList<AtaqueEnsinado>();
		for (AtaqueEnsinado atq : ataques) {
			if(atq.getEspecime().getCodigo() == getCodigo())
				resultado.add(atq);
		}
		return resultado;
	}
}
