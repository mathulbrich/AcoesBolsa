package com.acoes.bolsa.controlador;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.acoes.bolsa.modelo.Conta;
import com.acoes.bolsa.modelo.Monitoramento;
import com.acoes.bolsa.modelo.Negociacao;
import com.acoes.bolsa.servicos.ServicoConta;
import com.acoes.bolsa.servicos.ServicoMonitoramento;

@RestController
@EnableAutoConfiguration
@RequestMapping("/conta")
public class ContaControlador {

	@Autowired
	private ServicoConta servicoConta;
	@Autowired 
	private ServicoMonitoramento servicoMonitoramento;

	
	@Transactional
	@RequestMapping(path = "cadastrar", method = POST)
	public void cadastrarConta(@RequestBody(required = true) Conta conta) {
		servicoConta.salvarConta(conta);
	}
	

	@RequestMapping(path = "{id}", method = GET, produces = "application/json")
	public Conta consultarConta(@PathVariable("id") long id) {
		return servicoConta.consultarContaPorId(id);
	}
	
	
	@Transactional
	@RequestMapping(path = "{id}/monitoramentos/cadastrar", method = POST, consumes = "application/json")
	public void cadastrarMonitoramento(
			@PathVariable("id") Long contaId,
			@RequestBody(required = true) Monitoramento monitoramento) {
		
		servicoMonitoramento.salvarMonitoramento(
				contaId, 
				monitoramento
		);
	}
	
	
	@Transactional
	@RequestMapping(path = "{id}/monitoramentos/atualizar", method = PUT, consumes = "application/json")
	public void atualizarMonitoramento(
			@PathVariable("id") Long contaId,
			@RequestBody(required = true) Monitoramento monitoramento) {
		
		servicoMonitoramento.salvarMonitoramento(
				contaId, 
				monitoramento
		);
	}
	
	
	@RequestMapping(path = "{conta_id}/monitoramentos/remover/{moni_id}", method = DELETE)
	public void removerMonitoramentos(
			@PathVariable("conta_id") Long contaId, 
			@PathVariable("moni_id") Long monitoramentoId) {
		
		servicoMonitoramento.excluirMonitoramento(monitoramentoId);
	}
	
	
	@RequestMapping(path = "{id}/monitoramentos", method = GET)
	public Set<Monitoramento> consultarMonitoramentos(@PathVariable("id") Long id) {
		Conta conta = servicoConta.consultarContaPorId(id);
		return conta.getMonitoramentos();
	}
	
	
	@RequestMapping(path = "{conta_id}/historico", method = GET)
	public Set<Negociacao> consultarHistorico(@PathVariable("conta_id") Long id) {
		Conta conta = servicoConta.consultarContaPorId(id);
		return conta.getNegociacoes();
	}

}
