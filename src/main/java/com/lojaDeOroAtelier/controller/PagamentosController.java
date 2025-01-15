package com.lojaDeOroAtelier.controller;


import com.lojaDeOroAtelier.dto.PagamentosDto;
import com.lojaDeOroAtelier.entity.PagamentosEntity;
import com.lojaDeOroAtelier.service.PagamentoService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentosController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentosDto> criarPagamento(@RequestBody PagamentosDto pagamentosDto) {
        // Salvar o pagamento e obter a entidade salva
        PagamentosEntity pagamentosEntity = pagamentoService.salvarPagamento(pagamentosDto);

        // Converter a entidade salva para um DTO
        PagamentosDto respostaDto = new PagamentosDto(pagamentosEntity);

        // Retornar o DTO na resposta com status 201 CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaDto);
    }

    @GetMapping("/pagamentos")
    public ResponseEntity<List<PagamentosDto>> getAllPagamentos() {
        // Chama o método correto do serviço para obter a lista de entidades
        List<PagamentosEntity> pagamentosEntity = pagamentoService.buscarTodosPagamentos();

        // Converte a lista de entidades para DTOs
        List<PagamentosDto> pagamentosDto = pagamentosEntity.stream()
                .map(PagamentosDto::new)  // Converte cada PagamentoEntity para PagamentoDto
                .collect(Collectors.toList());

        // Retorna a lista de PagamentosDto como resposta HTTP
        return ResponseEntity.ok(pagamentosDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PagamentosDto> obterPagamentoPorId(@PathVariable Long id) {
        // Buscar a entidade pelo ID
        PagamentosEntity pagamentosEntity = pagamentoService.buscarPagamentoPorId(id);

        // Converter a entidade para um DTO
        PagamentosDto pagamentosDto = new PagamentosDto(pagamentosEntity);

        // Retornar o DTO como resposta com status 200 OK
        return ResponseEntity.ok(pagamentosDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable Long id) {
        pagamentoService.apagarPagamento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PagamentosDto> atualizarPagamento(@PathVariable Long id, @RequestBody PagamentosDto pagamentosDto) {
        // Converte o DTO para Entity antes de chamar o service
        PagamentosEntity pagamentoAtualizadoEntity = new PagamentosEntity();
        BeanUtils.copyProperties(pagamentosDto, pagamentoAtualizadoEntity);

        // Chama o serviço para atualizar o pagamento
        PagamentosEntity pagamentoAtualizado = pagamentoService.atualizarPagamento(id, pagamentoAtualizadoEntity);

        // Converte a entidade de volta para DTO para retornar
        PagamentosDto responseDto = new PagamentosDto(pagamentoAtualizado);

        return ResponseEntity.ok(responseDto);
    }
}

