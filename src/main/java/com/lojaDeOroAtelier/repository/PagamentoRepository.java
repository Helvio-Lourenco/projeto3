package com.lojaDeOroAtelier.repository;

import com.lojaDeOroAtelier.entity.BrincosEntity;
import com.lojaDeOroAtelier.entity.PagamentosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<PagamentosEntity, Long> {

}
