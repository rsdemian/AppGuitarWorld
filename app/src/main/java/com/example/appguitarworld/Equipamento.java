package com.example.appguitarworld;

public class Equipamento {
    public int id;
    public String nome;
    public String marca;
    public String valor;
    public String categoria;


    public Equipamento() { }

    public Equipamento(String nome, String marca,String valor ,String categoria) {
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return  categoria +": " + nome + "-" + marca + " - " + " R$ " + valor;
    }


}
