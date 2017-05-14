package br.com.braga.pedidos.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@XmlRootElement(name="cliente")
@Table(name = "clientes")
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = -8143685086990846502L;

	@Id
	@XmlAttribute(name = "id")
	@Column(name = "idCliente")
	private int id;
	
	@XmlAttribute(name = "nomeCliente")
	@Column(name = "nomeCliente")
	private String nomeCliente;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
	@JsonBackReference
	private Set<Pedido> pedidos = new HashSet<Pedido>(0);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
