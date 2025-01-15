package com.lojaDeOroAtelier.controller;

import com.lojaDeOroAtelier.dto.ClientesDto;
import com.lojaDeOroAtelier.entity.ClientesEntity;
import com.lojaDeOroAtelier.exception.EntityAlreadyExistsException;
import com.lojaDeOroAtelier.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @PostMapping
    public ResponseEntity<ClientesDto> criarCliente(@RequestBody ClientesDto clientesDto) {
        // Converter o DTO para Entity
        ClientesEntity clientesEntity = new ClientesEntity();
        BeanUtils.copyProperties(clientesDto, clientesEntity);

        // Verificar se o NIF já existe, caso contrário, salvar
        try {
            // Salvar o cliente no banco de dados
            ClientesEntity clienteSalvo = clientesService.salvarCliente(clientesEntity);

            // Converter a entidade salva de volta para DTO
            ClientesDto responseDto = new ClientesDto(clienteSalvo);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (EntityAlreadyExistsException e) {
            // Retornar erro 409 se o cliente já existir
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

        // Obter cliente por NIF
        @GetMapping("/nif/{nif}")
        public ResponseEntity<ClientesDto> obterClientePorNif(@PathVariable int nif) {
            // Buscar o cliente pelo NIF
            ClientesEntity cliente = clientesService.buscarClientePorNif(nif);

            // Converter a entidade para DTO
            ClientesDto clientesDto = new ClientesDto(cliente);

            return ResponseEntity.ok(clientesDto);
        }

        // Obter cliente por ID
        @GetMapping("/{id}")
        public ResponseEntity<ClientesDto> obterClientePorId(@PathVariable Long id) {
            ClientesEntity cliente = clientesService.buscarClientePorId(id);

            // Converter a entidade para DTO
            ClientesDto clientesDto = new ClientesDto(cliente);

            return ResponseEntity.ok(clientesDto);
        }

        // Deletar cliente por ID
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletarClientePorId(@PathVariable Long id) {
            clientesService.apagarClientePorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Deletar cliente por NIF
        @DeleteMapping("/nif/{nif}")
        public ResponseEntity<Void> deletarClientePorNif(@PathVariable int nif) {
            clientesService.apagarClientePorNif(nif);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    @GetMapping
    public ResponseEntity<List<ClientesDto>> listarClientes() {
        // Buscar todos os clientes
        List<ClientesEntity> clientesEntityList = clientesService.listarClientes();

        // Converter a lista de entidades para DTOs
        List<ClientesDto> clientesDtoList = clientesEntityList.stream()
                .map(ClientesDto::new)  // Converter cada ClientesEntity para ClientesDto
                .collect(Collectors.toList());

        // Retornar a lista de DTOs como resposta HTTP
        return ResponseEntity.ok(clientesDtoList);
    }

        // Atualizar um cliente
        @PutMapping("/{id}")
        public ResponseEntity<ClientesDto> atualizarCliente(@PathVariable Long id, @RequestBody ClientesDto clientesDto) {
            // Converter o DTO para Entity
            ClientesEntity clienteAtualizadoEntity = new ClientesEntity();
            BeanUtils.copyProperties(clientesDto, clienteAtualizadoEntity);

            // Atualizar o cliente no banco de dados
            ClientesEntity clienteAtualizado = clientesService.atualizarCliente(clienteAtualizadoEntity);

            // Converter a entidade atualizada de volta para DTO
            ClientesDto responseDto = new ClientesDto(clienteAtualizado);

            return ResponseEntity.ok(responseDto);
        }
    }


