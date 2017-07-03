package br.com.marcos.poke.domain;

import java.util.List;
import java.util.Random;

import br.com.marcos.poke.dao.EfetividadeDao;

public class Lutador {
	private Especime especime;
	private Lutador adversario;
	
	private Ataque ataqueSelecionado;
	private Jogador controlador;
	private Integer vidaAtual;
	private boolean derrotado;

	public Lutador(Especime especime, Jogador controlador) {
		setEspecime(especime);
		setControlador(controlador);
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

	public Ataque getAtaqueSelecionado() {
		return ataqueSelecionado;
	}

	public void setAtaqueSelecionado(Ataque ataqueSelecionado) {
		this.ataqueSelecionado = ataqueSelecionado;
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

	public Jogador getControlador() {
		return controlador;
	}

	public void setControlador(Jogador controlador) {
		this.controlador = controlador;
	}
	
	public boolean isJogador() {
		return getControlador() == Jogador.JOGADOR;
	}

	public String getNome() {
		return getEspecime().getApelidoOuNome();
	}
	
	public Ataque escolherAtaque() {
		Random gerador = new Random();
		int qtAtaques = getEspecime().getMeusAtaques().size();
		return getEspecime().getMeusAtaques().get(gerador.nextInt(qtAtaques)).getAtaque();
	}
	
	public void atacar(Lutador adversario, List<String> mensagens) {
		Ataque ataque;
		if(isJogador())
			ataque = getAtaqueSelecionado();
		else
			ataque = escolherAtaque();
		
		//adversario.receberAtaque(dano, ataque.getNome(), mensagens); 
		adversario.receberAtaque(ataque, mensagens);
	}
	
	public void receberAtaque(Ataque ataque, List<String> mensagens) {
		double div;
		if(ataque.getEspecial()) {
			div = ((double) adversario.getEspecime().getPokemon().getAtaque_e())/getEspecime().getPokemon().getDefesa_e();
		} else {
			div = ((double) adversario.getEspecime().getPokemon().getAtaque())/getEspecime().getPokemon().getDefesa();
		}
		Random gerador = new Random();
		double modNivel = (double) adversario.getEspecime().getNivel() * 0.4 + 2;
		double fatorAleatorio = 0.85 + gerador.nextDouble() * 0.15;
		double fatorEfetividade = efetividade(ataque);
		double modificador = fatorAleatorio * stab(ataque, adversario) * fatorEfetividade;
		int dano = (int) Math.ceil((((modNivel * ataque.getPoder() * div)/50) + 2) * modificador);
		mensagens.add(adversario.getNome() + " atacou " + getNome() + " com "
				+ ataque.getNome() + ", causando " + dano + " pontos de dano!");
		if (fatorEfetividade == 0.0) {
			mensagens.add("O ataque não teve efeito nenhum!");
		} else if (fatorEfetividade < 1.0) {
			mensagens.add("O ataque não foi muito efetivo...");
		} else if (fatorEfetividade > 1.0) {
			mensagens.add("O ataque foi super-efetivo!");
		}
		//System.out.println(stab(ataque, adversario) + "stab" + modificador);
		if (dano >= getVidaAtual()) {
			setDerrotado(true);
			setVidaAtual(0);
			mensagens.add(getNome() + " não consegue mais lutar!");
			adversario.vencer(mensagens);
		} else {
			setVidaAtual(getVidaAtual() - dano);
		}
	}
	
	//retorna 1.5 (ou seja, 50% mais poder) se o ataque é de um tipo possuído pelo pokémon, 1 caso não seja
	public double stab(Ataque ataque, Lutador adversario) {
		if(ataque.getTipo().getCodigo() == adversario.getEspecime().getPokemon().getTipo1().getCodigo() ||
				(adversario.getEspecime().getPokemon().getTipo2() != null &&
				ataque.getTipo().getCodigo() == adversario.getEspecime().getPokemon().getTipo2().getCodigo()))
			return 1.5;
		else
			return 1.0;
	}
	
	public double efetividade(Ataque ataque) {
		EfetividadeDao dao = new EfetividadeDao();
		//se não houver efetividade definida, seja por não ter sido cadastrada,
		//seja por o adversário só ter um tipo, usar 100% como padrão
		double ef1 = 1.0;
		double ef2 = 1.0;
		for (Efetividade ef : dao.listarTodos()) {
			if(ef.getTipo1().getCodigo() == ataque.getTipo().getCodigo() &&
					ef.getTipo2().getCodigo() == getEspecime().getPokemon().getTipo1().getCodigo()) {
				ef1 = ((double) ef.getEfetividade()) / 100;
				//System.out.println("ef1 " + ef1 + "," + ataque.getTipo().getNome() + "," + getEspecime().getPokemon().getTipo1().getNome());
			}
			if(getEspecime().getPokemon().getTipo2() != null &&
					ef.getTipo1().getCodigo() == ataque.getTipo().getCodigo() &&
					ef.getTipo2().getCodigo() == getEspecime().getPokemon().getTipo2().getCodigo()) {
				ef2 = ((double) ef.getEfetividade()) / 100;
			}
		}
		return ef1 * ef2;
	}
	
	public void vencer(List<String> mensagens) {
		mensagens.add(getNome() + " é o vencedor!");
	}
}
