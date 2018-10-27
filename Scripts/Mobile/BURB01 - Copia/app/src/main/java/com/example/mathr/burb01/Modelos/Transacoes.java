package com.example.mathr.burb01.Modelos;

/**
 * Created by mathr on 04/03/2018.
 */

public class Transacoes {

    private String Nome;

    private int Valor;

    public Transacoes(){}

    public Transacoes(String nome, int valor) {
        Nome = nome;
        Valor = valor;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getValor() {
        return Valor;
    }

    public void setValor(int valor) {
        Valor = valor;
    }

}
