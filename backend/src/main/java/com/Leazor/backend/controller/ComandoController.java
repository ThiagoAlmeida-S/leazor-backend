package com.Leazor.backend.controller;

import com.Leazor.backend.dto.ComandoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comando")
@CrossOrigin(origins = "*")

public class ComandoController {
    @PostMapping
    public ResponseEntity<String> receberComando(@RequestBody ComandoDTO comando) {
        // Aqui o valor do Joystick chega no Backend!
        System.out.println("Joystick -> Esq: " + comando.getMotorEsquerdo() + "% | Dir: " + comando.getMotorDireito() + "%");

        // Futuramente: enviar esses valores via MQTT ou Serial para o ESP32/Arduino

        return ResponseEntity.ok("Comando recebido com sucesso");
    }
}
