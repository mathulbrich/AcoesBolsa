package com.acoes.bolsa.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acoes.bolsa.modelo.Conta;
import com.acoes.bolsa.modelo.Empresa;
import com.acoes.bolsa.modelo.Monitoramento;
import com.acoes.bolsa.repositorio.RepositorioConta;
import com.acoes.bolsa.repositorio.RepositorioMonitoramento;

@Service
public class ServicoMonitoramento {

	@Autowired
	private ServicoNegociacao servicoNegociacao;
	@Autowired
	private RepositorioConta repoConta;
	@Autowired
	private RepositorioMonitoramento repoMonitoramento;

	@Transactional
	public Monitoramento salvarMonitoramento(Long contaId, Monitoramento monitoramento) {
		Conta conta = repoConta.findById(contaId).get();
		monitoramento.setConta(conta);
		
		monitoramento = repoMonitoramento.save(monitoramento);
		return monitoramento;
	}

	public Monitoramento consultarPorId(Long id) {
		return repoMonitoramento.findById(id).get();
	}

	@Transactional
	public void excluirMonitoramento(Long id) {
		repoMonitoramento.deleteById(id);
	}
	
	public List<Monitoramento> consultarTodosMonitoramentos() {
		return repoMonitoramento.findAll();
	}
	
	@Transactional
	public void analisarMudancaDePreco(List<Monitoramento> monitoramentos) {
		
		for(Monitoramento m : monitoramentos) {
			Empresa empresa = Empresa.getInstancePorNome(m.getNomeEmpresa());
			
			if(m.getConta().getSaldo() > 0) {
				if(m.getPrecoCompra() >= empresa.getValorAcao()) {
					servicoNegociacao.realizarCompraAcoes(m, empresa, m.getConta());
				}
			}
			
			else if(m.getConta().getQuantidadeAcoes() > 0) {
				if(m.getPrecoVenda() <= empresa.getValorAcao()) {
					servicoNegociacao.realizarVendaAcoes(m, empresa, m.getConta());
				}
			}
			
		}
		
	}

}
