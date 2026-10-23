package com.Leazor.backend.controller;

import com.Leazor.backend.dto.ComandoDTO;
import com.Leazor.backend.model.Historico;
import com.Leazor.backend.repository.HistoricoRepository;
import com.Leazor.backend.service.IntegracaoPythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/comando")
@CrossOrigin(origins = "*")
public class ComandoController {

    @Autowired
    private IntegracaoPythonService integracaoPythonService;

    @Autowired
    private HistoricoRepository historicoRepository;

    @PostMapping
    public ResponseEntity<String> receberComando(@RequestBody ComandoDTO comando) {
        System.out.println("Joystick -> Esq: " + comando.getMotorEsquerdo() + "% | Dir: " + comando.getMotorDireito() + "%");
        return ResponseEntity.ok("Comando recebido com sucesso");
    }

    // Endpoint para receber comandos genéricos do aplicativo e repassar ao Python
    @PostMapping("/enviar")
    public ResponseEntity<Map<String, Object>> enviarComandoGenerico(@RequestBody Map<String, Object> payload) {
        if (payload != null && "emergencia".equalsIgnoreCase(String.valueOf(payload.get("comando")))) {
            registrarEventoEmergencia();
            Map<String, Object> resposta = integracaoPythonService.enviarEmergencia();
            return ResponseEntity.ok(resposta);
        }

        Map<String, Object> resposta = integracaoPythonService.enviarComando(payload);
        return ResponseEntity.ok(resposta);
    }

    // Endpoint dedicado para o botão de emergência
    @PostMapping("/emergencia")
    public ResponseEntity<Map<String, Object>> acionarEmergencia() {
        registrarEventoEmergencia();
        Map<String, Object> resposta = integracaoPythonService.enviarEmergencia();
        return ResponseEntity.ok(resposta);
    }

    // Método auxiliar para salvar o disparo de emergência no Banco de Dados
    private void registrarEventoEmergencia() {
        try {
            Historico evento = new Historico(
                    "EMERGENCIA_ACIONADA",
                    "Parada de emergência acionada pelo operador no aplicativo."
            );
            historicoRepository.save(evento);
        } catch (Exception e) {
            System.err.println("Erro ao registrar evento de emergência no histórico: " + e.getMessage());
        }
    }

    // Endpoint para alternar a tomada de decisão da IA (Navegação/Direção)
    @PostMapping("/ia-decisao")
    public ResponseEntity<Map<String, Object>> alternarDecisaoIA(@RequestBody Map<String, Object> payload) {
        Map<String, Object> resposta = integracaoPythonService.alternarDecisaoIA(payload);
        return ResponseEntity.ok(resposta);
    }

    // Endpoint para alternar o acionamento automático do corte pela IA
    @PostMapping("/ia-autocorte")
    public ResponseEntity<Map<String, Object>> alternarAutocorteIA(@RequestBody Map<String, Object> payload) {
        Map<String, Object> resposta = integracaoPythonService.alternarAutocorteIA(payload);
        return ResponseEntity.ok(resposta);
    }

    // Endpoint para obter o status da IA em tempo real
    @GetMapping("/ia-status")
    public ResponseEntity<Map<String, Object>> obterStatusIA() {
        Map<String, Object> status = integracaoPythonService.obterStatusIAEmTempoReal();
        return ResponseEntity.ok(status);
    }
}