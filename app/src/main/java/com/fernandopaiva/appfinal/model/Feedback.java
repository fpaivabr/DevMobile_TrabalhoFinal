package com.fernandopaiva.appfinal.model;

import java.io.Serializable;

public class Feedback implements Serializable {
    private int id;
    private String titulo;
    private String descricao;
    private ItemCardapio cardapio; // Associação com ItemCardapio
    private String resposta;
    private String status;

    public Feedback(int id, String titulo, String descricao, ItemCardapio cardapio) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.cardapio = cardapio;
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

    public ItemCardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(ItemCardapio cardapio) {
        this.cardapio = cardapio;
    }

    public String getTituloCardapio() {
        return cardapio != null ? cardapio.getTitulo() : "";
    }

    public String getDescricaoCardapio() {
        return cardapio != null ? cardapio.getDescricao() : "";
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
