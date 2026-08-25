package com.Leazor.backend.controller;

import com.Leazor.backend.dto.TelemetriaDTO;
import com.Leazor.backend.model.LeituraSensor;
import com.Leazor.backend.model.Sensor;
import com.Leazor.backend.service.LeituraSensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/telemetria")
@CrossOrigin(origins = "*") // Permite que o React Native acesse a API
public class TelemetriaController {

    private final LeituraSensorService leituraService;

    public TelemetriaController(LeituraSensorService leituraService) {
        this.leituraService = leituraService;
    }

    // === ATUALIZADO: Endpoint GET buscando do banco de dados ===
    @GetMapping
    public Map<String, Object> obterTelemetriaFrontEnd() {
        // Defina os IDs dos sensores conforme cadastrados no banco de dados
        Long ID_BATERIA = 1L;
        Long ID_ULTRASSONICO = 2L;

        Double valorBateria = null;
        Double valorDistancia = null;

        // 1. Busca a leitura mais recente da bateria
        List<LeituraSensor> leiturasBateria = leituraService.buscarHistoricoDoSensor(ID_BATERIA);
        if (!leiturasBateria.isEmpty()) {
            valorBateria = leiturasBateria.get(0).getValor(); // Pega a primeira da lista (mais recente)
        }

        // 2. Busca a leitura mais recente da distância
        List<LeituraSensor> leiturasDistancia = leituraService.buscarHistoricoDoSensor(ID_ULTRASSONICO);
        if (!leiturasDistancia.isEmpty()) {
            valorDistancia = leiturasDistancia.get(0).getValor(); // Pega a primeira da lista (mais recente)
        }

        // 3. Monta a resposta para o React Native
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("bateria", valorBateria);
        resposta.put("distancia", valorDistancia);

        return resposta;
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