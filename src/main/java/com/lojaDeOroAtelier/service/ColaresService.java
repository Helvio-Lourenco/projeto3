package com.lojaDeOroAtelier.service;

import com.lojaDeOroAtelier.entity.AneisEntity;
import com.lojaDeOroAtelier.entity.ColaresEntity;
import com.lojaDeOroAtelier.repository.AneisRepository;
import com.lojaDeOroAtelier.repository.ColaresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaresService {

    @Autowired
    private ColaresRepository colaresRepository;

    public ColaresEntity salvarColar(ColaresEntity colaresEntity) {

        if (colaresEntity.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero!");
        }

        if (colaresEntity.getPeso() <= 0) {
            throw new IllegalArgumentException("Artigo sem peso, invalido!");
        }

        if (colaresEntity.getQuantidadeEmStock() <= 0) {
            throw new IllegalArgumentException("Quantidade em Stock deve ser maior que zero!");
        }

        return colaresRepository.save(colaresEntity);
    }


    public ColaresEntity buscarColar(Long id) {

        return colaresRepository.findById(id).orElseThrow(() -> new RuntimeException("Colar não encontrado com o ID: " + id));

    }


    public List<ColaresEntity> listarColares() {

        return colaresRepository.findAll();
    }


    public ColaresEntity atualizarColar(ColaresEntity colaresEntity) {
        ColaresEntity colarAtualizado = buscarColar(colaresEntity.getId());

        if (colarAtualizado == null) {
            throw new RuntimeException("Coar com id" + colaresEntity.getId() + " não existe!");
        }

        colarAtualizado.setPreco(colaresEntity.getPreco());
        colarAtualizado.setQuantidadeEmStock(colaresEntity.getQuantidadeEmStock());

        return colaresRepository.save(colarAtualizado);
    }

    public void apagarColar(Long id) {
        ColaresEntity colarParaApagar = buscarColar(id);

        colaresRepository.delete(colarParaApagar);
    }


}
