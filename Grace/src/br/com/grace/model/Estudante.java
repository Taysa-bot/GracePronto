package br.com.grace.model;

public class Estudante extends Pessoa {

    private String nome;
    private String senha;
    private int matricula;

    public Estudante(int id, String senha,String nome, int matricula) {
        super(id);
        this.senha = senha;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
}
