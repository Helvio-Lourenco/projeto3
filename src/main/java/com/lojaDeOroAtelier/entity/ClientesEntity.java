package com.lojaDeOroAtelier.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
public class ClientesEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto incremento do ID
    private Long id;  // Novo campo id para integração com o banco


    private int nif;
    private String nome;
    private String email;
    private String contacto;
    private String morada;

    public ClientesEntity() {
    }

    // Construtor
    public ClientesEntity( int nif, String nome, String email, String contacto, String morada) {
        this.nome = nome;
        this.nif = nif;
        this.email = email;
        this.contacto = contacto;
        this.morada = morada;
    }

    // Getters e Setters

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

    // toString
    @Override
    public String toString() {
        return "Cliente [ID=" + id + ", Nif=" + nif + ", Nome=" + nome + ", Email=" + email +
                ", Contacto=" + contacto + ", Morada=" + morada + "]";
    }


}
