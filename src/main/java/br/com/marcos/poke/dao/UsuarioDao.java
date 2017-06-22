package br.com.marcos.poke.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.marcos.poke.domain.Usuario;
import br.com.marcos.poke.util.HibernateUtil;

public class UsuarioDao extends GenericDao<Usuario>{
	
	public Usuario buscarLoginSenha(String login, String senha){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.add(Restrictions.eq("login", login));
			consulta.add(Restrictions.eq("senha", senha));
			Usuario resultado = (Usuario)consulta.uniqueResult();
			return resultado;
		} catch (Exception e) {
			throw(e);
		}finally{
			sessao.close();
		}
	}

}
