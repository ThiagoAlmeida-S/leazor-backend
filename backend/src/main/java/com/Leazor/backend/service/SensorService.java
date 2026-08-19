package com.Leazor.backend.service;

import com.Leazor.backend.model.Sensor;
import com.Leazor.backend.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

    private final SensorRepository repository;

    public SensorService(SensorRepository repository) {
        this.repository = repository;
    }

    public Sensor cadastrar(Sensor sensor) {
        return repository.save(sensor);
    }

    public List<Sensor> listarTodos() {
        return repository.findAll();
    }
}
