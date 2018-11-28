package com.example.mathr.burb01.Modelos;

import java.util.Date;

/**
 * Created by mathr on 25/02/2018.
 */

public class Objetivos {

    private String Nome,Data;
    private float Valor;

    public Objetivos(){}

    public Objetivos(String nome, String data, float valor) {
        Nome = nome;
        Data = data;
        Valor = valor;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float valor) {
        Valor = valor;
    }
}
