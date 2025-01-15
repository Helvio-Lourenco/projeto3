package com.lojaDeOroAtelier.service;

import com.lojaDeOroAtelier.dto.PagamentosDto;
import com.lojaDeOroAtelier.entity.PagamentosEntity;
import com.lojaDeOroAtelier.entity.PedidosEntity;
import com.lojaDeOroAtelier.repository.PagamentoRepository;
import com.lojaDeOroAtelier.repository.PedidoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;


   // public PagamentosEntity salvarPagamento(PagamentosEntity pagamentosEntity) {
    //    return pagamentoRepository.save(pagamentosEntity);
   // }

    public PagamentosEntity salvarPagamento(PagamentosDto pagamentosDto) {
        PagamentosEntity pagamentosEntity = new PagamentosEntity();
        BeanUtils.copyProperties(pagamentosDto, pagamentosEntity);
        return pagamentoRepository.save(pagamentosEntity);
    }


    public PagamentosEntity buscarPagamentoPorId(Long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado com o ID: " + id));
    }


    public List<PagamentosEntity> buscarPagamentosPorPedido(Long pedidoId) {
        PedidosEntity pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + pedidoId));
        return pedido.getPagamentos();
    }


    public PagamentosEntity atualizarPagamento(Long id, PagamentosEntity pagamentoAtualizado) {
        PagamentosEntity pagamentoExistente = buscarPagamentoPorId(id);
        pagamentoExistente.setMetodoDePagar(pagamentoAtualizado.getMetodoDePagar());
        pagamentoExistente.setValorAPagar(pagamentoAtualizado.getValorAPagar());
        return pagamentoRepository.save(pagamentoExistente);
    }


    public void apagarPagamento(Long id) {
        PagamentosEntity pagamento = buscarPagamentoPorId(id);
        pagamentoRepository.delete(pagamento);
    }


    public List<PagamentosEntity> buscarTodosPagamentos() {
        return pagamentoRepository.findAll();
    }


}

