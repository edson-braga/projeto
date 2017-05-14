package br.com.braga.pedidos.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.braga.pedidos.model.Pedido;
import br.com.braga.pedidos.util.HibernateUtil;

public class PedidoDao {
	
	private Session session;
	private Transaction transaction;
	private List<Pedido> pedidos;
	private Pedido pedido;
	
	@SuppressWarnings("unchecked")
	public List<Pedido> listarTodos() {
		pedidos = (List<Pedido>) openCurrentSessionwithTransaction().createQuery("from Pedido").list();
		return pedidos;
	}

	public boolean pedidoExiste(long id) {
		try {
			if(listarPedido(id) == null) {
				return false;
			} else {
				return true;
			}
		} catch(Exception exception) {
			return false;
		}
	}

	public void cadastrarPedido(Pedido pedido) {
		openCurrentSessionwithTransaction().save(pedido);
		transaction.commit();
		session.close();
	}

	public Pedido listarPedido(long numeroControle) {
		Query query = openCurrentSessionwithTransaction().createQuery("from Pedido where numeroControle = :numeroControle ");
		query.setParameter("numeroControle", numeroControle);
		pedido = (Pedido) query.uniqueResult();
		return pedido;
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> listarTodosPorDataCadastro(Date dataCadastro) {
		Query query = openCurrentSessionwithTransaction().createQuery("from Pedido where dataCadastro = :dataCadastro ");
		query.setParameter("dataCadastro", dataCadastro);
		pedidos = (List<Pedido>) query.list();
		return pedidos;
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> listarTodosPorCliente(long idCliente) {
		pedido = new Pedido();
		Query query = openCurrentSessionwithTransaction().createQuery("from Pedido where codigoCliente = :idCliente ");
		query.setParameter("idCliente", idCliente);
		pedidos = (List<Pedido>) query.list();
		return pedidos;
	}
	
	public Session openCurrentSessionwithTransaction() {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		return session;
	}
}
