package com.Leazor.backend.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class MqttTestListener {

    @Value("${mqtt.broker.url}")
    private String brokerUrl;

    @Value("${mqtt.client.id}")
    private String clientId;

    private static final String TOPICO_TESTE = "leazor";

    @PostConstruct
    public void iniciar() {
        try {
            MqttClient client = new MqttClient(brokerUrl, clientId, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Conexão com o broker MQTT perdida: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    System.out.println("Mensagem recebida [" + topic + "]: " + new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {}
            });

            client.connect(options);
            client.subscribe(TOPICO_TESTE);
            System.out.println("Conectado ao broker MQTT, escutando: " + TOPICO_TESTE);

        } catch (MqttException e) {
            System.out.println("Erro ao conectar no broker MQTT: " + e.getMessage());
        }
    }
}
