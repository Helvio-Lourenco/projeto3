package com.lojaDeOroAtelier.controller;

import com.lojaDeOroAtelier.dto.ColaresDto;
import com.lojaDeOroAtelier.entity.ColaresEntity;
import com.lojaDeOroAtelier.service.ColaresService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/colares")
public class ColaresController {

    @Autowired
    private ColaresService colaresService;

    // Criar um colar
    @PostMapping
    public ResponseEntity<ColaresDto> criarColar(@RequestBody ColaresDto colaresDto) {
        ColaresEntity colaresEntity = new ColaresEntity();
        BeanUtils.copyProperties(colaresDto, colaresEntity);

        // Salvar o colar no banco de dados
        ColaresEntity colarSalvo = colaresService.salvarColar(colaresEntity);

        // Converter a entidade salva para DTO
        ColaresDto respostaDto = new ColaresDto(colarSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(respostaDto);
    }

    // Obter colar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ColaresDto> obterColarPorId(@PathVariable Long id) {
        ColaresEntity colar = colaresService.buscarColar(id);

        // Converter a entidade para DTO
        ColaresDto colaresDto = new ColaresDto(colar);

        return ResponseEntity.ok(colaresDto);
    }

    // Listar todos os colares
    @GetMapping
    public ResponseEntity<List<ColaresDto>> listarColares() {
        List<ColaresEntity> colaresEntityList = colaresService.listarColares();

        // Converter a lista de entidades para DTOs
        List<ColaresDto> colaresDtoList = colaresEntityList.stream()
                .map(ColaresDto::new)  // Converter cada ColaresEntity para ColaresDto
                .collect(Collectors.toList());

        return ResponseEntity.ok(colaresDtoList);
    }

    // Atualizar colar
    @PutMapping("/{id}")
    public ResponseEntity<ColaresDto> atualizarColar(@PathVariable Long id, @RequestBody ColaresDto colaresDto) {
        ColaresEntity colarAtualizadoEntity = new ColaresEntity();
        BeanUtils.copyProperties(colaresDto, colarAtualizadoEntity);

        // Atualizar o colar no banco de dados
        ColaresEntity colarAtualizado = colaresService.atualizarColar(colarAtualizadoEntity);

        // Converter a entidade atualizada de volta para DTO
        ColaresDto responseDto = new ColaresDto(colarAtualizado);

        return ResponseEntity.ok(responseDto);
    }

    // Deletar colar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarColarPorId(@PathVariable Long id) {
        colaresService.apagarColar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
