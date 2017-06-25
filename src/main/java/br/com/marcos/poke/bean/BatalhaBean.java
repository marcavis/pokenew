package br.com.marcos.poke.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.marcos.poke.domain.Batalha;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class BatalhaBean implements Serializable {
	private Batalha batalha;
	
	public void novo(ActionEvent evento) {
		batalha = new Batalha();
	}

	public Batalha getBatalha() {
		return batalha;
	}

	public void setBatalha(Batalha batalha) {
		this.batalha = batalha;
	}
	
	
}
