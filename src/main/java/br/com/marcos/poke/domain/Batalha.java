package br.com.marcos.poke.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Batalha{
	private Especime pokemon1;
	private Especime pokemon2;
	private List<String> mensagens;
	
	public void lutar() {
		mensagens = new ArrayList<String>();
		int[] vida = {pokemon1.getPokemon().getVida(), pokemon2.getPokemon().getVida()};
		//mensagens.add(String.valueOf(pokemon1.getVida()));
		//mensagens.add(String.valueOf(pokemon2.getVida()));
		int dano = 0;
		while(vida[0] > 0 && vida[1] > 0) {
			mensagens.add(pokemon1.getApelidoOuNome() + " tem " + vida[0] + " pontos de vida.");
			mensagens.add(pokemon2.getApelidoOuNome() + " tem " + vida[1] + " pontos de vida.");
			dano = calculaDano(pokemon1, pokemon2);
			mensagens.add(pokemon1.getApelidoOuNome() + " ataca " + pokemon2.getApelidoOuNome() + ", causando "
					+ dano + " pontos de dano!");
			vida[1] -= dano;
			if(vida[1] < 1) {
				mensagens.add(pokemon2.getApelidoOuNome() + " não consegue mais lutar!");
				mensagens.add(pokemon1.getApelidoOuNome() + " é o vencedor!");
				return;
			}
			dano = calculaDano(pokemon2, pokemon1);
			mensagens.add(pokemon2.getApelidoOuNome() + " ataca " + pokemon1.getApelidoOuNome() + ", causando "
					+ dano + " pontos de dano!");
			vida[0] -= dano;
			if(vida[0] < 1) {
				mensagens.add(pokemon1.getApelidoOuNome() + " não consegue mais lutar!");
				mensagens.add(pokemon2.getApelidoOuNome() + " é o vencedor!");
			}
		}
	}
	
	public int calculaDano (Especime p1, Especime p2) {
		double div = ((double) p1.getPokemon().getAtaque())/p2.getPokemon().getDefesa();
		double modificador = 0.85;
		return (int) Math.ceil((((20 * 60 * div)/50) + 2) * modificador); 
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
