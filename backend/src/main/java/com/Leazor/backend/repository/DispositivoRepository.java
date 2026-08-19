package com.Leazor.backend.repository;

import com.Leazor.backend.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    // O Spring Data cria automaticamente a query para buscar por esse campo
    Optional<Dispositivo> findByIdentificador(String identificador);
}