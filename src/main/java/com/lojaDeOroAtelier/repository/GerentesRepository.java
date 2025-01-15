package com.lojaDeOroAtelier.repository;

import com.lojaDeOroAtelier.entity.BrincosEntity;
import com.lojaDeOroAtelier.entity.GerentesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GerentesRepository extends JpaRepository<GerentesEntity, Long> {
    Optional<GerentesEntity> findByNif(int nif);
    boolean existsByNif(int nif);
}
