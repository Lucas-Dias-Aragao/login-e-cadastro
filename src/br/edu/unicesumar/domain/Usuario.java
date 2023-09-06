package br.edu.unicesumar.domain;

public class Usuario {
    private String nome;
    private String login;
    private String senha;
    private String email;

    public Usuario(String nome, String login, String senha, String email){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }
}
