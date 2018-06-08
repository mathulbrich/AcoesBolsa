package com.acoes.bolsa.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ServicoEmail {
	
	@Autowired
	private JavaMailSender sender;
	
	@Async
	public void enviarEmail(String receptor, String assunto, String conteudo) {
		SimpleMailMessage mensagem = new SimpleMailMessage();
		mensagem.setTo(receptor);
		mensagem.setSubject(assunto);
		mensagem.setText(conteudo);
		
		sender.send(mensagem);
	}
	
}
