package com.lojaDeOroAtelier.dto;

import com.lojaDeOroAtelier.entity.ColaresEntity;

import java.io.Serializable;

public class ColaresDto extends JoiaDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String comprimento;
    public ColaresDto (){}


    // Construtor que recebe ColaresEntity
    public ColaresDto(ColaresEntity colaresEntity) {
        super(colaresEntity); // Chama o construtor da classe base
        this.comprimento = colaresEntity.getComprimento(); // Inicializa comprimento
        this.id = colaresEntity.getId();                  // Inicializa id
    }

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
        return "Anel{" +
                "ID=" + id +
                ", Nome='" + getNome() + '\'' +
                ", Tamanho='" + comprimento + '\'' +
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
        ColaresDto other = (ColaresDto) obj;
        return super.equals(obj) &&  // Chama o equals da classe base para comparar nome, peso, preco, material
                comprimento.equals(other.comprimento);  // Corrigido: comparar o tamanho corretamente
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + comprimento.hashCode();
    }

}
