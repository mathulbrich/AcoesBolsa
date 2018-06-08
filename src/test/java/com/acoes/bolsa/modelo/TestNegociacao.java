package com.acoes.bolsa.modelo;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import com.acoes.bolsa.repositorio.RepositorioConta;
import com.acoes.bolsa.repositorio.RepositorioNegociacao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestNegociacao {
	
	@Autowired
	RepositorioNegociacao repoNegociacao;
	@Autowired
	RepositorioConta repoConta;
	
	@Test
	public void testSalvarNegociacao() {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		
		conta = repoConta.save(conta);
		
		Negociacao negociacao = new Negociacao();
		negociacao.setConta(conta); 
		negociacao.setData(new Date());
		negociacao.setNomeEmpresa(empresa.getNome());
		negociacao.setQuantidade(400f);
		negociacao.setTipoNegociacao(TipoNegociacao.COMPRA);
		negociacao.setValorNegociado(6.5f);
		
		negociacao = repoNegociacao.save(negociacao);
		
		Negociacao negociacaoRecuperada = repoNegociacao.findById(negociacao.getId()).get();
		
		Assert.assertNotNull(negociacaoRecuperada);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSalvarNegociacaoSemValor() {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		
		conta = repoConta.save(conta);
		
		Negociacao negociacao = new Negociacao();
		negociacao.setConta(conta); 
		negociacao.setData(new Date());
		negociacao.setNomeEmpresa(empresa.getNome());
		negociacao.setQuantidade(400f);
		negociacao.setTipoNegociacao(TipoNegociacao.COMPRA);
		
		negociacao = repoNegociacao.save(negociacao);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSalvarNegociacaoSemTipo() {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		
		conta = repoConta.save(conta);
		
		Negociacao negociacao = new Negociacao();
		negociacao.setConta(conta); 
		negociacao.setData(new Date());
		negociacao.setNomeEmpresa(empresa.getNome());
		negociacao.setQuantidade(400f);
		negociacao.setValorNegociado(6.5f);
		
		negociacao = repoNegociacao.save(negociacao);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSalvarNegociacaoSemData() {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		
		conta = repoConta.save(conta);
		
		Negociacao negociacao = new Negociacao();
		negociacao.setConta(conta); 
		negociacao.setNomeEmpresa(empresa.getNome());
		negociacao.setQuantidade(400f);
		negociacao.setTipoNegociacao(TipoNegociacao.COMPRA);
		negociacao.setValorNegociado(6.5f);
		
		negociacao = repoNegociacao.save(negociacao);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSalvarNegociacaoSemConta() {
		Empresa empresa = Empresa.getIntelInstance();
		
		Negociacao negociacao = new Negociacao();
		negociacao.setData(new Date());
		negociacao.setNomeEmpresa(empresa.getNome());
		negociacao.setQuantidade(400f);
		negociacao.setTipoNegociacao(TipoNegociacao.COMPRA);
		negociacao.setValorNegociado(6.5f);
		
		negociacao = repoNegociacao.save(negociacao);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSalvarNegociacaoSemEmpresa() {
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		
		conta = repoConta.save(conta);
		
		Negociacao negociacao = new Negociacao();
		negociacao.setConta(conta); 
		negociacao.setData(new Date());
		negociacao.setQuantidade(400f);
		negociacao.setTipoNegociacao(TipoNegociacao.COMPRA);
		negociacao.setValorNegociado(6.5f);
		
		negociacao = repoNegociacao.save(negociacao);
	}
	
	@Test
	public void testConsultarNegociacaoPorId() {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("testSalvar@exemplo.com");
		conta.setSaldo(10000f);
		
		conta = repoConta.save(conta);
		
		Negociacao negociacao = new Negociacao();
		negociacao.setConta(conta); 
		negociacao.setData(new Date());
		negociacao.setNomeEmpresa(empresa.getNome());
		negociacao.setQuantidade(400f);
		negociacao.setTipoNegociacao(TipoNegociacao.COMPRA);
		negociacao.setValorNegociado(6.5f);
		
		negociacao = repoNegociacao.save(negociacao);
		Negociacao negociacaoRecuperado = repoNegociacao.findById(negociacao.getId()).get();
		
		Assert.assertNotNull(negociacaoRecuperado);
	}
	
}
