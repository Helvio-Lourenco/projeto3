package com.lojaDeOroAtelier.dto;

import com.lojaDeOroAtelier.entity.JoiaEntity;
import com.lojaDeOroAtelier.entity.JoiaEntity.TipoJoia;
import java.io.Serializable;
import java.util.Objects;

public class JoiaDto implements Serializable {

    private static final long serialVersionUID = 1L;




    private String nome;
    private double peso;
    private double preco;
    private String material;
    private int quantidadeEmStock;
    private TipoJoia tipo;




    public JoiaDto() {
        // Construtor vazio
    }

    // Construtor
    public JoiaDto(JoiaEntity joiaEntity) {
        this.nome = joiaEntity.getNome(); // Atribui o nome da entidade
        this.peso = joiaEntity.getPeso(); // Atribui o peso da entidade
        this.preco = joiaEntity.getPreco(); // Atribui o preço da entidade
        this.material = joiaEntity.getMaterial(); // Atribui o material da entidade
        this.quantidadeEmStock = joiaEntity.getQuantidadeEmStock();
        this.tipo = joiaEntity.getTipo();// Atribui a quantidade em stock da entidade
    }

    // Getters e Setters


    public TipoJoia getTipo() {
        return tipo;
    }

    public void setTipo(TipoJoia tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getQuantidadeEmStock() {
        return quantidadeEmStock;
    }

    public void setQuantidadeEmStock(int quantidadeEmStock) {
        this.quantidadeEmStock = quantidadeEmStock;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        JoiaDto other = (JoiaDto) obj;
        return Objects.equals(nome, other.nome) &&
                Double.compare(peso, other.peso) == 0 &&
                Double.compare(preco, other.preco) == 0 &&
                Objects.equals(material, other.material) &&
                quantidadeEmStock == other.quantidadeEmStock &&
                tipo == other.tipo; // Adicionando o tipo na comparação
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, peso, preco, material, quantidadeEmStock, tipo); // Adicionando o tipo no hashCode
    }
}

