package com.Leazor.backend.service;

import com.Leazor.backend.model.Dispositivo;
import com.Leazor.backend.repository.DispositivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DispositivoService {

    private final DispositivoRepository repository;

    // Injeção de dependência pelo construtor (melhor prática)
    public DispositivoService(DispositivoRepository repository) {
        this.repository = repository;
    }

    public Dispositivo cadastrar(Dispositivo dispositivo) {
        // Verifica se já existe um robô com esse identificador (ex: LEAZOR-01)
        Optional<Dispositivo> existente = repository.findByIdentificador(dispositivo.getIdentificador());
        if (existente.isPresent()) {
            throw new RuntimeException("Já existe um dispositivo com este identificador.");
        }
        return repository.save(dispositivo);
    }

    public List<Dispositivo> listarTodos() {
        return repository.findAll();
    }

    public Dispositivo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado."));
    }
}