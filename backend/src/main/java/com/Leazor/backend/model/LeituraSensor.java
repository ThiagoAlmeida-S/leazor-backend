package com.Leazor.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_leitura_sensor")
@Data
public class LeituraSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double valor; // Armazena a medição (ex: 42.5 para temp, 15.0 para distância, 88.0 para bateria)

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    @PrePersist
    public void prePersist() {
        this.dataHora = LocalDateTime.now();
    }
}
