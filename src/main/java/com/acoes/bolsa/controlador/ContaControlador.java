package com.acoes.bolsa.controlador;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.acoes.bolsa.modelo.Conta;
import com.acoes.bolsa.modelo.Monitoramento;
import com.acoes.bolsa.modelo.Negociacao;
import com.acoes.bolsa.servicos.ServicoConta;
import com.acoes.bolsa.servicos.ServicoMonitoramento;

@RestController
@RequestMapping("/conta")
public class ContaControlador {

	@Autowired
	private ServicoConta servicoConta;
	@Autowired 
	private ServicoMonitoramento servicoMonitoramento;

	
	@PostMapping("cadastrar")
	public void cadastrarConta(@RequestBody(required = true) Conta conta) {
		servicoConta.salvarConta(conta);
	}
	

	@GetMapping("{id}")
	public Conta consultarConta(@PathVariable("id") long id) {
		return servicoConta.consultarContaPorId(id);
	}
	
	@PostMapping("{id}/monitoramentos/cadastrar")
	public void cadastrarMonitoramento(
			@PathVariable("id") Long contaId,
			@RequestBody(required = true) Monitoramento monitoramento) {
		
		servicoMonitoramento.salvarMonitoramento(
				contaId, 
				monitoramento
		);
	}
	
	@PutMapping("{id}/monitoramentos/atualizar")
	public void atualizarMonitoramento(
			@PathVariable("id") Long contaId,
			@RequestBody(required = true) Monitoramento monitoramento) {
		
		servicoMonitoramento.salvarMonitoramento(
				contaId, 
				monitoramento
		);
	}
	
	@DeleteMapping("{conta_id}/monitoramentos/remover/{moni_id}")
	public void removerMonitoramentos(
			@PathVariable("conta_id") Long contaId, 
			@PathVariable("moni_id") Long monitoramentoId) {
		
		servicoMonitoramento.excluirMonitoramento(monitoramentoId);
	}
	
	@GetMapping("{id}/monitoramentos")
	public Set<Monitoramento> consultarMonitoramentos(@PathVariable("id") Long id) {
		Conta conta = servicoConta.consultarContaPorId(id);
		return conta.getMonitoramentos();
	}
	
	
	@GetMapping("{conta_id}/historico")
	public Set<Negociacao> consultarHistorico(@PathVariable("conta_id") Long id) {
		Conta conta = servicoConta.consultarContaPorId(id);
		return conta.getNegociacoes();
	}

}
