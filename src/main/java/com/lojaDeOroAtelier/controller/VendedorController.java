package com.lojaDeOroAtelier.controller;

import com.lojaDeOroAtelier.dto.VendedorDto;
import com.lojaDeOroAtelier.entity.VendedorEntity;
import com.lojaDeOroAtelier.exception.EntityAlreadyExistsException;
import com.lojaDeOroAtelier.service.VendedorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    // Criar um vendedor
    @PostMapping
    public ResponseEntity<VendedorDto> criarVendedor(@RequestBody VendedorDto vendedorDto) {
        VendedorEntity vendedorEntity = new VendedorEntity();
        BeanUtils.copyProperties(vendedorDto, vendedorEntity);
        try {
            // Salvar o vendedor no banco de dados
            VendedorEntity vendedorSalvo = vendedorService.salvarVendedor(vendedorEntity);

            // Converter a entidade salva para DTO
            VendedorDto respostaDto = new VendedorDto(vendedorSalvo);

            return ResponseEntity.status(HttpStatus.CREATED).body(respostaDto);
        } catch (EntityAlreadyExistsException e) {
            // Retornar erro 409 se o vendedor já existir
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    // Obter vendedor por NIF
    @GetMapping("/nif/{nif}")
    public ResponseEntity<VendedorDto> obterVendedorPorNif(@PathVariable int nif) {
        VendedorEntity vendedor = vendedorService.buscarVendedorPorNif(nif);

        // Converter a entidade para DTO
        VendedorDto vendedorDto = new VendedorDto(vendedor);

        return ResponseEntity.ok(vendedorDto);
    }

    // Obter vendedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<VendedorDto> obterVendedorPorId(@PathVariable Long id) {
        VendedorEntity vendedor = vendedorService.buscarVendedorPorId(id);

        // Converter a entidade para DTO
        VendedorDto vendedorDto = new VendedorDto(vendedor);

        return ResponseEntity.ok(vendedorDto);
    }

    // Listar todos os vendedores
    @GetMapping
    public ResponseEntity<List<VendedorDto>> listarVendedores() {
        List<VendedorEntity> vendedorEntityList = vendedorService.listarVendedores();

        // Converter a lista de entidades para DTOs
        List<VendedorDto> vendedorDtoList = vendedorEntityList.stream()
                .map(VendedorDto::new)  // Converter cada VendedorEntity para VendedorDto
                .collect(Collectors.toList());

        return ResponseEntity.ok(vendedorDtoList);
    }

    // Atualizar vendedor
    @PutMapping("/{id}")
    public ResponseEntity<VendedorDto> atualizarVendedor(@PathVariable Long id, @RequestBody VendedorDto vendedorDto) {
        VendedorEntity vendedorAtualizadoEntity = new VendedorEntity();
        BeanUtils.copyProperties(vendedorDto, vendedorAtualizadoEntity);

        // Atualizar o vendedor no banco de dados
        VendedorEntity vendedorAtualizado = vendedorService.atualizarVendedor(vendedorAtualizadoEntity);

        // Converter a entidade atualizada de volta para DTO
        VendedorDto responseDto = new VendedorDto(vendedorAtualizado);

        return ResponseEntity.ok(responseDto);
    }

    // Deletar vendedor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVendedorPorId(@PathVariable Long id) {
        vendedorService.apagarVendedorPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Deletar vendedor por NIF
    @DeleteMapping("/nif/{nif}")
    public ResponseEntity<Void> deletarVendedorPorNif(@PathVariable int nif) {
        vendedorService.apagarVendedorPorNif(nif);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

