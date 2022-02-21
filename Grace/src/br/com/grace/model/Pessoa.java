package br.com.grace.model;

public abstract class Pessoa {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pessoa(int id) {
        this.id = id;
    }

}
