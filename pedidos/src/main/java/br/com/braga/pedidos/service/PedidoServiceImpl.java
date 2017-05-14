package br.com.braga.pedidos.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.braga.pedidos.dao.PedidoDao;
import br.com.braga.pedidos.model.Pedido;

@Service("pedidoService")
public class PedidoServiceImpl implements PedidoService {

	private PedidoDao dao = new PedidoDao();
	
	@Override
	public List<Pedido> listarTodos() {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		pedidos = dao.listarTodos();
		return pedidos;
	}

	@Override
	public boolean pedidoExiste(long id) {
		return dao.pedidoExiste(id);
	}

	@Override
	public void cadastrarPedido(Pedido pedido) {
		if(pedido.validarPedido(pedido)) {
			dao.cadastrarPedido(pedido);
		}
	}

	@Override
	public Pedido listarPedido(long numeroControle) {
		Pedido pedido = new Pedido();
		pedido = dao.listarPedido(numeroControle);
		return pedido;
	}

	@Override
	public List<Pedido> listarTodosPorCliente(long idCliente) {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		pedidos = dao.listarTodosPorCliente(idCliente);
		return pedidos;
	}

	@Override
	public List<Pedido> listarTodosPorDataCadastro(Date dataCadastro) {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		pedidos = dao.listarTodosPorDataCadastro(dataCadastro);
		return pedidos;
	}

}
