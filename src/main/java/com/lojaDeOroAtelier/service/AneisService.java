package com.lojaDeOroAtelier.service;

import com.lojaDeOroAtelier.entity.AneisEntity;
import com.lojaDeOroAtelier.exception.EntityAlreadyExistsException;
import com.lojaDeOroAtelier.repository.AneisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class AneisService {

    @Autowired
    private AneisRepository aneisRepository;

    public AneisEntity salvarAnel (AneisEntity aneisEntity) {

        if ( aneisEntity.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero!");
        }

        if ( aneisEntity.getPeso() <= 0) {
            throw new IllegalArgumentException("Artigo sem peso, invalido!");
        }

        if ( aneisEntity.getQuantidadeEmStock() <= 0) {
            throw new IllegalArgumentException("Quantidade em Stock deve ser maior que zero!");
        }

        if ( aneisEntity.getTamanho() <= 0) {
            throw new IllegalArgumentException("Tamanho do anel invalido!");
        }

        return aneisRepository.save(aneisEntity);
    }


    public AneisEntity buscarAnel (Long id) {

        return aneisRepository.findById(id).orElseThrow(() -> new RuntimeException("Anel não encontrado com o ID: " + id) );

    }



    public List<AneisEntity> listarAneis() {

        return aneisRepository.findAll();
    }



    public AneisEntity atualizarAnel(AneisEntity aneisEntity) {
        AneisEntity anelAtualizado = buscarAnel(aneisEntity.getId());

        if (anelAtualizado == null ) {
            throw new RuntimeException( "Anel com id"+ aneisEntity.getId() + " não existe!");
        }

        anelAtualizado.setPreco(aneisEntity.getPreco());
        anelAtualizado.setQuantidadeEmStock(aneisEntity.getQuantidadeEmStock());

        return aneisRepository.save(anelAtualizado);
    }

    public void apagarAnel(Long id) {
         AneisEntity anelParaApagar = buscarAnel(id);

        aneisRepository.delete(anelParaApagar);
    }





}
