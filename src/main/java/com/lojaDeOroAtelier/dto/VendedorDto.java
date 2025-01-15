package com.lojaDeOroAtelier.dto;

import com.lojaDeOroAtelier.entity.VendedorEntity;

import java.io.Serializable;
import java.time.LocalDate;

public class VendedorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private int vendasFeitas;
    private String nome;
    private int nif;
    private LocalDate dataDeContratacao;
    private double salario;



    public VendedorDto(VendedorEntity vendedorEntity) {
        this.id = id;
        this.nome = nome;
        this.nif = nif;
        this.dataDeContratacao = dataDeContratacao;
        this.salario = salario;
        this.vendasFeitas = vendasFeitas;
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

    // toString
    @Override
    public String toString() {
        return "VendedorDto{" +
                "id=" + id +
                ", vendasFeitas=" + vendasFeitas +
                ", nome='" + nome + '\'' +
                ", nif=" + nif +
                ", dataDeContratacao=" + dataDeContratacao +
                ", salario=" + salario +
                '}';
    }

    // equals e hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        VendedorDto other = (VendedorDto) obj;
        return vendasFeitas == other.vendasFeitas &&
                nif == other.nif &&
                Double.compare(other.salario, salario) == 0 &&
                id.equals(other.id) &&
                nome.equals(other.nome) &&
                dataDeContratacao.equals(other.dataDeContratacao);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, vendasFeitas, nome, nif, dataDeContratacao, salario);
    }
}
