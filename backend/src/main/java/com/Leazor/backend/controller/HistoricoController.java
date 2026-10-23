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

    // Injeção de dependência via construtor
    public HistoricoController(HistoricoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Historico>> listarHistorico() {
        // Retorna a lista ordenada do evento mais recente para o mais antigo
        return ResponseEntity.ok(repository.findAllByOrderByIdDesc());
    }

    @PostMapping
    public ResponseEntity<Historico> criarHistorico(@RequestBody Historico historico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(historico));
    }

    // Endpoint para deletar um registro específico por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarHistoricoPorId(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint para apagar todo o histórico no banco de dados
    @DeleteMapping
    public ResponseEntity<Void> limparTodoHistorico() {
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}