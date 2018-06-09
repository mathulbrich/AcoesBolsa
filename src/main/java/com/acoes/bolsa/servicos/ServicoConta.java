package com.acoes.bolsa.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acoes.bolsa.modelo.Conta;
import com.acoes.bolsa.repositorio.RepositorioConta;

@Service
public class ServicoConta {

	@Autowired
	private RepositorioConta repoConta;
	
	@Transactional
	public Conta salvarConta(Conta conta) {
		conta = repoConta.save(conta);
		return conta;
	}
	
	public Conta consultarContaPorId(Long id) {
		return repoConta.findById(id).get();
	}
	
	public List<Conta> consultarTodasContas() {
		return repoConta.findAll();
	}
	
}
