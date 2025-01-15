package com.lojaDeOroAtelier.entity;

import com.lojaDeOroAtelier.entity.interfac.Joia;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;



@Entity

public class PedidosEntity  {


    public enum Status {
        Entregue, Pendente, Aceito, Cancelado
    }

    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "data", nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClientesEntity cliente;

    @ManyToMany
    @JoinTable(
            name = "pedido_joia",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "joia_id")
    )
    private List<JoiaEntity> itens;

    @Column( name= "valor_total", nullable = false)
    private double valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Status estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PagamentosEntity> pagamentos;

    @Column(name = "metodo_de_pagar")
    private String metodoDePagar;

    public PedidosEntity() {
    }

    public PedidosEntity(LocalDate data, ClientesEntity cliente, List<JoiaEntity> itens, Status estado, List<Joia> pagamentos, double valorTotal, String metodoDePagar) {
        this.data = data;
        this.cliente = cliente;
        this.estado = estado;
        this.valorTotal = valorTotal;
        this.pagamentos = new ArrayList<>();
        this.itens = itens;
        this.metodoDePagar = metodoDePagar;

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

    public ClientesEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClientesEntity cliente) {
        this.cliente = cliente;
    }

    public List<JoiaEntity> getItens() {
        return itens;
    }

    public void setItens(List<JoiaEntity> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Status getEstado() {
        return estado;
    }

    public void setEstado(Status estado) {
        this.estado = estado;
    }

    public List<PagamentosEntity> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<PagamentosEntity> pagamentos) {
        this.pagamentos = pagamentos;
    }

    private Long gerarId() {
        return (long) (Math.random() * 1000);  // Gera um ID aleatório entre 0 e 100000
    }

    public String getMetodoDePagar() {
        return metodoDePagar;
    }

    public void setMetodoDePagar(String metodoDePagar) {
        this.metodoDePagar = metodoDePagar;
    }


    @Override
    public String toString() {
        // Exibir os itens do pedido como uma lista de IDs de joias
        StringBuilder itensString = new StringBuilder();
        for (Joia joia : itens) {
            itensString.append(joia.getId()).append(";");  // Usar ponto-e-vírgula
        }

        // Remover a última vírgula e espaço extra
        if (itensString.length() > 0) {
            itensString.setLength(itensString.length() - 2); // Remover vírgula final
        }

        // Exibir as informações do pedido
        return "Pedido [id: " + id + ", Data: " + data + ", Ciente Nif:" + cliente.getNif() + ", Itens: [" + itensString + "], Valor Total: " + valorTotal + ", Metodo de pagamento: " + metodoDePagar + "]";
    }



}



