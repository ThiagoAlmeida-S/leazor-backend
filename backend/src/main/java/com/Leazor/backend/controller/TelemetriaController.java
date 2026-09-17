package com.Leazor.backend.controller;

import com.Leazor.backend.dto.TelemetriaDTO;
import com.Leazor.backend.dto.TelemetriaIADTO;
import com.Leazor.backend.model.Historico;
import com.Leazor.backend.model.LeituraSensor;
import com.Leazor.backend.model.Sensor;
import com.Leazor.backend.repository.HistoricoRepository;
import com.Leazor.backend.service.LeituraSensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/telemetria")
@CrossOrigin(origins = "*")
public class TelemetriaController {

    private final LeituraSensorService leituraService;
    private final HistoricoRepository HistoricoRepository; // Injeção do repositório de histórico
    private TelemetriaIADTO ultimoStatusIA = new TelemetriaIADTO();

    // Construtor atualizado com as duas dependências
    public TelemetriaController(LeituraSensorService leituraService, HistoricoRepository eventoRepository) {
        this.leituraService = leituraService;
        this.HistoricoRepository = eventoRepository;
    }

    @GetMapping
    public Map<String, Object> obterTelemetriaFrontEnd() {
        Long ID_BATERIA = 1L;
        Long ID_ULTRASSONICO = 2L;
        Long ID_ORIENTACAO = 3L;
        Long ID_TEMPERATURA = 4L;

        Map<String, Object> resposta = new HashMap<>();

        resposta.put("bateria", buscarUltimoValor(ID_BATERIA));
        resposta.put("distancia", buscarUltimoValor(ID_ULTRASSONICO));
        resposta.put("inclinacao", buscarUltimoValor(ID_ORIENTACAO));
        resposta.put("temperatura", buscarUltimoValor(ID_TEMPERATURA));
        resposta.put("iaStatus", ultimoStatusIA);

        return resposta;
    }

    private Double buscarUltimoValor(Long sensorId) {
        List<LeituraSensor> leituras = leituraService.buscarHistoricoDoSensor(sensorId);
        return leituras.isEmpty() ? null : leituras.get(0).getValor();
    }

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

    // ONDE O CÓDIGO DA IA E DO HISTÓRICO É EXECUTADO:
    @PostMapping("/ia")
    public ResponseEntity<String> receberTelemetriaIA(@RequestBody TelemetriaIADTO dto) {
        this.ultimoStatusIA = dto;

        // Regra de negócio: Salva evento no banco se um obstáculo for detectado
        if (dto.getQtdObstaculos() != null && dto.getQtdObstaculos() > 0) {
            Historico evento = new Historico(
                    "OBSTACULO_DETECTADO",
                    "Obstáculo identificado pela visão computacional (" + dto.getClasseDetectada() + ")"
            );
            HistoricoRepository.save(evento);
        }

        return ResponseEntity.ok("Status da IA e eventos salvos com sucesso!");
    }
}