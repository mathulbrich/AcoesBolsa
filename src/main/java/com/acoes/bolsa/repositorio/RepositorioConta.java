package com.acoes.bolsa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.acoes.bolsa.modelo.Conta;

@Repository
public interface RepositorioConta extends JpaRepository<Conta, Long> {

}
