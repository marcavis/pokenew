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
	
	public void lutar() {
		mensagens = new ArrayList<String>();
		
		lutadores = new ArrayList<Lutador>();
		//a enum Jogador ainda não faz nada 
		lutadores.add(new Lutador(pokemon1, Jogador.JOGADOR));
		lutadores.add(new Lutador(pokemon2, Jogador.COMPUTADOR));
		lutadores.get(0).setAdversario(lutadores.get(1));
		lutadores.get(1).setAdversario(lutadores.get(0));
		boolean fim = false;
		while(fim == false) {
			lutadores = ordenarPorVelocidade(lutadores);
			for (Lutador l : lutadores) {
				mensagens.add(l.getNome() + " tem " + l.getVidaAtual() + " pontos de vida.");
			}
			for (Lutador l : lutadores) {
				l.atacar(l.getAdversario(), mensagens);
				if(l.getAdversario().isDerrotado()) {
					fim = true;
					break;
				}
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

	public int calculaDano(Especime p1, Especime p2) {
		double div = ((double) p1.getPokemon().getAtaque())/p2.getPokemon().getDefesa();
		Random gerador = new Random();
		double fatorAleatorio = 0.85 + gerador.nextDouble() * 0.15;
		double modificador = fatorAleatorio;
		return (int) Math.ceil((((20 * 30 * div)/50) + 2) * modificador); 
		//return Math.max(2, p1.getPokemon().getAtaque()-p2.getPokemon().getDefesa());
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
}
