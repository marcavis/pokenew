package br.com.marcos.poke.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.marcos.poke.domain.Batalha;
import br.com.marcos.poke.domain.Equipe;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class BatalhaBean implements Serializable {
	private Batalha batalha;
	
	public void novo(ActionEvent evento) {
		batalha = new Batalha();
		System.out.println("COMECOU");
		Equipe equipeAtual = (Equipe) evento.getComponent().getAttributes().get("equipeAtual");
		System.out.println(equipeAtual.getTreinador());
	}

	public Batalha getBatalha() {
		return batalha;
	}

	public void setBatalha(Batalha batalha) {
		this.batalha = batalha;
	}
	
	
}
