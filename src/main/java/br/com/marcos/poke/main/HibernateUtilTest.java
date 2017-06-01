package br.com.marcos.poke.main;

import java.util.ArrayList;

import br.com.marcos.poke.dao.EstadoDao;
import br.com.marcos.poke.domain.Estado;

public class HibernateUtilTest {
	public static void main(String[] args) {
		//Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		//sessao.close();
		//HibernateUtil.getFabricaDeSessoes().close();
		
		EstadoDao estado = new EstadoDao();
		ArrayList<Estado> lista = (ArrayList<Estado>)estado.listarTodos();
		for (Estado e : lista) {
			System.out.println(e.getNome()+"-"+e.getSigla());
		}
		
		//GenericDao<Estado> dao = new EstadoDao();
		//dao.Salvar(e);
	}
}
