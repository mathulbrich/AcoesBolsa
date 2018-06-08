package com.acoes.bolsa.servicos;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.acoes.bolsa.modelo.Conta;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServicoConta {
	
	@Autowired
	ServicoConta servicoConta;
	
	@Test
	public void testSalvarConta() {
		Conta conta = new Conta();
		conta.setEmail("mathulbrich@gmail.com");
		conta.setSaldo(10000f);
		
		conta = servicoConta.salvarConta(conta);
		Conta contaBuscada = servicoConta.consultarContaPorId(conta.getId());
		
		Assert.assertEquals(conta.getId(), contaBuscada.getId());
	}
	
	@Test
	public void testConsultarContaPorId() {
		Conta conta = new Conta();
		conta.setEmail("mathulbrich@gmail.com");
		conta.setSaldo(10000f);

		conta = servicoConta.salvarConta(conta);
		Conta contaBuscada = servicoConta.consultarContaPorId(conta.getId());
		
		Assert.assertNotNull(contaBuscada);
	}
	
	@Test
	public void testConsultarTodasContas() {
		Conta conta = new Conta();
		conta.setEmail("mathulbrich@gmail.com");
		conta.setSaldo(10000f);
	
		conta = servicoConta.salvarConta(conta);
		
		List<Conta> contas = servicoConta.consultarTodasContas();
		
		Assert.assertFalse(contas.isEmpty());
	}
	
}
