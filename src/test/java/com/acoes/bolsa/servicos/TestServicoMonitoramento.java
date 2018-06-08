package com.acoes.bolsa.servicos;

import java.util.List;
import java.util.NoSuchElementException;
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
public class TestServicoMonitoramento {

	@Autowired
	ServicoMonitoramento servicoMonitoramento;
	@Autowired
	ServicoConta servicoConta;
	@Autowired
	ServicoNegociacao servicoNegociacoes;
	
	@Test
	public void testSalvarMonitoramento() {
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
		Monitoramento monitoramentoSalvo = servicoMonitoramento.consultarPorId(monitoramento.getId());
		
		Assert.assertEquals(monitoramentoSalvo.getId(), monitoramento.getId());
	}
	
	@Test
	public void testConsultarMonitoramentoPorId() {
		Empresa empresa = Empresa.getSoftExpertInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		conta = servicoConta.salvarConta(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setConta(conta);
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(8.4f);
		monitoramento.setPrecoVenda(12.3f);
		
		Monitoramento monitoramentoSalvo = servicoMonitoramento.salvarMonitoramento(conta.getId(), monitoramento);
		monitoramentoSalvo = servicoMonitoramento.consultarPorId(monitoramentoSalvo.getId());
		
		Assert.assertNotNull(monitoramentoSalvo);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testExcluirMonitoramento() {
		Empresa empresa = Empresa.getSoftExpertInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		conta = servicoConta.salvarConta(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setConta(conta);
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(9.8f);
		monitoramento.setPrecoVenda(14f);
		
		Monitoramento monitoramentoSalvo = servicoMonitoramento.salvarMonitoramento(conta.getId(), monitoramento);
		servicoMonitoramento.excluirMonitoramento(monitoramentoSalvo.getId());
		
		servicoMonitoramento.consultarPorId(monitoramentoSalvo.getId());
	}
	
	@Test
	public void testConsultarTodosMonitoramentos() {
		Empresa empresa = Empresa.getSoftExpertInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		conta = servicoConta.salvarConta(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setConta(conta);
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(8.5f);
		monitoramento.setPrecoVenda(11f);
		
		servicoMonitoramento.salvarMonitoramento(conta.getId(), monitoramento);
		
		List<Monitoramento> monitoramentos = servicoMonitoramento.consultarTodosMonitoramentos();
		
		Assert.assertFalse(monitoramentos.isEmpty());
	}
	
	@Test
	public void testAnalisarMudancaDePrecos() {
		Empresa empresa = Empresa.getSoftExpertInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		conta = servicoConta.salvarConta(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setConta(conta);
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(7.9f);
		monitoramento.setPrecoVenda(12f);
		
		servicoMonitoramento.salvarMonitoramento(conta.getId(), monitoramento);
		List<Monitoramento> monitoramentos = servicoMonitoramento.consultarTodosMonitoramentos();
		
		empresa.setValorAcao(6f);
		servicoMonitoramento.analisarMudancaDePreco(monitoramentos);
		
		Conta contaRelacionada = servicoConta.consultarContaPorId(conta.getId());
		
		Assert.assertFalse(contaRelacionada.getNegociacoes().isEmpty());
	}
	
}
