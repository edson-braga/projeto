package br.com.braga.pedidos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.braga.pedidos.model.Pedido;

public interface RestApiController {

	ResponseEntity<List<Pedido>> listarTodosPedidos();

	ResponseEntity<?> listarPorNumeroControle(long id);

	ResponseEntity<List<Pedido>> listarPorCliente(long idCliente);

	ResponseEntity<List<Pedido>> listarPorDataCadastro(String dataCadastro);

	ResponseEntity<?> criarPedido(Pedido pedido, UriComponentsBuilder uriComponentsBuilder);

}