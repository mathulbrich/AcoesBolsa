package com.acoes.bolsa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.acoes.bolsa.modelo.Negociacao;

public interface RepositorioNegociacao extends JpaRepository<Negociacao, Long> {
	
}
