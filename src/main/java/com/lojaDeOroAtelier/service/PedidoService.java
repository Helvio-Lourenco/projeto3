package com.lojaDeOroAtelier.service;

import com.lojaDeOroAtelier.entity.PedidosEntity;
import com.lojaDeOroAtelier.entity.ClientesEntity;
import com.lojaDeOroAtelier.entity.JoiaEntity;
import com.lojaDeOroAtelier.repository.PedidoRepository;
import com.lojaDeOroAtelier.repository.ClientesRepository;
import com.lojaDeOroAtelier.repository.JoiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private JoiaRepository joiaRepository;


    public PedidosEntity salvarPedido(PedidosEntity pedidosEntity) {
        return pedidoRepository.save(pedidosEntity);
    }


    public PedidosEntity buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + id));
    }


    public PedidosEntity atualizarPedido(Long id, PedidosEntity pedidoAtualizado) {
        PedidosEntity pedidoExistente = buscarPedidoPorId(id);
        pedidoExistente.setData(pedidoAtualizado.getData());
        pedidoExistente.setCliente(pedidoAtualizado.getCliente());
        pedidoExistente.setItens(pedidoAtualizado.getItens());
        pedidoExistente.setValorTotal(pedidoAtualizado.getValorTotal());
        pedidoExistente.setEstado(pedidoAtualizado.getEstado());
        pedidoExistente.setMetodoDePagar(pedidoAtualizado.getMetodoDePagar());
        return pedidoRepository.save(pedidoExistente);
    }


    public void apagarPedido(Long id) {
        PedidosEntity pedido = buscarPedidoPorId(id);
        pedidoRepository.delete(pedido);
    }


    public List<PedidosEntity> buscarTodosPedidos() {
        return pedidoRepository.findAll();
    }

    public long contarPedidos() {
        return pedidoRepository.count();
    }

}