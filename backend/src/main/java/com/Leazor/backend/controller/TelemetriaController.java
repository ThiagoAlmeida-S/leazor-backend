package com.Leazor.backend.controller;

import com.Leazor.backend.dto.TelemetriaDTO;
import com.Leazor.backend.model.LeituraSensor;
import com.Leazor.backend.model.Sensor;
import com.Leazor.backend.service.LeituraSensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/telemetria")
@CrossOrigin(origins = "*") // Permite que o React Native acesse a API
public class TelemetriaController {

    private final LeituraSensorService leituraService;

    public TelemetriaController(LeituraSensorService leituraService) {
        this.leituraService = leituraService;
    }

    // === NOVO: Endpoint GET para o aplicativo React Native ===
    @GetMapping
    public Map<String, Object> obterTelemetriaFrontEnd() {
        // Por enquanto retornamos valores fixos para testar a tela.
        // Futuramente, podemos usar o 'leituraService' para buscar a última leitura no banco!
        return Map.of(
                "bateria", 88,
                "distancia", 15.4
        );
    }

    // === SEU CÓDIGO ORIGINAL CONTINUA AQUI ===
    @PostMapping
    public ResponseEntity<String> receberTelemetria(@RequestBody TelemetriaDTO dto) {
        try {
            LeituraSensor leitura = new LeituraSensor();
            leitura.setValor(dto.getValor());

            Sensor sensor = new Sensor();
            sensor.setId(dto.getSensorId());
            leitura.setSensor(sensor);

            leituraService.registrarLeitura(leitura);

            return ResponseEntity.ok("Leitura salva com sucesso via HTTP!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar leitura: " + e.getMessage());
        }
    }
}