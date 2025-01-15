package com.lojaDeOroAtelier.service;

import com.lojaDeOroAtelier.entity.BrincosEntity;
import com.lojaDeOroAtelier.entity.GerentesEntity;
import com.lojaDeOroAtelier.exception.EntityAlreadyExistsException;
import com.lojaDeOroAtelier.repository.GerentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerentesService {

    @Autowired
    private GerentesRepository gerentesRepository;

    public GerentesEntity salvarGerente(GerentesEntity gerentesEntity) throws EntityAlreadyExistsException {

        if (gerentesRepository.existsByNif(gerentesEntity.getNif())) {
            throw new EntityAlreadyExistsException(GerentesEntity.class, "Gerente já existe com o NIF fornecido.");
        }

        return gerentesRepository.save(gerentesEntity);
    }

    public GerentesEntity buscarGerentePorNif(int nif) {
        return gerentesRepository.findByNif(nif)
                .orElseThrow(() -> new RuntimeException("Gerente não encontrado com o NIF: " + nif));
    }

    public GerentesEntity buscarGerentePorId(Long id) {
        return gerentesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gerente não encontrado com o ID: " + id));
    }

    public void apagarGerentePorId(Long id) {
        gerentesRepository.deleteById(id);
    }

    public void apagarGerentePorNif(int nif) {
        GerentesEntity gerenteParaApagar = buscarGerentePorNif(nif);

        gerentesRepository.delete(gerenteParaApagar);
    }

    public List<GerentesEntity> listarGerentes() {

        return gerentesRepository.findAll();
    }

    public GerentesEntity atualizarGerente(GerentesEntity gerenteAtualizado) {

        GerentesEntity gerenteExistente = buscarGerentePorNif(gerenteAtualizado.getNif());

        gerenteExistente.setNome(gerenteAtualizado.getNome());
        gerenteExistente.setSalario(gerenteAtualizado.getSalario());
        gerenteExistente.setDataDeContratacao(gerenteAtualizado.getDataDeContratacao());
        return gerentesRepository.save(gerenteExistente);
    }
}
