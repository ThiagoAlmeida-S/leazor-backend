package com.Leazor.backend.controller;

import com.Leazor.backend.model.Dispositivo;
import com.Leazor.backend.service.DispositivoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoController {

    private final DispositivoService service;

    public DispositivoController(DispositivoService service) {
        this.service = service;
    }

    // Endpoint para listar todos os robôs
    @GetMapping
    public ResponseEntity<List<Dispositivo>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Endpoint para cadastrar um novo robô
    @PostMapping
    public ResponseEntity<Dispositivo> cadastrar(@RequestBody Dispositivo dispositivo) {
        Dispositivo novoDispositivo = service.cadastrar(dispositivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDispositivo);
    }
}