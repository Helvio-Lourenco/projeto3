package com.lojaDeOroAtelier.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class GerentesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Adicionado para identificador único no banco de dados

    private String nome;

    @Column(unique = true, nullable = false) // Garante que o NIF seja único e obrigatório
    private int nif;

    private double salario;


    private LocalDate dataDeContratacao;


    private int vendasEquipe;

    // Construtor padrão (necessário para JPA)
    public GerentesEntity() {
    }

    // Construtor com parâmetros
    public GerentesEntity(String nome, int nif, LocalDate dataDeContratacao, double salario, int vendasEquipe) {
        this.nome = nome;
        this.nif = nif;
        this.dataDeContratacao = dataDeContratacao;
        this.salario = salario;
        this.vendasEquipe = vendasEquipe;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVendasEquipe() {
        return vendasEquipe;
    }

    public void setVendasEquipe(int vendasEquipe) {
        this.vendasEquipe = vendasEquipe;
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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getDataDeContratacao() {
        return dataDeContratacao;
    }

    public void setDataDeContratacao(LocalDate dataDeContratacao) {
        this.dataDeContratacao = dataDeContratacao;
    }

    @Override
    public String toString() {
        return "GerentesEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nif=" + nif +
                ", salario=" + salario +
                ", dataDeContratacao=" + dataDeContratacao +
                ", vendasEquipe=" + vendasEquipe +
                '}';
    }
}
