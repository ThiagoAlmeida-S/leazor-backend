package com.Leazor.backend.service;

import com.Leazor.backend.model.Historico;
import com.Leazor.backend.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    public List<Historico> listarTodos() {
        return historicoRepository.findAll();
    }

    public Historico salvarEvento(Historico historico) {
        return historicoRepository.save(historico);
    }
}