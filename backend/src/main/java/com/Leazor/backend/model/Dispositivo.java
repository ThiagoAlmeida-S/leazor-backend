package com.Leazor.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_dispositivo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identificador; // Ex: "LEAZOR-01"

    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDispositivo status;

    private LocalDateTime dataCadastro;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        if (this.status == null) {
            this.status = StatusDispositivo.PARADO;
        }
    }
}
