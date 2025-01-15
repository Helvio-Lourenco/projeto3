package com.lojaDeOroAtelier.controller;



import com.lojaDeOroAtelier.dto.AneisDto;
import com.lojaDeOroAtelier.entity.AneisEntity;
import com.lojaDeOroAtelier.service.AneisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/aneis")
public class AneisController {

    @Autowired
    private AneisService aneisService;

    // Criar um novo anel
    @PostMapping
    public ResponseEntity<AneisDto> criarAnel(@RequestBody AneisDto aneisDto) {
        // Converter o DTO para a entidade
        AneisEntity aneisEntity = new AneisEntity();
        BeanUtils.copyProperties(aneisDto, aneisEntity);

        // Salvar a entidade
        AneisEntity savedAnel = aneisService.salvarAnel(aneisEntity);

        // Converter a entidade salva de volta para DTO
        AneisDto responseDto = new AneisDto(savedAnel);

        // Retornar o DTO na resposta com status 201 CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // Listar todos os aneis
    @GetMapping
    public ResponseEntity<List<AneisDto>> listarAneis() {
        List<AneisEntity> aneisEntityList = aneisService.listarAneis();

        // Converter a lista de entidades para DTOs
        List<AneisDto> aneisDtoList = aneisEntityList.stream()
                .map(AneisDto::new)  // Converter cada AneisEntity para AneisDto
                .collect(Collectors.toList());

        // Retornar a lista de DTOs como resposta HTTP
        return ResponseEntity.ok(aneisDtoList);
    }

    // Obter anel por ID
    @GetMapping("/{id}")
    public ResponseEntity<AneisDto> obterAnelPorId(@PathVariable Long id) {
        // Buscar a entidade pelo ID
        AneisEntity aneisEntity = aneisService.buscarAnel(id);

        // Converter a entidade para DTO
        AneisDto aneisDto = new AneisDto(aneisEntity);

        // Retornar o DTO como resposta com status 200 OK
        return ResponseEntity.ok(aneisDto);
    }

    // Deletar anel por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnel(@PathVariable Long id) {
        aneisService.apagarAnel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Atualizar um anel existente
    @PutMapping("/{id}")
    public ResponseEntity<AneisDto> atualizarAnel(@PathVariable Long id, @RequestBody AneisDto aneisDto) {
        // Converter o DTO para a entidade
        AneisEntity aneisEntity = new AneisEntity();
        BeanUtils.copyProperties(aneisDto, aneisEntity);

        // Atualizar o anel com a entidade convertida
        AneisEntity updatedAnel = aneisService.atualizarAnel(aneisEntity);

        // Converter a entidade atualizada de volta para DTO
        AneisDto responseDto = new AneisDto(updatedAnel);

        // Retornar o DTO atualizado com status 200 OK
        return ResponseEntity.ok(responseDto);
    }
}

