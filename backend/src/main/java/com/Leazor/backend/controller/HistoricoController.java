package com.Leazor.backend.controller;

import com.Leazor.backend.model.Historico;
import com.Leazor.backend.repository.HistoricoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historico")
@CrossOrigin(origins = "*")
public class HistoricoController {

    private final HistoricoRepository repository;

    // Injeção de dependência via construtor (mesmo padrão dos seus outros controllers)
    public HistoricoController(HistoricoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Historico>> listarHistorico() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Historico> criarHistorico(@RequestBody Historico historico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(historico));
    }
}
