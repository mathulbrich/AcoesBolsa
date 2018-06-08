package com.acoes.bolsa.servicos;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.acoes.bolsa.modelo.Conta;
import com.acoes.bolsa.modelo.Empresa;
import com.acoes.bolsa.modelo.Monitoramento;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServicoNegociacao {
	
	@Autowired
	ServicoNegociacao servicoNegociacao;
	@Autowired
	ServicoMonitoramento servicoMonitoramento;
	@Autowired
	ServicoConta servicoConta;
	
	@Test
	public void testRealizarCompraAcoes() {
		Empresa empresa = Empresa.getSoftExpertInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		conta = servicoConta.salvarConta(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setConta(conta);
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(8.9f);
		monitoramento.setPrecoVenda(14.5f);
		
		monitoramento = servicoMonitoramento.salvarMonitoramento(conta.getId(), monitoramento);
		
		servicoNegociacao.realizarCompraAcoes(monitoramento, empresa, conta);
		
		Conta contaRelacionada = servicoConta.consultarContaPorId(conta.getId());
		
		Assert.assertFalse(contaRelacionada.getNegociacoes().isEmpty());
	}
	
	@Test
	public void testRealizarVendaAcoes() {
		Empresa empresa = Empresa.getSoftExpertInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		conta = servicoConta.salvarConta(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setConta(conta);
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(8.9f);
		monitoramento.setPrecoVenda(14.5f);
		
		monitoramento = servicoMonitoramento.salvarMonitoramento(conta.getId(), monitoramento);
		
		servicoNegociacao.realizarCompraAcoes(monitoramento, empresa, conta);
		servicoNegociacao.realizarVendaAcoes(monitoramento, empresa, conta);
		
		Conta contaRelacionada = servicoConta.consultarContaPorId(conta.getId());
		
		Assert.assertEquals(contaRelacionada.getNegociacoes().size(), 2);
	}
	
}
