package com.lojaDeOroAtelier.controller;

import com.lojaDeOroAtelier.dto.PedidosDto;
import com.lojaDeOroAtelier.entity.PedidosEntity;
import com.lojaDeOroAtelier.service.PedidoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    @Autowired
    private PedidoService pedidoService;

    // Criar um pedido
    @PostMapping
    public ResponseEntity<PedidosDto> criarPedido(@RequestBody PedidosDto pedidosDto) {
        PedidosEntity pedidosEntity = new PedidosEntity();
        BeanUtils.copyProperties(pedidosDto, pedidosEntity);

        // Salvar o pedido no banco de dados
        PedidosEntity pedidoSalvo = pedidoService.salvarPedido(pedidosEntity);

        // Converter a entidade salva para DTO
        PedidosDto respostaDto = new PedidosDto(pedidoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(respostaDto);
    }

    // Obter pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidosDto> obterPedidoPorId(@PathVariable Long id) {
        PedidosEntity pedido = pedidoService.buscarPedidoPorId(id);

        // Converter a entidade para DTO
        PedidosDto pedidosDto = new PedidosDto(pedido);

        return ResponseEntity.ok(pedidosDto);
    }

    // Listar todos os pedidos
    @GetMapping
    public ResponseEntity<List<PedidosDto>> listarPedidos() {
        List<PedidosEntity> pedidosEntityList = pedidoService.buscarTodosPedidos();

        // Converter a lista de entidades para DTOs
        List<PedidosDto> pedidosDtoList = pedidosEntityList.stream()
                .map(PedidosDto::new)  // Converter cada PedidosEntity para PedidosDto
                .collect(Collectors.toList());

        return ResponseEntity.ok(pedidosDtoList);
    }

    // Atualizar um pedido
    @PutMapping("/{id}")
    public ResponseEntity<PedidosDto> atualizarPedido(@PathVariable Long id, @RequestBody PedidosDto pedidosDto) {
        PedidosEntity pedidoAtualizadoEntity = new PedidosEntity();
        BeanUtils.copyProperties(pedidosDto, pedidoAtualizadoEntity);

        // Atualizar o pedido no banco de dados
        PedidosEntity pedidoAtualizado = pedidoService.atualizarPedido(id, pedidoAtualizadoEntity);

        // Converter a entidade atualizada de volta para DTO
        PedidosDto responseDto = new PedidosDto(pedidoAtualizado);

        return ResponseEntity.ok(responseDto);
    }

    // Deletar pedido por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedidoPorId(@PathVariable Long id) {
        pedidoService.apagarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
