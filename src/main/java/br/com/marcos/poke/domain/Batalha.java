package br.com.marcos.poke.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Batalha{
	private Especime pokemon1;
	private Especime pokemon2;
	private ArrayList<Lutador> lutadores;
	private List<String> mensagens;
	private Lutador lutadorDaEsquerda;
	private Lutador lutadorDaDireita;
	private Boolean fim = false;
	
	public void lutar() {
		setMensagens(new ArrayList<String>());
		
		setLutadores(new ArrayList<Lutador>());
		lutadores.add(new Lutador(pokemon1, Jogador.JOGADOR));
		setLutadorDaEsquerda(lutadores.get(0));
		lutadores.add(new Lutador(pokemon2, Jogador.COMPUTADOR));
		setLutadorDaDireita(lutadores.get(1));
		lutadores.get(0).setAdversario(lutadores.get(1));
		lutadores.get(1).setAdversario(lutadores.get(0));
	}
	
	public void rodada() {
		lutadores = ordenarPorVelocidade(lutadores);
		setMensagens(new ArrayList<String>());
		for (Lutador l : lutadores) {
			l.atacar(l.getAdversario(), mensagens);
			if(l.getAdversario().isDerrotado()) {
				setFim(true);
				break;
			}
		}
	}
	
	//Ordenar a lista de lutadores de modo que o mais rápido ataque primeiro
	//Se ambos têm a mesma velocidade, decidir aleatoriamente pela ordem
	private ArrayList<Lutador> ordenarPorVelocidade(ArrayList<Lutador> lutadores) {
		//ArrayList<Lutador> novaLista = new ArrayList<Lutador>()
		Random gerador = new Random();
		if (lutadores.get(0).getEspecime().getVelocidade() > lutadores.get(1).getEspecime().getVelocidade() ||
				lutadores.get(0).getEspecime().getVelocidade() == lutadores.get(1).getEspecime().getVelocidade() &&
				gerador.nextDouble() < 0.5)
			return lutadores;
		else
			return new ArrayList<Lutador>(Arrays.asList(lutadores.get(1), lutadores.get(0)));
	}
	
	public Especime getPokemon1() {
		return pokemon1;
	}
	
	public void setPokemon1(Especime pokemon1) {
		this.pokemon1 = pokemon1;
	}
	
	public Especime getPokemon2() {
		return pokemon2;
	}
	
	public void setPokemon2(Especime pokemon2) {
		this.pokemon2 = pokemon2;
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}

	public ArrayList<Lutador> getLutadores() {
		return lutadores;
	}

	public void setLutadores(ArrayList<Lutador> lutadores) {
		this.lutadores = lutadores;
	}

	public Lutador getLutadorDaEsquerda() {
		return lutadorDaEsquerda;
	}

	public void setLutadorDaEsquerda(Lutador lutadorDaEsquerda) {
		this.lutadorDaEsquerda = lutadorDaEsquerda;
	}
	
	public String getVidaEsquerda() {
		return getLutadorDaEsquerda().getNome() + ": "
				+ getLutadorDaEsquerda().getVidaAtual() + "/" + getLutadorDaEsquerda().getEspecime().getVida()
				+ " ❤";
	}
	
	public Lutador getLutadorDaDireita() {
		return lutadorDaDireita;
	}

	public void setLutadorDaDireita(Lutador lutadorDaDireita) {
		this.lutadorDaDireita = lutadorDaDireita;
	}
	
	public String getVidaDireita() {
		return getLutadorDaDireita().getNome() + ": "
				+ getLutadorDaDireita().getVidaAtual() + "/" + getLutadorDaDireita().getEspecime().getVida()
				+ " ❤";
	}

	public Boolean getFim() {
		return fim;
	}

	public void setFim(Boolean fim) {
		this.fim = fim;
	}
}
