package com.Leazor.backend.controller;

import com.Leazor.backend.model.Localizacao;
import com.Leazor.backend.service.LocalizacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/localizacoes")
public class LocalizacaoController {

    private final LocalizacaoService service;

    public LocalizacaoController(LocalizacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Localizacao> registrar(@RequestBody Localizacao localizacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarLocalizacao(localizacao));
    }

    @GetMapping
    public ResponseEntity<List<Localizacao>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }
}
