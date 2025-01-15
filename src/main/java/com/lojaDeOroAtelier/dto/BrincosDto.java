package com.lojaDeOroAtelier.dto;

import com.lojaDeOroAtelier.entity.BrincosEntity;

import java.io.Serializable;



public class BrincosDto extends JoiaDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String tipoFecho;  // Ex: argola, pendente, etc.


    public BrincosDto(){


    }

    public BrincosDto(BrincosEntity brincosEntity) {
        super(brincosEntity); // Chama o construtor da classe base
        this.tipoFecho = brincosEntity.getTipoFecho(); // Inicializa tipoFecho
        this.id = brincosEntity.getId();              // Inicializa id
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
                "ID=" + id +  // Aqui usamos o getId() herdado de JoiaDto
                ", Nome='" + getNome() + '\'' +
                ", Tipo='" + tipoFecho + '\'' +
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
        BrincosDto other = (BrincosDto) obj;
        return super.equals(obj) && // Chama o equals da classe base para comparar nome, peso, preco, material
                tipoFecho.equals(other.tipoFecho);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + tipoFecho.hashCode();
    }
}