package com.acoes.bolsa.servicos;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acoes.bolsa.modelo.Conta;
import com.acoes.bolsa.modelo.Empresa;
import com.acoes.bolsa.modelo.Monitoramento;
import com.acoes.bolsa.modelo.Negociacao;
import com.acoes.bolsa.modelo.TipoNegociacao;
import com.acoes.bolsa.repositorio.RepositorioNegociacao;

@Service
public class ServicoNegociacao {

	@Autowired
	private RepositorioNegociacao repoNegociacao;
	@Autowired
	private ServicoEmail servicoEmail;
	
	@Transactional
	public Negociacao realizarCompraAcoes(Monitoramento monitoramento, Empresa empresa, Conta conta) {
		Float acoes = conta.getSaldo() / empresa.getValorAcao();
		
		Negociacao negociacao = new Negociacao();
		negociacao.setData(new Date());
		negociacao.setConta(conta);
		negociacao.setNomeEmpresa(empresa.getNome());
		negociacao.setQuantidade(acoes);
		negociacao.setTipoNegociacao(TipoNegociacao.COMPRA);
		negociacao.setValorNegociado(empresa.getValorAcao());
		
		conta.setQuantidadeAcoes(acoes);
		conta.setSaldo(0f);
		
		negociacao = repoNegociacao.save(negociacao);
		
		String mailContent = 
				"Empresa: " + negociacao.getNomeEmpresa() + "\n" +
				"Valor de compra: $" + monitoramento.getPrecoCompra()  +"\n" +
				"Valor negociacao: $" + negociacao.getValorNegociado() + "\n";
		
		servicoEmail.enviarEmail(conta.getEmail(), "Compra de ações realizada", mailContent);
		
		return negociacao;
	}
	
	@Transactional
	public Negociacao realizarVendaAcoes(Monitoramento monitoramento, Empresa empresa, Conta conta) {
		
		Float quantidade = conta.getQuantidadeAcoes();
		Float valorAcao = empresa.getValorAcao();
		Float saldo = quantidade * valorAcao;
		
		Negociacao negociacao = new Negociacao();
		negociacao.setData(new Date());
		negociacao.setConta(conta);
		negociacao.setNomeEmpresa(empresa.getNome());
		negociacao.setQuantidade(quantidade);
		negociacao.setValorNegociado(valorAcao);
		negociacao.setTipoNegociacao(TipoNegociacao.VENDA);
		
		conta.setSaldo(saldo);
		
		negociacao = repoNegociacao.save(negociacao);
		
		String mailContent = 
				"Empresa: " + negociacao.getNomeEmpresa() + "\n" +
				"Valor de venda: $" + + monitoramento.getPrecoVenda() + "\n" +
				"Valor negociacao: $" + negociacao.getValorNegociado() + "\n";
		
		servicoEmail.enviarEmail(conta.getEmail(), "Venda de ações realizada", mailContent);
		
		return negociacao;
	}
	
	public List<Negociacao> consultarTodasNegociacoes() {
		return repoNegociacao.findAll();
	}
	
}
