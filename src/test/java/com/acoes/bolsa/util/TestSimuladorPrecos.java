package com.acoes.bolsa.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.acoes.bolsa.util.SimuladorPrecos;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSimuladorPrecos {

	@Test
	public void testGerarPrecosAlteatorios() {
		float preco = SimuladorPrecos.gerarPrecoAleatorio();
		Assert.assertTrue(preco >= 10 && preco <= 11);
	}
	
}
