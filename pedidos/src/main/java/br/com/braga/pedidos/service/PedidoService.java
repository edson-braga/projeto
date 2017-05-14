package br.com.braga.pedidos.service;

import java.util.Date;
import java.util.List;

import br.com.braga.pedidos.model.Pedido;

public interface PedidoService {

	List<Pedido> listarTodos();

	boolean pedidoExiste(long id);

	void cadastrarPedido(Pedido pedido);

	Pedido listarPedido(long id);

	List<Pedido> listarTodosPorCliente(long idCliente);

	List<Pedido> listarTodosPorDataCadastro(Date dataCadastro);

}