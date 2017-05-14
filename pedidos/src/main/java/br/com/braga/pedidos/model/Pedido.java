package br.com.braga.pedidos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@XmlRootElement(name="pedido")
@Table(name = "pedidos")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 2799208418798697257L;
	
	@Id
	@Column(name = "numeroControle")
	@XmlAttribute(name = "numeroControle")
	private long numeroControle;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@XmlAttribute(name = "dataCadastro")
	@Column(name = "dataCadastro")
	private Date dataCadastro;
	
	@XmlAttribute(name = "nomeProduto")
	@Column(name = "nomeProduto")
	private String nomeProduto;
	
	@XmlAttribute(name = "valorUnitario")
	@Column(name = "valorUnitario")
	private double valorUnitario;
	
	@XmlAttribute(name = "quantidade")
	@Column(name = "quantidade")
	private int quantidade;

	@XmlAttribute(name = "valorTotal")
	@Column(name = "valorTotal")
	private Double valorTotal;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigoCliente", nullable = false)
	@XmlAttribute(name = "cliente")
	@JsonManagedReference
	@JsonIgnore
	private Cliente cliente;
	

	public long getNumeroControle() {
		return numeroControle;
	}

	public void setNumeroControle(long numeroControle) {
		this.numeroControle = numeroControle;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Cliente getCodigoCliente() {
		return cliente;
	}

	public void setCodigoCliente(Cliente codigoCliente) {
		this.cliente = codigoCliente;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public boolean validarPedido(Pedido pedido) {
		if(this.dataCadastro == null) {
			this.dataCadastro = new Date();
		}
		
		if(quantidade == 0) {
			this.quantidade = 1;
		}
		
		this.valorTotal = valorUnitario * quantidade;
		calcularDesconto();
		
		return true;
	}

	private void calcularDesconto() {
		double desconto = 0.0;
		if(this.quantidade > 5) {
			desconto = this.valorTotal * 0.05;
			this.valorTotal = this.valorTotal - desconto; 
		} else if(this.quantidade >= 10) {
			desconto = this.valorTotal * 0.10;
			this.valorTotal = this.valorTotal - desconto;
		}
	}


}
