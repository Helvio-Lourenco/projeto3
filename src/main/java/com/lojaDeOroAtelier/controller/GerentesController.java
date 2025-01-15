package com.lojaDeOroAtelier.controller;

import com.lojaDeOroAtelier.dto.GerentesDto;
import com.lojaDeOroAtelier.entity.GerentesEntity;
import com.lojaDeOroAtelier.exception.EntityAlreadyExistsException;
import com.lojaDeOroAtelier.service.GerentesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gerentes")
public class GerentesController {

    @Autowired
    private GerentesService gerentesService;

    // Criar um gerente
    @PostMapping
    public ResponseEntity<GerentesDto> criarGerente(@RequestBody GerentesDto gerentesDto) {
        GerentesEntity gerentesEntity = new GerentesEntity();
        BeanUtils.copyProperties(gerentesDto, gerentesEntity);
        try {
            // Salvar o gerente no banco de dados
            GerentesEntity gerenteSalvo = gerentesService.salvarGerente(gerentesEntity);

            // Converter a entidade salva para DTO
            GerentesDto respostaDto = new GerentesDto(gerenteSalvo);

            return ResponseEntity.status(HttpStatus.CREATED).body(respostaDto);
        } catch (EntityAlreadyExistsException e) {
            // Retornar erro 409 se o cliente já existir
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    // Obter gerente por NIF
    @GetMapping("/nif/{nif}")
    public ResponseEntity<GerentesDto> obterGerentePorNif(@PathVariable int nif) {
        GerentesEntity gerente = gerentesService.buscarGerentePorNif(nif);

        // Converter a entidade para DTO
        GerentesDto gerentesDto = new GerentesDto(gerente);

        return ResponseEntity.ok(gerentesDto);
    }

    // Obter gerente por ID
    @GetMapping("/{id}")
    public ResponseEntity<GerentesDto> obterGerentePorId(@PathVariable Long id) {
        GerentesEntity gerente = gerentesService.buscarGerentePorId(id);

        // Converter a entidade para DTO
        GerentesDto gerentesDto = new GerentesDto(gerente);

        return ResponseEntity.ok(gerentesDto);
    }

    // Listar todos os gerentes
    @GetMapping
    public ResponseEntity<List<GerentesDto>> listarGerentes() {
        List<GerentesEntity> gerentesEntityList = gerentesService.listarGerentes();

        // Converter a lista de entidades para DTOs
        List<GerentesDto> gerentesDtoList = gerentesEntityList.stream()
                .map(GerentesDto::new)  // Converter cada GerentesEntity para GerentesDto
                .collect(Collectors.toList());

        return ResponseEntity.ok(gerentesDtoList);
    }

    // Deletar gerente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGerentePorId(@PathVariable Long id) {
        gerentesService.apagarGerentePorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Deletar gerente por NIF
    @DeleteMapping("/nif/{nif}")
    public ResponseEntity<Void> deletarGerentePorNif(@PathVariable int nif) {
        gerentesService.apagarGerentePorNif(nif);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Atualizar gerente
    @PutMapping("/{id}")
    public ResponseEntity<GerentesDto> atualizarGerente(@PathVariable Long id, @RequestBody GerentesDto gerentesDto) {
        GerentesEntity gerenteAtualizadoEntity = new GerentesEntity();
        BeanUtils.copyProperties(gerentesDto, gerenteAtualizadoEntity);

        // Atualizar o gerente no banco de dados
        GerentesEntity gerenteAtualizado = gerentesService.atualizarGerente(gerenteAtualizadoEntity);

        // Converter a entidade atualizada de volta para DTO
        GerentesDto responseDto = new GerentesDto(gerenteAtualizado);

        // Retornar a resposta com o status 200 OK e o DTO atualizado
        return ResponseEntity.ok(responseDto);
    }



}
