package com.acoes.bolsa.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.acoes.bolsa.modelo.Conta;
import com.acoes.bolsa.modelo.Empresa;
import com.acoes.bolsa.modelo.Monitoramento;
import com.acoes.bolsa.modelo.Negociacao;
import com.acoes.bolsa.servicos.ServicoConta;
import com.acoes.bolsa.servicos.ServicoMonitoramento;
import com.acoes.bolsa.servicos.ServicoNegociacao;
import com.acoes.bolsa.util.EmissorRelatorios;
import com.acoes.bolsa.util.SimuladorPrecos;

@RestController
@RequestMapping("/transacoes")
public class TransacoesControlador {
	
	@Autowired
	private ServicoMonitoramento servicoMonitoramento;
	@Autowired
	private ServicoConta servicoConta;
	@Autowired
	private ServicoNegociacao servicoNegociacoes;
	
	@PostMapping("iniciar")
	public void inicarTransacoes() {
		
		Empresa empresa = Empresa.getIntelInstance();
		List<Monitoramento> monitoramentos = servicoMonitoramento.consultarTodosMonitoramentos();
		List<Conta> contas = servicoConta.consultarTodasContas();
		
		for(int i = 0; i < 100; i++) {
			
			empresa.setValorAcao(SimuladorPrecos.gerarPrecoAleatorio());
			servicoMonitoramento.analisarMudancaDePreco(monitoramentos);
			
			try {
				Thread.sleep(5000);
			} 
			
			catch (InterruptedException e) {
				System.out.println("A operação de mudança do valor da ação foi interrompida.");
				e.printStackTrace();
			}
		}
		
		List<Negociacao> negociacoes = servicoNegociacoes.consultarTodasNegociacoes();
		
		EmissorRelatorios.emitirRelatorioNegociacoes(contas, negociacoes);
		
	}
	
}
