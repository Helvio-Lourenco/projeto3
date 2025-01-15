package com.lojaDeOroAtelier.dto;

import com.lojaDeOroAtelier.entity.ClientesEntity;

import java.io.Serializable;
import java.util.Objects;

public class ClientesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private int nif;
    private String nome;
    private String email;
    private String contacto;
    private String morada;

     public ClientesDto() {


     }
    public ClientesDto(ClientesEntity clientesEntity) {
        this.id = clientesEntity.getId();           // Obtém o ID da entidade
        this.nif = clientesEntity.getNif();         // Obtém o NIF da entidade
        this.nome = clientesEntity.getNome();       // Obtém o nome da entidade
        this.email = clientesEntity.getEmail();     // Obtém o email da entidade
        this.contacto = clientesEntity.getContacto(); // Obtém o contacto da entidade
        this.morada = clientesEntity.getMorada();   // Obtém a morada da entidade
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }


    @Override
    public String toString() {
        return "ClienteDto{" +
                "ID=" + id +
                ", NIF=" + nif +
                ", Nome='" + nome + '\'' +
                ", Email='" + email + '\'' +
                ", Contacto='" + contacto + '\'' +
                ", Morada='" + morada + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ClientesDto other = (ClientesDto) obj;
        return nif == other.nif &&  // Comparar o NIF
                id.equals(other.id) &&  // Comparar o ID
                Objects.equals(nome, other.nome) &&
                Objects.equals(email, other.email) &&
                Objects.equals(contacto, other.contacto) &&
                Objects.equals(morada, other.morada);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, nif, nome, email, contacto, morada);
    }
}
