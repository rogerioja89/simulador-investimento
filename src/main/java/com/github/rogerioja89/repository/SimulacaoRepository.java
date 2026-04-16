package com.github.rogerioja89.repository;

import com.github.rogerioja89.entity.Simulacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class SimulacaoRepository implements PanacheRepository<Simulacao> {

    public List<Simulacao> listarPorClienteId(Long clienteId) {
        return find("clienteId = ?1 order by dataSimulacao desc", clienteId).list();
    }
}


