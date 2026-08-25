package com.Leazor.backend.repository;

import com.Leazor.backend.model.LeituraSensor;
import com.Leazor.backend.model.TipoSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeituraSensorRepository extends JpaRepository<LeituraSensor, Long> {

    // Adicionado para resolver a chamada do LeituraSensorService
    List<LeituraSensor> findBySensorIdOrderByDataHoraDesc(Long sensorId);

    // Busca a leitura mais recente de um tipo específico de sensor
    @Query("SELECT l FROM LeituraSensor l WHERE l.sensor.tipo = :tipo ORDER BY l.id DESC LIMIT 1")
    Optional<LeituraSensor> buscarUltimaLeituraPorTipo(@Param("tipo") TipoSensor tipo);
}