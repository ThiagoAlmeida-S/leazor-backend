package com.Leazor.backend.controller;



import com.Leazor.backend.dto.TelemetriaDTO;
import com.Leazor.backend.model.LeituraSensor;
import com.Leazor.backend.model.Sensor;
import com.Leazor.backend.service.LeituraSensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/telemetria")
public class TelemetriaController {

    private final LeituraSensorService leituraService;

    public TelemetriaController(LeituraSensorService leituraService) {
        this.leituraService = leituraService;
    }

    // O método POST vai receber o JSON do hardware
    @PostMapping
    public ResponseEntity<String> receberTelemetria(@RequestBody TelemetriaDTO dto) {
        try {
            LeituraSensor leitura = new LeituraSensor();
            leitura.setValor(dto.getValor());

            Sensor sensor = new Sensor();
            sensor.setId(dto.getSensorId());
            leitura.setSensor(sensor);

            leituraService.registrarLeitura(leitura);

            // Retorna status 200 OK para o hardware
            return ResponseEntity.ok("Leitura salva com sucesso via HTTP!");

        } catch (Exception e) {
            // Se o sensor não existir (Erro 547) ou der problema, retorna status 400
            return ResponseEntity.badRequest().body("Erro ao salvar leitura: " + e.getMessage());
        }
    }
}