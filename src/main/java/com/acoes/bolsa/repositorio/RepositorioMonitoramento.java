package com.acoes.bolsa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.acoes.bolsa.modelo.Monitoramento;

public interface RepositorioMonitoramento extends JpaRepository<Monitoramento, Long> {

}
