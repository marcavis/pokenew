package br.com.marcos.poke.domain;
//google drive: http://goo.gl/TwK3ZM

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Pokemon extends GenericDomain{

	@Column
	private Integer numero;
	
	@Column(length=12, nullable=false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Tipo tipo1;
	
	@ManyToOne
	@JoinColumn
	private Tipo tipo2;
	
	@Column(nullable=false)
	private Integer vida;
	
	@Column(nullable=false)
	private Integer ataque;
	
	@Column(nullable=false)
	private Integer defesa;
	
	@Column(nullable=false)
	private Integer ataque_e;
	
	@Column(nullable=false)
	private Integer defesa_e;
	
	@Column(nullable=false)
	private Integer velocidade;
	
	@Transient
	private String caminhoImg;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Tipo getTipo1() {
		return tipo1;
	}

	public void setTipo1(Tipo tipo1) {
		this.tipo1 = tipo1;
	}

	public Tipo getTipo2() {
		return tipo2;
	}

	public void setTipo2(Tipo tipo2) {
		this.tipo2 = tipo2;
	}

	public Integer getVida() {
		return vida;
	}

	public void setVida(Integer vida) {
		this.vida = vida;
	}

	public Integer getAtaque() {
		return ataque;
	}

	public void setAtaque(Integer ataque) {
		this.ataque = ataque;
	}

	public Integer getDefesa() {
		return defesa;
	}

	public void setDefesa(Integer defesa) {
		this.defesa = defesa;
	}

	public Integer getAtaque_e() {
		return ataque_e;
	}

	public void setAtaque_e(Integer ataque_e) {
		this.ataque_e = ataque_e;
	}

	public Integer getDefesa_e() {
		return defesa_e;
	}

	public void setDefesa_e(Integer defesa_e) {
		this.defesa_e = defesa_e;
	}

	public Integer getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Integer velocidade) {
		this.velocidade = velocidade;
	}

	public String getCaminhoImg() {
		return caminhoImg;
	}

	public void setCaminhoImg(String caminhoImg) {
		this.caminhoImg = caminhoImg;
	}
}
