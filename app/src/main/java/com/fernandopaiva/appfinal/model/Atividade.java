package com.fernandopaiva.appfinal.model;

import java.io.Serializable;

public class Atividade implements Serializable {
    private int id;
    private String tituloAtividade;
    private String descricaoAtividade;
    private int feedbackId;  // Relacionado ao feedback
    private String acao;

    public Atividade() {
        // Construtor vazio necessário para deserialização
    }

    public Atividade(int id, String tituloAtividade, String descricaoAtividade, int feedbackId, String acao) {
        this.id = id;
        this.tituloAtividade = tituloAtividade;
        this.descricaoAtividade = descricaoAtividade;
        this.feedbackId = feedbackId;
        this.acao = acao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTituloAtividade() {
        return tituloAtividade;
    }

    public void setTituloAtividade(String tituloAtividade) {
        this.tituloAtividade = tituloAtividade;
    }

    public String getDescricaoAtividade() {
        return descricaoAtividade;
    }

    public void setDescricaoAtividade(String descricaoAtividade) {
        this.descricaoAtividade = descricaoAtividade;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    // Método para obter o título do feedback relacionado
    public String getTituloFeedback() {
        // Implementar a lógica para buscar o título do feedback a partir do feedbackId
        return "Título do Feedback";
    }
}
