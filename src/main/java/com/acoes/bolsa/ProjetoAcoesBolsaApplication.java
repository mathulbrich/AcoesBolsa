package com.acoes.bolsa;

import java.util.concurrent.Executor;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import com.acoes.bolsa.modelo.Conta;
import com.acoes.bolsa.modelo.Empresa;
import com.acoes.bolsa.modelo.Monitoramento;
import com.acoes.bolsa.servicos.ServicoConta;
import com.acoes.bolsa.servicos.ServicoMonitoramento;

@SpringBootApplication
@EnableAsync
public class ProjetoAcoesBolsaApplication {

	@Autowired
	private ServicoConta servicoConta;
	@Autowired
	private ServicoMonitoramento servicoMonitoramento;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoAcoesBolsaApplication.class, args);
	}
	
	@PostConstruct
	public void inserirDadosIniciais() {
		Empresa empresa = Empresa.getIntelInstance();
		
		Conta conta = new Conta();
		conta.setEmail("mathulbrich@gmail.com");
		conta.setSaldo(10000f);
		conta = servicoConta.salvarConta(conta);
		
		Monitoramento monitoramento = new Monitoramento();
		monitoramento.setConta(conta);
		monitoramento.setNomeEmpresa(empresa.getNome());
		monitoramento.setPrecoCompra(10.3f);
		monitoramento.setPrecoVenda(10.8f);
		
		monitoramento = servicoMonitoramento.salvarMonitoramento(conta.getId(), monitoramento);
	}
	
	@Bean
	public Executor executorAssincrono() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(3);
		executor.setMaxPoolSize(5);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("springasync");
		executor.initialize();
		return executor;
	}
	
}
