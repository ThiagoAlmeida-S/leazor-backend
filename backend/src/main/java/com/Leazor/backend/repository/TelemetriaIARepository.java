package com.Leazor.backend.repository;


import com.Leazor.backend.model.TelemetriaIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelemetriaIARepository extends JpaRepository<TelemetriaIA, Long> {
}
