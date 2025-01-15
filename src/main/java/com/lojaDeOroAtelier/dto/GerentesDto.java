package com.lojaDeOroAtelier.dto;

import com.lojaDeOroAtelier.entity.GerentesEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class GerentesDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id; // Adicionado para identificador único no banco de dados
    private String nome;
    private int nif;
    private double salario;
    private LocalDate dataDeContratacao;
    private int vendasEquipe;

    public GerentesDto( ){

      }


    public GerentesDto(GerentesEntity gerentesEntity) {
        this.nome = gerentesEntity.getNome();                       // Obtém o nome da entidade
        this.nif = gerentesEntity.getNif();                         // Obtém o NIF da entidade
        this.dataDeContratacao = gerentesEntity.getDataDeContratacao(); // Obtém a data de contratação
        this.salario = gerentesEntity.getSalario();                 // Obtém o salário da entidade
        this.vendasEquipe = gerentesEntity.getVendasEquipe();       // Obtém as vendas da equipe
        this.id = gerentesEntity.getId();                           // Obtém o ID da entidade
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getVendasEquipe() {
        return vendasEquipe;
    }

    public void setVendasEquipe(int vendasEquipe) {
        this.vendasEquipe = vendasEquipe;
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

    // fata equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        GerentesDto other = (GerentesDto) obj;

        // Comparar os atributos importantes para verificar se os objetos são iguais
        return Objects.equals(id, other.id) &&  // Comparar id
                Objects.equals(nome, other.nome) &&  // Comparar nome
                nif == other.nif &&  // Comparar nif
                Double.compare(salario, other.salario) == 0 &&  // Comparar salário
                Objects.equals(dataDeContratacao, other.dataDeContratacao) &&  // Comparar data de contratação
                vendasEquipe == other.vendasEquipe;  // Comparar vendas da equipe

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, nif, salario, dataDeContratacao, vendasEquipe);
    }

}
