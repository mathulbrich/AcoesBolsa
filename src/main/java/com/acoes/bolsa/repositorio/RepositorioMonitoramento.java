package com.acoes.bolsa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acoes.bolsa.modelo.Monitoramento;

@Repository
public interface RepositorioMonitoramento extends JpaRepository<Monitoramento, Long> {

}
