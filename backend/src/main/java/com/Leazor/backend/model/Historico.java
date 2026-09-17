package com.Leazor.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_historico_eventos")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoEvento; // "OBSTACULO_DETECTADO", "GRAMA_ALTA", "EMERGENCIA"
    private String descricao;
    private LocalDateTime dataHora;

    public Historico() {
        this.dataHora = LocalDateTime.now();
    }

    public Historico(String tipoEvento, String descricao) {
        this.tipoEvento = tipoEvento;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
}