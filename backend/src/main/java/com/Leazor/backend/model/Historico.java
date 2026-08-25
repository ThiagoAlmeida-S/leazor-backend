package com.Leazor.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_historico")
@Data // O Lombok já cria os Getters e Setters

public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;    // Ex: "ALERTA" ou "CORTE"
    private String titulo;  // Ex: "Lâminas travadas"
    private String detalhe; // Ex: "Objeto rígido detectado"

    private LocalDateTime dataHora;

    @PrePersist
    public void prePersist() {
        this.dataHora = LocalDateTime.now();
    }
}
