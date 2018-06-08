package com.acoes.bolsa.modelo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="conta")
public class Conta {

	private Long id;
	private String email;
	private Float saldo;
	private float quantidadeAcoes;
	private Set<Negociacao> negociacoes;
	private Set<Monitoramento> monitoramentos;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="conta_id")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable=false)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(nullable=false)
	public Float getSaldo() {
		return saldo;
	}
	
	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}
	
	public float getQuantidadeAcoes() {
		return quantidadeAcoes;
	}

	public void setQuantidadeAcoes(Float quantidadeAcoes) {
		this.quantidadeAcoes = quantidadeAcoes;
	}

	@OneToMany(mappedBy="conta", fetch=FetchType.EAGER)
	@JsonManagedReference
	public Set<Negociacao> getNegociacoes() {
		return negociacoes;
	}
	
	public void setNegociacoes(Set<Negociacao> negociacoes) {
		this.negociacoes = negociacoes;
	}
	
	@OneToMany(mappedBy="conta", fetch=FetchType.EAGER)
	@JsonManagedReference
	public Set<Monitoramento> getMonitoramentos() {
		return monitoramentos;
	}
	
	public void setMonitoramentos(Set<Monitoramento> monitoramentos) {
		this.monitoramentos = monitoramentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
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
		Conta other = (Conta) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		return true;
	}
	
}
