package com.fernandopaiva.appfinal.model;

public class Usuario {
    private String loginUsuario;
    private String nome;
    private String sobrenome;
    private String descricao;
    private String senha;

    // Construtor
    public Usuario(String loginUsuario, String nome, String sobrenome, String descricao, String senha) {
        this.loginUsuario = loginUsuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.descricao = descricao;
        this.senha = senha;
    }

    // Getters e Setters
    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
