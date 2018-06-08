package com.acoes.bolsa.modelo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import com.acoes.bolsa.repositorio.RepositorioConta;
import com.acoes.bolsa.repositorio.RepositorioMonitoramento;
import com.acoes.bolsa.repositorio.RepositorioNegociacao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestConta {
	
	@Autowired
	RepositorioConta repoConta;
	@Autowired
	RepositorioMonitoramento repoMonitoramento;
	@Autowired
	RepositorioNegociacao repoNegociacao;
	
	@Test
	public void testSalvarConta() {
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		
		conta  = repoConta.save(conta);
		Conta contaSalva = repoConta.findById(conta.getId()).get();
		
		Assert.assertEquals(conta.getId(), contaSalva.getId());
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSalvarContaSemEmail() {
		Conta conta = new Conta();
		conta.setSaldo(10000f);
		
		repoConta.save(conta);
	}
	
	@Test
	public void testSalvarIdGerado() {
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		
		conta = repoConta.save(conta);
		
		Assert.assertNotNull(conta.getId());
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSalvarContaSemSaldo() {
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		
		repoConta.save(conta);
	}
	
	@Test
	public void testConsultarComId() {
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		
		conta = repoConta.save(conta);
		
		Conta contaBuscada = repoConta.findById(conta.getId()).get();
		
		Assert.assertNotNull(contaBuscada);
	}
	
	@Test
	public void testConsultarMonitoramentoPorConta() {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		
		conta = repoConta.save(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setConta(conta);
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(7.9f);
		monitoramento.setPrecoVenda(12f);
		
		monitoramento = repoMonitoramento.save(monitoramento);
		
		Monitoramento monitoramentoSalvo = repoMonitoramento.findById(monitoramento.getId()).get();
		
		Assert.assertNotNull(monitoramentoSalvo);
	}
	
	@Test
	public void testSalvarMonitoramentoNaConta() {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		
		conta = repoConta.save(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setConta(conta);
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(7.9f);
		monitoramento.setPrecoVenda(12f);
		
		monitoramento = repoMonitoramento.save(monitoramento);
		
		Assert.assertEquals(conta.getId(), monitoramento.getConta().getId());
	}
	
}
