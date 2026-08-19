package com.Leazor.backend.controller;

import com.Leazor.backend.model.LeituraSensor;
import com.Leazor.backend.service.LeituraSensorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leituras")
public class LeituraSensorController {

    private final LeituraSensorService service;

    public LeituraSensorController(LeituraSensorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LeituraSensor> registrar(@RequestBody LeituraSensor leitura) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarLeitura(leitura));
    }

    @GetMapping("/sensor/{sensorId}")
    public ResponseEntity<List<LeituraSensor>> buscarHistorico(@PathVariable Long sensorId) {
        return ResponseEntity.ok(service.buscarHistoricoDoSensor(sensorId));
    }
}