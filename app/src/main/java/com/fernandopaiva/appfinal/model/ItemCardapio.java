package com.fernandopaiva.appfinal.model;

import java.io.Serializable;

public class ItemCardapio implements Serializable {
    private int id;
    private String titulo;
    private String descricao;
    private String tipo;

    public ItemCardapio(int id, String titulo, String descricao, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
