package com.acoes.bolsa.modelo;

import java.util.NoSuchElementException;
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
public class TestMonitoramento {

	@Autowired
	RepositorioMonitoramento repoMonitoramento;
	@Autowired
	RepositorioConta repoConta;
	@Autowired
	RepositorioNegociacao repoNegociacao;
	
	@Test
	public void testSalvarMonitoramento() {
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
		Monitoramento monitoramentoRecuperado = repoMonitoramento.findById(monitoramento.getId()).get();
		
		Assert.assertNotNull(monitoramentoRecuperado);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSalvarMonitoramentoSemEmpresa() {
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		
		conta = repoConta.save(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setConta(conta);
		monitoramento.setPrecoCompra(7.9f);
		monitoramento.setPrecoVenda(12f);
		
		monitoramento = repoMonitoramento.save(monitoramento);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSalvarMonitoramentoSemConta() {
		Empresa empresa = Empresa.getIntelInstance();
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(7.9f);
		monitoramento.setPrecoVenda(12f);
		
		monitoramento = repoMonitoramento.save(monitoramento);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSalvarMonitoramentoSemPrecoCompra() {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		
		conta = repoConta.save(conta);
	
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setConta(conta);
		monitoramento.setNomeEmpresa(empresa.getNome()); 		
		monitoramento.setPrecoVenda(12f);
		
		monitoramento = repoMonitoramento.save(monitoramento);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSalvarMonitoramentoSemPrecoVenda() {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		
		conta = repoConta.save(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setConta(conta);
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoVenda(12f);
		
		monitoramento = repoMonitoramento.save(monitoramento);
	}
	
	@Test
	public void testConsultarMonitoramentoPorId() {
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
		Monitoramento monitoramentoRecuperado = repoMonitoramento.findById(monitoramento.getId()).get();
		
		Assert.assertNotNull(monitoramentoRecuperado);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testExcluirMonitoramento() {
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
		
		repoMonitoramento.delete(monitoramento);
		
		repoMonitoramento.findById(monitoramento.getId()).get();
	}
	
}
