package com.Leazor.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_telemetria_ia")
public class TelemetriaIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    @Column(name = "emergencia_ativa")
    private Boolean emergenciaAtiva;

    @Column(name = "lamina_ativa")
    private Boolean laminaAtiva;

    // Construtores
    public TelemetriaIA() {
        this.dataRegistro = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDateTime dataRegistro) { this.dataRegistro = dataRegistro; }
    public Boolean getEmergenciaAtiva() { return emergenciaAtiva; }
    public void setEmergenciaAtiva(Boolean emergenciaAtiva) { this.emergenciaAtiva = emergenciaAtiva; }
    public Boolean getLaminaAtiva() { return laminaAtiva; }
    public void setLaminaAtiva(Boolean laminaAtiva) { this.laminaAtiva = laminaAtiva; }
}
