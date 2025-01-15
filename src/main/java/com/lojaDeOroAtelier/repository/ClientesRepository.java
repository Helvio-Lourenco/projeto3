package com.lojaDeOroAtelier.repository;

import com.lojaDeOroAtelier.entity.ClientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientesRepository extends JpaRepository<ClientesEntity, Long> {
    boolean existsByNif(int nif);
    Optional<ClientesEntity> findByNif(int nif);
    void deleteByNif(int nif);
}
