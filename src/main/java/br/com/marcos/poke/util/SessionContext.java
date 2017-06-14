package br.com.marcos.poke.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.marcos.poke.domain.Usuario;

public class SessionContext {
	
	private static SessionContext instance;
	
	public static SessionContext getInstance(){
        if (instance == null){
            instance = new SessionContext();
        }
        return instance;
	}
	
	private ExternalContext currentExternalContext(){
		if (FacesContext.getCurrentInstance() == null){
        	throw new RuntimeException("Falta uma requisição HTTP");
        } else {
        	return FacesContext.getCurrentInstance().getExternalContext();
        }
	}
	
	public Usuario getUsuarioLogado() {
		return (Usuario) getAttribute("usuarioLogado");
	}
	 
	public void setUsuarioLogado(Usuario usuario){
		setAttribute("usuarioLogado", usuario);
	}
	 
	public void encerrarSessao(){   
		currentExternalContext().invalidateSession();
	}
	 
	public Object getAttribute(String nome){
		return currentExternalContext().getSessionMap().get(nome);
	}
	 
	public void setAttribute(String nome, Object valor){
		currentExternalContext().getSessionMap().put(nome, valor);
	}
}
