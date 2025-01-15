package com.lojaDeOroAtelier.entity;

import com.lojaDeOroAtelier.entity.interfac.Joia;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Ou SINGLE_TABLE para uma tabela única
public class JoiaEntity implements Joia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    private double preco;
    private String material;
    private double peso;
    private int quantidadeEmStock;
    @Enumerated(EnumType.STRING)
    private TipoJoia tipo; // Novo atributo para o tipo da joia


    public enum TipoJoia {
        ANEL,
        COLAR,
        BRINCO
    }

    // Getters e Setters
    public JoiaEntity() {
        // Construtor vazio necessário para o JPA
    }

    public JoiaEntity(String nome, double peso, double preco, String material, int quantidadeEmStock, TipoJoia tipo) {
        this.nome = nome;
        this.peso = peso;
        this.preco = preco;
        this.material = material;
        this.quantidadeEmStock = quantidadeEmStock;
        this.tipo=tipo;
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

    // Getters e Setters para o id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}