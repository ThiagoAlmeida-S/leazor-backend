package com.Leazor.backend.service;

import com.Leazor.backend.model.LeituraSensor;
import com.Leazor.backend.repository.LeituraSensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeituraSensorService {

    private final LeituraSensorRepository repository;

    public LeituraSensorService(LeituraSensorRepository repository) {
        this.repository = repository;
    }

    public LeituraSensor registrarLeitura(LeituraSensor leitura) {
        return repository.save(leitura);
    }

    public List<LeituraSensor> buscarHistoricoDoSensor(Long sensorId) {
        // Usa o método que criamos no repository para trazer as mais recentes primeiro
        return repository.findBySensorIdOrderByDataHoraDesc(sensorId);
    }
}