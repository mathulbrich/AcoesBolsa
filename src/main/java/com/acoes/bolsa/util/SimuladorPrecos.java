package com.acoes.bolsa.util;

import java.util.Random;

public class SimuladorPrecos {
	
	public static float gerarPrecoAleatorio() {
		Random random = new Random();
		float variacao = random.nextFloat();
		return 10 + variacao;
	}
	
}
