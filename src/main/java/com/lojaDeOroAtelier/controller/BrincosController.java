package com.lojaDeOroAtelier.controller;

import com.lojaDeOroAtelier.dto.BrincosDto;
import com.lojaDeOroAtelier.entity.BrincosEntity;
import com.lojaDeOroAtelier.service.BrincosService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/brincos")
public class BrincosController {

    @Autowired
    private BrincosService brincosService;


    @PostMapping
    public ResponseEntity<BrincosDto> criarBrinco(@RequestBody BrincosDto brincosDto) {
        // Converter o DTO para a entidade
        BrincosEntity brincosEntity = new BrincosEntity();
        BeanUtils.copyProperties(brincosDto, brincosEntity);

        // Salvar a entidade
        BrincosEntity savedBrinco = brincosService.salvarBrinco(brincosEntity);

        // Converter a entidade salva de volta para DTO
        BrincosDto responseDto = new BrincosDto(savedBrinco);

        // Retornar o DTO na resposta com status 201 CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // Listar todos os brincos
    @GetMapping
    public ResponseEntity<List<BrincosDto>> listarBrincos() {
        List<BrincosEntity> brincosEntityList = brincosService.listarBrincos();

        // Converter a lista de entidades para DTOs
        List<BrincosDto> brincosDtoList = brincosEntityList.stream()
                .map(BrincosDto::new)  // Converter cada BrincosEntity para BrincosDto
                .collect(Collectors.toList());

        // Retornar a lista de DTOs como resposta HTTP
        return ResponseEntity.ok(brincosDtoList);
    }

    // Obter brinco por ID
    @GetMapping("/{id}")
    public ResponseEntity<BrincosDto> obterBrincoPorId(@PathVariable Long id) {
        // Buscar a entidade pelo ID
        BrincosEntity brincosEntity = brincosService.buscarBrinco(id);

        // Converter a entidade para DTO
        BrincosDto brincosDto = new BrincosDto(brincosEntity);

        // Retornar o DTO como resposta com status 200 OK
        return ResponseEntity.ok(brincosDto);
    }

    // Deletar brinco por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBrinco(@PathVariable Long id) {
        brincosService.apagarBrinco(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Atualizar um brinco existente
    @PutMapping("/{id}")
    public ResponseEntity<BrincosDto> atualizarBrinco(@PathVariable Long id, @RequestBody BrincosDto brincosDto) {
        // Converter o DTO para a entidade
        BrincosEntity brincosEntity = new BrincosEntity();
        BeanUtils.copyProperties(brincosDto, brincosEntity);

        // Atualizar o brinco com a entidade convertida
        BrincosEntity updatedBrinco = brincosService.atualizarBrinco(brincosEntity);

        // Converter a entidade atualizada de volta para DTO
        BrincosDto responseDto = new BrincosDto(updatedBrinco);

        // Retornar o DTO atualizado com status 200 OK
        return ResponseEntity.ok(responseDto);
    }
}

