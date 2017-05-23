package br.com.marcos.poke.domain;

import java.util.ArrayList;
import java.util.List;

public class Batalha{
	private Pokemon pokemon1;
	private Pokemon pokemon2;
	private List<String> mensagens;
	
	public void lutar() {
		mensagens = new ArrayList<String>();
		int[] vida = {pokemon1.getVida(), pokemon2.getVida()};
		//mensagens.add(String.valueOf(pokemon1.getVida()));
		//mensagens.add(String.valueOf(pokemon2.getVida()));
		int dano = 0;
		while(vida[0] > 0 && vida[1] > 0) {
			mensagens.add(pokemon1.getNome() + " tem " + vida[0] + " pontos de vida.");
			mensagens.add(pokemon2.getNome() + " tem " + vida[1] + " pontos de vida.");
			dano = calculaDano(pokemon1, pokemon2);
			mensagens.add(pokemon1.getNome() + " ataca " + pokemon2.getNome() + ", causando "
					+ dano + " pontos de dano!");
			vida[1] -= dano;
			if(vida[1] < 1) {
				mensagens.add(pokemon2.getNome() + " não consegue mais lutar!");
				mensagens.add(pokemon1.getNome() + " é o vencedor!");
				return;
			}
			dano = calculaDano(pokemon2, pokemon1);
			mensagens.add(pokemon2.getNome() + " ataca " + pokemon1.getNome() + ", causando "
					+ dano + " pontos de dano!");
			vida[0] -= dano;
			if(vida[0] < 1) {
				mensagens.add(pokemon1.getNome() + " não consegue mais lutar!");
				mensagens.add(pokemon2.getNome() + " é o vencedor!");
			}
		}
	}
	
	public int calculaDano (Pokemon p1, Pokemon p2) {
		return Math.max(2, p1.getAtaque()-p2.getDefesa());
	}
	
	public Pokemon getPokemon1() {
		return pokemon1;
	}
	
	public void setPokemon1(Pokemon pokemon1) {
		this.pokemon1 = pokemon1;
	}
	
	public Pokemon getPokemon2() {
		return pokemon2;
	}
	
	public void setPokemon2(Pokemon pokemon2) {
		this.pokemon2 = pokemon2;
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
}
