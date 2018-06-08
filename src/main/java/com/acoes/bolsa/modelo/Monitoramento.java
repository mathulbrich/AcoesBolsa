package com.acoes.bolsa.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "monitoramento")
public class Monitoramento {

	private Long id;
	private Float precoCompra;
	private Float precoVenda;
	private String nomeEmpresa;
	private Conta conta;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "monitoramento_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public Float getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(Float precoCompra) {
		this.precoCompra = precoCompra;
	}

	@Column(nullable = false)
	public Float getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Float precoVenda) {
		this.precoVenda = precoVenda;
	}

	@ManyToOne
	@JoinColumn(name = "conta_id", referencedColumnName = "conta_id", nullable = false)
	@JsonBackReference
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Column(nullable = false)
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeEmpresa == null) ? 0 : nomeEmpresa.hashCode());
		result = prime * result + ((precoCompra == null) ? 0 : precoCompra.hashCode());
		result = prime * result + ((precoVenda == null) ? 0 : precoVenda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monitoramento other = (Monitoramento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeEmpresa == null) {
			if (other.nomeEmpresa != null)
				return false;
		} else if (!nomeEmpresa.equals(other.nomeEmpresa))
			return false;
		if (precoCompra == null) {
			if (other.precoCompra != null)
				return false;
		} else if (!precoCompra.equals(other.precoCompra))
			return false;
		if (precoVenda == null) {
			if (other.precoVenda != null)
				return false;
		} else if (!precoVenda.equals(other.precoVenda))
			return false;
		return true;
	}

}
