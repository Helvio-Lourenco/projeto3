package com.lojaDeOroAtelier.service;

import com.lojaDeOroAtelier.entity.AneisEntity;
import com.lojaDeOroAtelier.entity.BrincosEntity;
import com.lojaDeOroAtelier.entity.ClientesEntity;
import com.lojaDeOroAtelier.exception.EntityAlreadyExistsException;
import com.lojaDeOroAtelier.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;



    public ClientesEntity salvarCliente(ClientesEntity clientesEntity) throws EntityAlreadyExistsException {

        if (clientesRepository.existsByNif(clientesEntity.getNif())) {
            throw new EntityAlreadyExistsException(ClientesEntity.class, "Cliente já existe com o NIF fornecido.");
        }

        return clientesRepository.save(clientesEntity);
    }

    public ClientesEntity buscarClientePorNif(int nif) {
        return clientesRepository.findByNif(nif)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o NIF: " + nif));
    }


    public ClientesEntity buscarClientePorId(Long id) {
        return clientesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o NIF: " + id));
    }

    public List<ClientesEntity> listarClientes() {

        return clientesRepository.findAll();
    }

    public void apagarClientePorId(Long id) {
        clientesRepository.deleteById(id);
    }

    public void apagarClientePorNif(int nif) {
        ClientesEntity clienteParaApagar = buscarClientePorNif(nif);

        clientesRepository.delete(clienteParaApagar);
    }


    public ClientesEntity atualizarCliente(ClientesEntity clienteAtualizado) {

        ClientesEntity clienteExistente = buscarClientePorNif(clienteAtualizado.getNif());


        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setContacto(clienteAtualizado.getContacto());
        clienteExistente.setMorada(clienteAtualizado.getMorada());


        return clientesRepository.save(clienteExistente);
    }



}
