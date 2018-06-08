package com.acoes.bolsa.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "negociacao")
public class Negociacao {

	private Long id;
	private Float valorNegociado;
	private Float quantidade;
	private String nomeEmpresa;
	private TipoNegociacao tipoNegociacao;
	private Date data;
	private Conta conta;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "negociacao_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public Float getValorNegociado() {
		return valorNegociado;
	}

	public void setValorNegociado(Float valorNegociado) {
		this.valorNegociado = valorNegociado;
	}

	@Column(nullable = false)
	public Float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public TipoNegociacao getTipoNegociacao() {
		return tipoNegociacao;
	}

	public void setTipoNegociacao(TipoNegociacao tipoNegociacao) {
		this.tipoNegociacao = tipoNegociacao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss", locale="pt-BR", timezone="Brazil/East")
	@Column(nullable = false)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeEmpresa == null) ? 0 : nomeEmpresa.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((tipoNegociacao == null) ? 0 : tipoNegociacao.hashCode());
		result = prime * result + ((valorNegociado == null) ? 0 : valorNegociado.hashCode());
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
		Negociacao other = (Negociacao) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
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
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (tipoNegociacao != other.tipoNegociacao)
			return false;
		if (valorNegociado == null) {
			if (other.valorNegociado != null)
				return false;
		} else if (!valorNegociado.equals(other.valorNegociado))
			return false;
		return true;
	}

}
