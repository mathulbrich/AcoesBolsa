package com.acoes.bolsa.util;

import java.util.List;
import com.acoes.bolsa.modelo.Conta;
import com.acoes.bolsa.modelo.Negociacao;

public class EmissorRelatorios {
	
	public static void emitirRelatorioNegociacoes(List<Conta> contas, List<Negociacao> negociacoes) {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("                            RELATORIO                              ");
		
		for(Conta c : contas) {
			System.out.println("-------------------------------------------------------------------");
			System.out.println("CONTA #" + c.getId());
			System.out.println("-------------------------------------------------------------------");
			
			negociacoes.forEach((n) -> {
				if(c.getId() == n.getConta().getId()) {
					System.out.println("ID: " + n.getId());
					System.out.println("EMPRESA: " + n.getNomeEmpresa());
					System.out.println("TIPO DE NEGOCIACAO: " + n.getTipoNegociacao());
					System.out.println("VALOR NEGOCIADO: $" + n.getValorNegociado());
					System.out.println("QUANTIDADE: " + n.getQuantidade());
					System.out.println("DATA: " + n.getData());
					System.out.println();
				}
			});
			
			System.out.println("SALDO FINAL: $" + c.getSaldo());
		}
		
		System.out.println("-------------------------------------------------------------------");
	}
	
}
