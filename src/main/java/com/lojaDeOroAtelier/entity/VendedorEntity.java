package com.lojaDeOroAtelier.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class VendedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Geração automática do ID
    private Long id;

    private int vendasFeitas;
    private String nome;

    @Column(unique = true, nullable = false)
    private int nif;

    @Column(nullable = false)
    private LocalDate dataDeContratacao;

    private double salario;

    public VendedorEntity() {
    }

    public VendedorEntity( String nome, int nif, LocalDate dataDeContratacao, double salario, int vendasFeitas) {

        this.vendasFeitas=vendasFeitas;
        this.nome=nome;
        this.nif=nif;
        this.dataDeContratacao=dataDeContratacao;
        this.salario=salario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVendasFeitas() {
        return vendasFeitas;
    }

    public void setVendasFeitas(int vendasFeitas) {
        this.vendasFeitas = vendasFeitas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public LocalDate getDataDeContratacao() {
        return dataDeContratacao;
    }

    public void setDataDeContratacao(LocalDate dataDeContratacao) {
        this.dataDeContratacao = dataDeContratacao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    @Override
    public String toString(){
        return "Id do vendedor: "+id +" Nome: "+nome+" ; Nif: "+nif+" ; Data de Contratação: "+ dataDeContratacao+ "; Salario: "+salario+ "; Vendas feitas: "+ vendasFeitas ;
    }


}

