package br.com.marcos.poke.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.marcos.poke.domain.Batalha;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class BatalhaBean implements Serializable {
	private Batalha batalha;
	
	public void novo() {
		batalha = new Batalha();
		System.out.println("COMECOU");
	}

	public Batalha getBatalha() {
		return batalha;
	}

	public void setBatalha(Batalha batalha) {
		this.batalha = batalha;
	}
	
	
}
