package com.lojaDeOroAtelier.service;

import com.lojaDeOroAtelier.entity.PedidosEntity;
import com.lojaDeOroAtelier.entity.VendedorEntity;
import com.lojaDeOroAtelier.exception.EntityAlreadyExistsException;
import com.lojaDeOroAtelier.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public VendedorEntity salvarVendedor(VendedorEntity vendedorEntity) throws EntityAlreadyExistsException {

        if (vendedorRepository.existsByNif(vendedorEntity.getNif())) {
            throw new EntityAlreadyExistsException(VendedorEntity.class, "Vendedor já existe com o NIF fornecido.");
        }

        return vendedorRepository.save(vendedorEntity);
    }

    public VendedorEntity buscarVendedorPorNif(int nif) {
        return vendedorRepository.findByNif(nif)
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado com o NIF: " + nif));
    }

    public VendedorEntity buscarVendedorPorId(Long id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado com o ID: " + id));
    }

    public void apagarVendedorPorId(Long id) {
        vendedorRepository.deleteById(id);
    }

    public void apagarVendedorPorNif(int nif) {
        VendedorEntity vendedorParaApagar = buscarVendedorPorNif(nif);

        vendedorRepository.delete(vendedorParaApagar);
    }

    public List<VendedorEntity> listarVendedores() {
        return vendedorRepository.findAll();
    }

    public VendedorEntity atualizarVendedor(VendedorEntity vendedorAtualizado) {

        VendedorEntity vendedorExistente = buscarVendedorPorNif(vendedorAtualizado.getNif());

        vendedorExistente.setNome(vendedorAtualizado.getNome());
        vendedorExistente.setSalario(vendedorAtualizado.getSalario());
        vendedorExistente.setDataDeContratacao(vendedorAtualizado.getDataDeContratacao());
        return vendedorRepository.save(vendedorExistente);
    }
}

