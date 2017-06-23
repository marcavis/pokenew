package br.com.marcos.poke.domain;

import java.util.List;
import java.util.Random;

public class Lutador {
	private Especime especime;
	private Lutador adversario;
	
	private Integer vidaAtual;
	private boolean derrotado;

	public Lutador(Especime especime) {
		this.especime = especime;
		setVidaAtual(especime.getVida());
		setDerrotado(false);
	}
	
	public Especime getEspecime() {
		return especime;
	}

	public void setEspecime(Especime especime) {
		this.especime = especime;
	}

	public Lutador getAdversario() {
		return adversario;
	}

	public void setAdversario(Lutador adversario) {
		this.adversario = adversario;
	}

	public Integer getVidaAtual() {
		return vidaAtual;
	}

	public void setVidaAtual(Integer vidaAtual) {
		this.vidaAtual = vidaAtual;
	}
	
	public boolean isDerrotado() {
		return derrotado;
	}

	public void setDerrotado(boolean derrotado) {
		this.derrotado = derrotado;
	}

	public String getNome() {
		return getEspecime().getApelidoOuNome();
	}
	
	public void atacar(Lutador adversario, List<String> mensagens) {
		double div = ((double) getEspecime().getPokemon().getAtaque())/adversario.getEspecime().getPokemon().getDefesa();
		Random gerador = new Random();
		double fatorAleatorio = 0.85 + gerador.nextDouble() * 0.15;
		double modificador = fatorAleatorio;
		int dano = (int) Math.ceil((((20 * 30 * div)/50) + 2) * modificador);
		adversario.receberAtaque(dano, mensagens); 
	}
	
	public void receberAtaque(int dano, List<String> mensagens) {
		mensagens.add(adversario.getNome() + " atacou " + getNome() + ", causando "
				+ dano + " pontos de dano!");
		if (dano >= getVidaAtual()) {
			setDerrotado(true);
			mensagens.add(getNome() + " não consegue mais lutar!");
			adversario.vencer(mensagens);
		} else {
			setVidaAtual(getVidaAtual() - dano);
		}
	}
	
	public void vencer(List<String> mensagens) {
		mensagens.add(getNome() + " é o vencedor!");
	}
}
