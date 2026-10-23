package com.Leazor.backend.service;

import com.Leazor.backend.model.TelemetriaIA;
import com.Leazor.backend.repository.TelemetriaIARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class IntegracaoPythonService {

    @Value("${python.api.url:http://100.94.182.54:5000}")
    private String pythonApiUrl;

    @Autowired
    private TelemetriaIARepository repository;

    private final RestTemplate restTemplate = new RestTemplate();

    // Roda a cada 5 segundos para salvar snapshot da IA no banco de dados local (H2)
    @Scheduled(fixedRate = 5000)
    public void monitorarStatusPython() {
        try {
            String url = pythonApiUrl + "/ia_status";
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response != null) {
                TelemetriaIA telemetria = new TelemetriaIA();
                telemetria.setEmergenciaAtiva((Boolean) response.getOrDefault("emergencia_ativa", false));
                telemetria.setLaminaAtiva((Boolean) response.getOrDefault("corte_permitido", false));

                repository.save(telemetria);
            }
        } catch (Exception e) {
            System.err.println("Leazor: Erro ao conectar com a API Python do Raspberry: " + e.getMessage());
        }
    }

    // Envia o comando de Emergência no formato exato esperado pela rota /emergencia do Flask
    public Map<String, Object> enviarEmergencia() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("acao", "ativar");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            String urlEmergencia = pythonApiUrl + "/emergencia";
            ResponseEntity<Map> response = restTemplate.postForEntity(urlEmergencia, request, Map.class);
            return response.getBody();
        } catch (Exception e) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("status", "erro");
            erro.put("mensagem", "Falha de emergência no Raspberry Pi: " + e.getMessage());
            return erro;
        }
    }

    // Repassa comandos genéricos para o Raspberry Pi (/comando)
    public Map<String, Object> enviarComando(Map<String, Object> payload) {
        try {
            String url = pythonApiUrl + "/comando";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

            return response.getBody();
        } catch (Exception e) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("status", "erro");
            erro.put("mensagem", "Falha de comunicação com o Raspberry Pi: " + e.getMessage());
            return erro;
        }
    }

    // Alterna a tomada de decisão da IA e/ou Autocorte no formato aceito pela rota /ia_decisao
    public Map<String, Object> alternarDecisaoIA(Map<String, Object> payload) {
        try {
            String url = pythonApiUrl + "/ia_decisao";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

            return response.getBody();
        } catch (Exception e) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("status", "erro");
            erro.put("mensagem", "Falha ao alterar decisão da IA: " + e.getMessage());
            return erro;
        }
    }

    // Método corrigido para alterar o autocorte utilizando a rota /ia_decisao real do Python
    public Map<String, Object> alternarAutocorteIA(Map<String, Object> payload) {
        return alternarDecisaoIA(payload);
    }

    // Busca status em tempo real da IA no Raspberry Pi (/ia_status)
    public Map<String, Object> obterStatusIAEmTempoReal() {
        try {
            return restTemplate.getForObject(pythonApiUrl + "/ia_status", Map.class);
        } catch (Exception e) {
            return new HashMap<>();
        }
    }
}