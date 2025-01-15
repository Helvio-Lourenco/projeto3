package com.lojaDeOroAtelier.dto;

import com.lojaDeOroAtelier.entity.AneisEntity;

import java.io.Serializable;
import java.util.Objects;

public class AneisDto extends JoiaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private double tamanho;

    public AneisDto(){


    }
    public AneisDto(AneisEntity aneisEntity) {
         // Chama o construtor da classe base
        this.tamanho = aneisEntity.getTamanho(); // Inicializa o atributo tamanho
                // Inicializa o atributo id
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

                ", Nome='" + getNome() + '\'' +
                ", Tamanho='" + tamanho + '\'' +
                ", Material='" + getMaterial() + '\'' +
                ", Peso=" + getPeso() +
                "g, Preço=" + getPreco() + " EUR" + "quantidade em stock = " + getQuantidadeEmStock() +
                '}';
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AneisDto other = (AneisDto) obj;
        return super.equals(obj) &&  // Chama o equals da classe base para comparar nome, peso, preco, material
                Double.compare(tamanho, other.tamanho) == 0;
    }




    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Double.hashCode(tamanho);
    }

}


