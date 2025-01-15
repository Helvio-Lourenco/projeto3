package com.lojaDeOroAtelier.repository;

import com.lojaDeOroAtelier.entity.BrincosEntity;
import com.lojaDeOroAtelier.entity.VendedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendedorRepository extends JpaRepository<VendedorEntity, Long> {
    Optional<VendedorEntity> findByNif(int nif);
    boolean existsByNif(int nif);
}
