package com.lojaDeOroAtelier.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamentos")
public class PagamentosEntity {

    public enum MetodoPagamento {
        CARTAODECREDITO, CARTAOBANCARIO, TRANSFERENCIABANCARIA, DINHEIROAPAGAR, MBWAY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetodoPagamento metodoDePagar;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private double valorAPagar;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private PedidosEntity pedido;

    // Construtores
    public PagamentosEntity() {
    }

    public PagamentosEntity(MetodoPagamento metodoDePagar, LocalDateTime data, double valorAPagar, PedidosEntity pedido) {
        this.metodoDePagar = metodoDePagar;
        this.data = data;
        this.valorAPagar = valorAPagar;
        this.pedido = pedido;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MetodoPagamento getMetodoDePagar() {
        return metodoDePagar;
    }

    public void setMetodoDePagar(MetodoPagamento metodoDePagar) {
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

    // toString
    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", valorAPagar=" + valorAPagar +
                ", metodoDePagar=" + metodoDePagar +
                ", data=" + data +
                '}';
    }
}