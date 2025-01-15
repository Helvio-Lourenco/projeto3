package com.lojaDeOroAtelier.dto;

import com.lojaDeOroAtelier.entity.PagamentosEntity;
import com.lojaDeOroAtelier.entity.PedidosEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class PagamentosDto implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum MetodoPagamento {
        CARTAODECREDITO, CARTAOBANCARIO, TRANSFERENCIABANCARIA, DINHEIROAPAGAR, MBWAY
    }

    private Long id;
    private PagamentosEntity.MetodoPagamento metodoDePagar;;
    private LocalDateTime data;
    private double valorAPagar;
    private PedidosEntity pedido;

    public PagamentosDto(){}

    public PagamentosDto(PagamentosEntity pagamentosEntity) {
        this.metodoDePagar = pagamentosEntity.getMetodoDePagar();  // Obtém o valor do Enum
        this.data = pagamentosEntity.getData();
        this.valorAPagar = pagamentosEntity.getValorAPagar();
        this.pedido = pagamentosEntity.getPedido();
        this.id = pagamentosEntity.getId();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PagamentosEntity.MetodoPagamento getMetodoDePagar() {
        return metodoDePagar;
    }

    public void setMetodoDePagar(PagamentosEntity.MetodoPagamento metodoDePagar) {
        this.metodoDePagar = metodoDePagar;
    }
    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public double getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public PedidosEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidosEntity pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", valorAPagar=" + valorAPagar +
                ", metodoDePagar=" + metodoDePagar +
                ", data=" + data +
                '}';
    }

    // equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PagamentosDto other = (PagamentosDto) obj;
        return Double.compare(valorAPagar, other.valorAPagar) == 0 &&
                Objects.equals(id, other.id) &&
                metodoDePagar == other.metodoDePagar &&
                Objects.equals(data, other.data) &&
                Objects.equals(pedido, other.pedido);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, metodoDePagar, data, valorAPagar, pedido);
    }


}
