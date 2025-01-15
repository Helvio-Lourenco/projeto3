package com.lojaDeOroAtelier.entity;



import com.lojaDeOroAtelier.entity.interfac.Joia;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


@Entity
public class BrincosEntity extends JoiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gerando ID automaticamente no banco
    private Long id;


    private String tipoFecho;  // Atributo exclusivo de Brinco


    // Construtor padrão necessário para JPA
    public BrincosEntity() {
    }

    // Construtor com parâmetros
    public BrincosEntity(String nome,  double peso ,double preco, String material, String tipoFecho, int quantidadeEmStock) {
        super(nome, peso, preco, material, quantidadeEmStock, TipoJoia.BRINCO);
        this.tipoFecho = tipoFecho;

    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }



    public String getTipoFecho() {
        return tipoFecho;
    }

    public void setTipoFecho(String tipoFecho) {
        this.tipoFecho = tipoFecho;
    }



    @Override
    public String toString() {
        return "Brinco{" +
                "ID=" + id +
                ", Nome='" + getNome() + '\'' +
                ", Tipo Fecho='" + tipoFecho + '\'' +
                ", Material='" + getMaterial() + '\'' +
                ", Peso=" + getPeso() + "g" +
                ", Preço=" + getPreco() + " EUR" + "quantidade em stock = " + getQuantidadeEmStock() +
                '}';
    }


}

