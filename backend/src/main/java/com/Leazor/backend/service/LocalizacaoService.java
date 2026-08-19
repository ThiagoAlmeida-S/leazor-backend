package com.Leazor.backend.service;

import com.Leazor.backend.model.Localizacao;
import com.Leazor.backend.repository.LocalizacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository repository;

    public LocalizacaoService(LocalizacaoRepository repository) {
        this.repository = repository;
    }

    public Localizacao registrarLocalizacao(Localizacao localizacao) {
        return repository.save(localizacao);
    }

    public List<Localizacao> listarTodas() {
        return repository.findAll();
    }
}