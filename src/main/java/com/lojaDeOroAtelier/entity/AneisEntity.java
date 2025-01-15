package com.lojaDeOroAtelier.entity;

import com.lojaDeOroAtelier.entity.interfac.Joia;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class AneisEntity extends JoiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Geração automática do ID
    private Long id;

    private double tamanho;




    public AneisEntity() {
    }

    public AneisEntity(JoiaEntity joiaEntity, double tamanho) {
        super(joiaEntity.getNome(), joiaEntity.getPeso(), joiaEntity.getPreco(), joiaEntity.getMaterial(), joiaEntity.getQuantidadeEmStock(), joiaEntity.getTipo());
        this.tamanho = tamanho;

    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }





    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }





    @Override
    public String toString() {
        return "Anel{" +
                "ID=" + id +
                ", Nome='" + getNome() + '\'' +
                ", Tamanho='" + tamanho + '\'' +
                ", Material='" + getMaterial() + '\'' +
                ", Peso=" + getPeso() +
                "g, Preço=" + getPreco() + " EUR" + "quantidade em stock = " + getQuantidadeEmStock() +
                '}';
    }

}
