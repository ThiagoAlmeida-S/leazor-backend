package com.Leazor.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_sensor")
@Data
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // Ex: "Ultrassônico Dianteiro"

    @Enumerated(EnumType.STRING)
    private TipoSensor tipo;

    private String unidadeMedida; // Ex: "cm", "%", "ºC"

    @ManyToOne
    @JoinColumn(name = "dispositivo_id")
    private Dispositivo dispositivo;
}
