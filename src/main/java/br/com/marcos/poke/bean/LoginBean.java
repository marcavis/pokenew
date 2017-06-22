package br.com.marcos.poke.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;

import br.com.marcos.poke.dao.UsuarioDao;
import br.com.marcos.poke.domain.Usuario;
import br.com.marcos.poke.util.SessionContext;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
	 
	private Usuario user;
	private String login,senha;
	private List<Usuario> users;
	
	public String fazLogin(){
		try {
			UsuarioDao dao = new UsuarioDao();
			user = dao.buscarLoginSenha(login, senha);
			if(user != null){
				SessionContext.getInstance().setAttribute("usuarioLogado", user);
				return "/templates/principal.xhtml?faces-redirect=true";
			}else{
				FacesContext.getCurrentInstance().validationFailed();
				Messages.addGlobalInfo("Combinação incorreta de Usuário e Senha.");
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
	
	public List<Usuario> getUsers() {
		return users;
	}

	public void setUsers(List<Usuario> users) {
		this.users = users;
	}

	public void salvar() {
		try {
			novo();
			listar();
			user.setLogin(login);
			user.setSenha(senha);
			UsuarioDao dao = new UsuarioDao();
			for (Usuario u : users) {
				if(u.getLogin().equals(user.getLogin())) {
					Messages.addGlobalError("Usuário " + user.getLogin() + " já foi cadastrado!");
					throw new Exception();
				}
			}
			dao.merge(user);
			Messages.addGlobalInfo("Usuário cadastrado com sucesso");
			novo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			
			UsuarioDao dao = new UsuarioDao();
			users = dao.listarTodos();
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar Usuários");
			e.printStackTrace();
		}
	}
	
	private void novo() {
		user = new Usuario();
	}
}
