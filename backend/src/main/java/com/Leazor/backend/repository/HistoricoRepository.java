package com.Leazor.backend.repository;

import com.Leazor.backend.model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    // Busca todos os eventos ordenando do ID mais recente para o mais antigo
    List<Historico> findAllByOrderByIdDesc();
}