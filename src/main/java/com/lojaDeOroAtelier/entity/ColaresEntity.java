package com.lojaDeOroAtelier.entity;

import com.lojaDeOroAtelier.entity.interfac.Joia;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class ColaresEntity extends JoiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String comprimento;  // Atributo exclusivo de Colar


    // Construtor padrão (necessário para JPA)
    public ColaresEntity() {
    }

    // Construtor principal (sem o ID)
    public ColaresEntity(String nome, String material, double peso, double preco, String comprimento, int quantidadeEmStock) {
        super(nome, preco, peso, material, quantidadeEmStock, TipoJoia.COLAR);

        this.comprimento = comprimento;

    }

    // Getters e Setters

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getComprimento() {
        return comprimento;
    }

    public void setComprimento(String comprimento) {
        this.comprimento = comprimento;
    }


    @Override
    public String toString() {
        return "Colar{" +
                "ID=" + id +
                ", Nome='" + getNome() + '\'' +
                ", Comprimento='" + comprimento + '\'' +
                ", Material='" + getMaterial() + '\'' +
                ", Peso=" + getPeso() + "g" +
                ", Preço=" + getPreco() + " EUR" + "quantidade em stock = " + getQuantidadeEmStock() +
                '}';
    }
}
