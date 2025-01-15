package com.lojaDeOroAtelier.repository;

import com.lojaDeOroAtelier.entity.BrincosEntity;
import com.lojaDeOroAtelier.entity.PedidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidosEntity, Long> {

}
