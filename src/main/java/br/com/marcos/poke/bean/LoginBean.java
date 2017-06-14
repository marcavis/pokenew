package br.com.marcos.poke.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.marcos.poke.dao.UsuarioDao;
import br.com.marcos.poke.domain.Usuario;
import br.com.marcos.poke.util.SessionContext;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
	 
	private Usuario user;
	private String login,senha;
	
	public String fazLogin(){
		try {
			UsuarioDao dao = new UsuarioDao();
			user = dao.buscarLoginSenha(login, senha);
			if(user != null){
				SessionContext.getInstance().setAttribute("usuarioLogado", user);
				return "/templates/principal.xhtml?faces-redirect=true";
			}else{
				FacesContext.getCurrentInstance().validationFailed();
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
