package com.acoes.bolsa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.acoes.bolsa.modelo.Conta;

public interface RepositorioConta extends JpaRepository<Conta, Long> {

}
