package com.Leazor.backend.repository;



import com.Leazor.backend.model.LeituraSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeituraSensorRepository extends JpaRepository<LeituraSensor, Long> {

    // Busca as leituras de um sensor específico ordenadas da mais recente para a mais antiga
    List<LeituraSensor> findBySensorIdOrderByDataHoraDesc(Long sensorId);
}