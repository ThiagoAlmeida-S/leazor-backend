package com.Leazor.backend.controller;


import com.Leazor.backend.model.Sensor;
import com.Leazor.backend.service.SensorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensores")
public class SensorController {

    private final SensorService service;

    public SensorController(SensorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Sensor> cadastrar(@RequestBody Sensor sensor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(sensor));
    }

    @GetMapping
    public ResponseEntity<List<Sensor>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
