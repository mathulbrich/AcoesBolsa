package com.acoes.bolsa.controlador;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.acoes.bolsa.modelo.Conta;
import com.acoes.bolsa.modelo.Empresa;
import com.acoes.bolsa.modelo.Monitoramento;
import com.acoes.bolsa.modelo.Negociacao;
import com.acoes.bolsa.servicos.ServicoConta;
import com.acoes.bolsa.servicos.ServicoMonitoramento;
import com.acoes.bolsa.servicos.ServicoNegociacao;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestContaControlador {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ContaControlador contaControlador;
	@Autowired
	private ServicoConta servicoConta;
	@Autowired
	private ServicoMonitoramento servicoMonitoramento;
	@Autowired
	private ServicoNegociacao servicoNegociacao;
	
	@Test
	public void testCadastrarConta() throws Exception {
		Conta conta = new Conta();
		conta.setEmail("mathulbrich@gmail.com");
		conta.setSaldo(5000f);
		
		Gson json = new Gson();
		
		doNothing().when(contaControlador).cadastrarConta(conta);
		
		mockMvc.perform(post("/conta/cadastrar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.toJson(conta)))
			.andExpect(status().isOk());
		
		verify(contaControlador, times(1)).cadastrarConta(conta);
	}
	
	@Test
	public void testConsultarConta() throws Exception {
		Conta conta = new Conta();
		conta.setEmail("mathulbrich@gmail.com");
		conta.setSaldo(5000f);
		conta = servicoConta.salvarConta(conta);
		
		when(contaControlador.consultarConta(conta.getId())).thenReturn(conta);
		
		mockMvc.perform(get("/conta/{id}", conta.getId()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(conta.getId()))
			.andExpect(jsonPath("$.email").value(conta.getEmail()))
			.andExpect(jsonPath("$.saldo").value(conta.getSaldo()));
		
		verify(contaControlador, times(1)).consultarConta(conta.getId());
	}
	
	@Test
	public void testCadastrarMonitoramento() throws Exception {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("mathulbrich@gmail.com");
		conta.setSaldo(5000f);
		conta = servicoConta.salvarConta(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(10f);
		monitoramento.setPrecoVenda(11f);
		
		Gson json = new Gson();
		
		doNothing().when(contaControlador).cadastrarMonitoramento(conta.getId(), monitoramento);
		
		mockMvc.perform(post("/conta/{id}/monitoramentos/cadastrar", conta.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.toJson(monitoramento)))
			.andExpect(status().isOk());
		
		verify(contaControlador, times(1)).cadastrarMonitoramento(conta.getId(), monitoramento);
	}
	
	@Test
	public void testAtualizarMonitoramento() throws Exception {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("mathulbrich@gmail.com");
		conta.setSaldo(5000f);
		conta = servicoConta.salvarConta(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(10f);
		monitoramento.setPrecoVenda(11f);
		monitoramento = servicoMonitoramento.salvarMonitoramento(conta.getId(), monitoramento);
		
		monitoramento.setPrecoCompra(8f);
		
		Gson json = new Gson();
		
		doNothing().when(contaControlador).atualizarMonitoramento(conta.getId(), monitoramento);
		
		mockMvc.perform(put("/conta/{id}/monitoramentos/atualizar", conta.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.toJson(monitoramento)))
			.andExpect(status().isOk());
		
		verify(contaControlador, times(1)).atualizarMonitoramento(conta.getId(), monitoramento);
	}
	
	@Test
	public void testRemoverMonitoramentos() throws Exception {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("mathulbrich@gmail.com");
		conta.setSaldo(5000f);
		conta = servicoConta.salvarConta(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(10f);
		monitoramento.setPrecoVenda(11f);
		monitoramento = servicoMonitoramento.salvarMonitoramento(conta.getId(), monitoramento);
		
		doNothing().when(contaControlador).removerMonitoramentos(conta.getId(), monitoramento.getId());
		
		mockMvc.perform(delete("/conta/{id}/monitoramentos/remover/{c_id}", conta.getId(), monitoramento.getId()))
			.andExpect(status().isOk());
		
		verify(contaControlador, times(1)).removerMonitoramentos(conta.getId(), monitoramento.getId());
	}
	
	@Test
	public void testConsultarMonitoramentos() throws Exception {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("mathulbrich@gmail.com");
		conta.setSaldo(2000f);
		conta = servicoConta.salvarConta(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(10f);
		monitoramento.setPrecoVenda(11f);
		monitoramento = servicoMonitoramento.salvarMonitoramento(conta.getId(), monitoramento);
		
		Set<Monitoramento> monitoramentos = new HashSet<Monitoramento>();
		monitoramentos.add(monitoramento);
		
		when(contaControlador.consultarMonitoramentos(conta.getId())).thenReturn(monitoramentos);
		
		mockMvc.perform(get("/conta/{id}/monitoramentos", conta.getId()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].id").value(monitoramento.getId()))
			.andExpect(jsonPath("$[0].precoCompra").value(monitoramento.getPrecoCompra()))
			.andExpect(jsonPath("$[0].precoVenda").value(monitoramento.getPrecoVenda()))
			.andExpect(jsonPath("$[0].nomeEmpresa").value(monitoramento.getNomeEmpresa()));
		
		verify(contaControlador, times(1)).consultarMonitoramentos(conta.getId());
	}
	
	@Test
	public void testConsultarHistorico() throws Exception {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("mathulbrich@gmail.com");
		conta.setSaldo(2000f);
		conta = servicoConta.salvarConta(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(10f);
		monitoramento.setPrecoVenda(11f);
		monitoramento = servicoMonitoramento.salvarMonitoramento(conta.getId(), monitoramento);
		
		Negociacao negociacao = servicoNegociacao.realizarCompraAcoes(monitoramento, empresa, conta);
		
		Set<Negociacao> negociacoes = new HashSet<>();
		negociacoes.add(negociacao);
		
		when(contaControlador.consultarHistorico(conta.getId())).thenReturn(negociacoes);
		
		mockMvc.perform(get("/conta/{id}/historico", conta.getId()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].id").value(negociacao.getId()))
			.andExpect(jsonPath("$[0].nomeEmpresa").value(negociacao.getNomeEmpresa()))
			.andExpect(jsonPath("$[0].quantidade").value(negociacao.getQuantidade()))
			.andExpect(jsonPath("$[0].valorNegociado").value(negociacao.getValorNegociado()));
		
		verify(contaControlador, times(1)).consultarHistorico(conta.getId());
	}
	
}
