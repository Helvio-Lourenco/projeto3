package com.lojaDeOroAtelier.service;


import com.lojaDeOroAtelier.entity.BrincosEntity;
import com.lojaDeOroAtelier.repository.BrincosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrincosService {

    @Autowired
    private BrincosRepository brincosRepository;

    public BrincosEntity salvarBrinco(BrincosEntity brincosEntity) {

        if (brincosEntity.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero!");
        }

        if (brincosEntity.getPeso() <= 0) {
            throw new IllegalArgumentException("Artigo sem peso, invalido!");
        }

        if (brincosEntity.getQuantidadeEmStock() <= 0) {
            throw new IllegalArgumentException("Quantidade em Stock deve ser maior que zero!");
        }


        return brincosRepository.save(brincosEntity);
    }


    public BrincosEntity buscarBrinco(Long id) {

        return brincosRepository.findById(id).orElseThrow(() -> new RuntimeException("Brinco não encontrado com o ID: " + id));

    }


    public List<BrincosEntity> listarBrincos() {

        return brincosRepository.findAll();
    }


    public BrincosEntity atualizarBrinco(BrincosEntity brincosEntity) {
        BrincosEntity brincoAtualizado = buscarBrinco(brincosEntity.getId());

        if (brincoAtualizado == null) {
            throw new RuntimeException("brinco com id" + brincosEntity.getId() + " não existe!");
        }

        brincoAtualizado.setPreco(brincosEntity.getPreco());
        brincoAtualizado.setQuantidadeEmStock(brincosEntity.getQuantidadeEmStock());

        return brincosRepository.save(brincoAtualizado);
    }

    public void apagarBrinco(Long id) {
        BrincosEntity brincoParaApagar = buscarBrinco(id);

        brincosRepository.delete(brincoParaApagar);
    }

}