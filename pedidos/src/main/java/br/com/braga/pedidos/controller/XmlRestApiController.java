package br.com.braga.pedidos.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.braga.pedidos.model.Pedido;
import br.com.braga.pedidos.service.PedidoService;

@RestController
@RequestMapping("/api/xml")
public class XmlRestApiController implements RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(XmlRestApiController.class);
	
	@Autowired
	PedidoService pedidoService;
	
	@RequestMapping(value = "/pedido/", method = RequestMethod.GET, produces={MediaType.APPLICATION_XML_VALUE},headers = "Accept=application/xml") 
	@Override
	public ResponseEntity<List<Pedido>> listarTodosPedidos() {
		List<Pedido> pedidos = pedidoService.listarTodos();
		if(pedidos.isEmpty()) {
			   return new ResponseEntity<List<Pedido>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Pedido>>(pedidos, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/pedido/numeroControle/{id}", method = RequestMethod.GET, produces={MediaType.APPLICATION_XML_VALUE},headers = "Accept=application/xml")
	public ResponseEntity<?> listarPorNumeroControle(long id) {
		logger.info("Localizando pedidos pelo número de controle", id);
		Pedido pedido = pedidoService.listarPedido(id);
		if(pedido == null) {
			logger.error("Não foi possível localizar o pedido com o número de controle: " + id);
			   return new ResponseEntity<String>(new String("Não existe pedido com número de controle " + id), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/pedido/cliente/{idCliente}", method = RequestMethod.GET, produces={MediaType.APPLICATION_XML_VALUE},headers = "Accept=application/xml")
	public ResponseEntity<List<Pedido>> listarPorCliente(long idCliente) {
		List<Pedido> pedidos = pedidoService.listarTodosPorCliente(idCliente);
		if(pedidos.isEmpty()) {
			return new ResponseEntity<List<Pedido>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Pedido>>(pedidos, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/pedido/dataCadastro/{data}", method = RequestMethod.GET, produces={MediaType.APPLICATION_XML_VALUE},headers = "Accept=application/xml")
	public ResponseEntity<List<Pedido>> listarPorDataCadastro(Date dataCadastro) {
		List<Pedido> pedidos = pedidoService.listarTodosPorDataCadastro(dataCadastro);
		if(pedidos.isEmpty()) {
			   return new ResponseEntity<List<Pedido>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Pedido>>(pedidos, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/pedido/", method = RequestMethod.POST)
	public ResponseEntity<?> criarPedido(Pedido pedido, UriComponentsBuilder uriComponentsBuilder) {
		logger.info("Criando o pedido: {}", pedido);
		
		if(pedidoService.pedidoExiste(pedido.getNumeroControle())) {
			logger.error("Não foi possível cadastrar o pedido número de controle já existe", pedido.getNumeroControle());
			return new ResponseEntity<String>(new String("Não foi possível criar o pedido com o número de controle " + 
		            pedido.getNumeroControle()),HttpStatus.CONFLICT);
		}
		pedidoService.cadastrarPedido(pedido);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.setLocation(uriComponentsBuilder.path("/api/pedido/{id}").buildAndExpand(pedido.getNumeroControle()).toUri());
		
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

}
