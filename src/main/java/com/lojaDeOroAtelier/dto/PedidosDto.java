package com.lojaDeOroAtelier.dto;

import com.lojaDeOroAtelier.entity.PedidosEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PedidosDto implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Status {
        Entregue, Pendente, Aceito, Cancelado
    }
    private Long id;
    private LocalDate data;
    private Long cliente; // ID do cliente para simplificar
    private List<Long> itens; // IDs das joias
    private double valorTotal;
    private PedidosEntity.Status estado; // Representação do status como String
    private List<Long> pagamentos; // IDs dos pagamentos associados
    private String metodoDePagar;




    public PedidosDto(PedidosEntity pedidosEntity) {
        this.id = pedidosEntity.getId();
        this.data = pedidosEntity.getData(); // Convertendo Date para String
        this.cliente = pedidosEntity.getCliente().getId(); // Obtém o ID do cliente
        this.itens = pedidosEntity.getItens().stream()
                .map(joia -> joia.getId()) // Convertendo cada joia para seu ID
                .collect(Collectors.toList());
        this.valorTotal = pedidosEntity.getValorTotal();
        this.estado = pedidosEntity.getEstado(); // Enum Status
        this.pagamentos = pedidosEntity.getPagamentos().stream()
                .map(pagamento -> pagamento.getId()) // Convertendo cada pagamento para seu ID
                .collect(Collectors.toList());
        this.metodoDePagar = pedidosEntity.getMetodoDePagar();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public List<Long> getItens() {
        return itens;
    }

    public void setItens(List<Long> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public PedidosEntity.Status getEstado() {
        return estado;
    }

    public void setEstado(PedidosEntity.Status estado) {
        this.estado = estado;
    }

    public List<Long> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Long> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public String getMetodoDePagar() {
        return metodoDePagar;
    }

    public void setMetodoDePagar(String metodoDePagar) {
        this.metodoDePagar = metodoDePagar;
    }

    // toString
    @Override
    public String toString() {
        return "PedidosDto{" +
                "id=" + id +
                ", data=" + data +
                ", cliente=" + cliente +
                ", itens=" + itens +
                ", valorTotal=" + valorTotal +
                ", estado='" + estado + '\'' +
                ", pagamentos=" + pagamentos +
                ", metodoDePagar='" + metodoDePagar + '\'' +
                '}';
    }

    // equals e hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PedidosDto other = (PedidosDto) obj;
        return Double.compare(other.valorTotal, valorTotal) == 0 &&
                id.equals(other.id) &&
                data.equals(other.data) &&
                cliente.equals(other.cliente) &&
                itens.equals(other.itens) &&
                estado.equals(other.estado) &&
                pagamentos.equals(other.pagamentos) &&
                metodoDePagar.equals(other.metodoDePagar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, cliente, itens, valorTotal, estado, pagamentos, metodoDePagar);
    }
}
